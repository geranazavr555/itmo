import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ConsistentHashImpl<K> implements ConsistentHash<K> {
    private final SortedMap<Integer, Shard> vnodesToShard = new TreeMap<>();
    private final Map<Shard, Set<Integer>> shardToVnodes = new HashMap<>();


    @NotNull
    @Override
    public Shard getShardByKey(K key) {
        return vnodesToShard.get(upperVnode(key.hashCode()));
    }

    private Long getLen(Integer l, Integer r) {
        if (l <= r) {
            return (long)r - (long)l + (long)1;
        } else {
            return (long)Integer.MAX_VALUE - (long)Integer.MIN_VALUE - ((long)l - (long)r + (long)1);
        }
    }

    private Long getLen(HashRange hashRange) {
        return getLen(hashRange.getLeftBorder(), hashRange.getRightBorder());
    }

    private Set<HashRange> merge1(Set<Map.Entry<Integer, Integer>> rangeBorder) {
        SortedMap<Integer, Boolean> points = new TreeMap<>();
        boolean inverse = false;
        for (Map.Entry<Integer, Integer> lAndR : rangeBorder) {
            points.put(lAndR.getKey(), false);
            points.put(lAndR.getValue(), true);
            inverse |= lAndR.getKey() > lAndR.getValue();
        }

        SortedMap<Integer, Boolean> resultPoints = new TreeMap<>();
        Set<Integer> skip = new HashSet<>();
        for (Integer point : points.keySet()) {
            if (skip.contains(point))
                continue;
            if (points.get(point) && points.containsKey(point + 1) && !points.get(point + 1)) {
                skip.add(point + 1);
            } else {
                resultPoints.put(point, points.get(point));
            }
        }

        Set<HashRange> result = new HashSet<>();
        List<Integer> pointsList = new ArrayList<>(resultPoints.keySet());
        if (inverse) {
            pointsList.add(0, pointsList.remove(pointsList.size() - 1));
        }
        for (int i = 0; i < pointsList.size(); i += 2) {
            result.add(new HashRange(pointsList.get(i), pointsList.get(i + 1)));
        }
        return result;
    }

    private Set<HashRange> merge(Set<HashRange> ranges) {
        Map<Integer, Long> rangeLen = new HashMap<>();
        Map<Integer, Integer> rangeBorder = new HashMap<>();
        for (HashRange range : ranges) {
            if (!rangeLen.containsKey(range.getLeftBorder())) {
                rangeLen.put(range.getLeftBorder(), getLen(range));
                rangeBorder.put(range.getLeftBorder(), range.getRightBorder());
            } else {
                if (getLen(range) > rangeLen.get(range.getLeftBorder())) {
                    rangeLen.put(range.getLeftBorder(), getLen(range));
                    rangeBorder.put(range.getLeftBorder(), range.getRightBorder());
                }
            }
        }

        return merge1(rangeBorder.entrySet());
    }

    private Integer lowerVnode(Integer x) {
        SortedMap<Integer, Shard> lowerMap = vnodesToShard.headMap(x);
        if (!lowerMap.isEmpty())
            return lowerMap.lastKey();

        SortedMap<Integer, Shard> upperMap = vnodesToShard.tailMap(x);
        if (!upperMap.isEmpty())
            return upperMap.lastKey();

        throw new RuntimeException();
    }

    private Integer upperVnode(Integer x) {
        SortedMap<Integer, Shard> upperMap = vnodesToShard.tailMap(x);
        if (!upperMap.isEmpty())
            return upperMap.firstKey();

        SortedMap<Integer, Shard> lowerMap = vnodesToShard.headMap(x);
        if (!lowerMap.isEmpty())
            return lowerMap.firstKey();

        throw new RuntimeException();
    }

    @NotNull
    @Override
    public Map<Shard, Set<HashRange>> addShard(@NotNull Shard newShard, @NotNull Set<Integer> vnodeHashes) {
        if (vnodesToShard.isEmpty()) {
            vnodeHashes.forEach(vnode -> vnodesToShard.put(vnode, newShard));
            shardToVnodes.computeIfAbsent(newShard, _k -> new HashSet<>()).addAll(vnodeHashes);
            return Collections.emptyMap();
        }

        Map<Shard, Set<HashRange>> result = new HashMap<>();
        for (Integer vnodeHash : vnodeHashes) {
            int fromVnode = lowerVnode(vnodeHash);
            int toVnode = upperVnode(vnodeHash + 1);

            result.computeIfAbsent(vnodesToShard.get(toVnode), _k -> new HashSet<>())
                    .add(new HashRange(fromVnode + 1, vnodeHash));
        }


        for (Integer vnodeHash : vnodeHashes) {
            vnodesToShard.put(vnodeHash, newShard);
            shardToVnodes.computeIfAbsent(newShard, _k -> new HashSet<>()).addAll(vnodeHashes);
        }

        result.replaceAll((_shard, hashRanges) -> merge(hashRanges));
        return result;
    }

    @NotNull
    @Override
    public Map<Shard, Set<HashRange>> removeShard(@NotNull Shard shard) {
        Map<Shard, Set<HashRange>> result = new HashMap<>();

        for (Integer vnodeHash : shardToVnodes.get(shard)) {
            vnodesToShard.remove(vnodeHash);
        }

        for (Integer vnodeHash : shardToVnodes.get(shard)) {
            int fromVnode = lowerVnode(vnodeHash);
            int toVnode = upperVnode(vnodeHash + 1);

            result.computeIfAbsent(vnodesToShard.get(toVnode), _k -> new HashSet<>())
                    .add(new HashRange(fromVnode + 1, vnodeHash));
        }

        shardToVnodes.remove(shard);

        result.replaceAll((_shard, hashRanges) -> merge(hashRanges));
        return result;
    }
}
