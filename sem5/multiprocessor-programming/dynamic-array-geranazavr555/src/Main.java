public class Main {
    public static void main(String[] args) {
        DynamicArray<Integer> a = new DynamicArrayImpl<>();
//        a.get(2);
//        a.put(1, 1);
        System.out.println(a.getSize());
        a.pushBack(6);
        a.pushBack(2);
        a.pushBack(1);
        a.put(2, 4);
    }
}
