package ru.itmo.sdcourse.hw9.search.engine;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itmo.sdcourse.hw9.search.SearchResult;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSystemSearchEngine extends SearchEngine {
    private static final Logger logger = LogManager.getLogger(FileSystemSearchEngine.class);
    private static final int CONTENT_PREVIEW_LENGTH = 256;
    private static final long FILE_SIZE_LIMIT_BYTES = 1024 * 1024 * 256;

    private final Path root;
    private final int resultsLimit;

    public FileSystemSearchEngine(int resultsLimit, String root) {
        this(resultsLimit, Path.of(root));
    }

    public FileSystemSearchEngine(int resultsLimit, File root) {
        this(resultsLimit, root.toPath());
    }

    public FileSystemSearchEngine(int resultsLimit, Path root) {
        super();
        Validate.isTrue(resultsLimit > 0, "Limit must be > 0");
        this.resultsLimit = resultsLimit;
        if (!Files.isDirectory(root))
            logger.warn(root + " is not a directory. Results will be always empty");
        this.root = root;
    }

    @Override
    public String getName() {
        return "fs: " + root;
    }

    private Optional<SearchResult> getSearchResult(Path path, String query) {
        try {
            String content = Files.readString(path);
            int firstOccurrence = content.indexOf(query);
            if (firstOccurrence < 0)
                return Optional.empty();

            String contentPreview = content.substring(firstOccurrence,
                    Math.min(firstOccurrence + CONTENT_PREVIEW_LENGTH, content.length()));

            return Optional.of(new SearchResult(
                    getName(),
                    path.getFileName().toString(),
                    "file://" + path.toAbsolutePath(),
                    contentPreview
            ));
        } catch (IOException e) {
            logger.error("IOException on file " + path + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    private long fileSize(Path path) {
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    protected List<SearchResult> doSearch(String query) {
        try (Stream<Path> paths = Files.walk(root, FileVisitOption.FOLLOW_LINKS)
                .filter(Files::isRegularFile)
                .filter(Files::isReadable)
                .filter(path -> fileSize(path) < FILE_SIZE_LIMIT_BYTES)) {
            return paths.map(path -> getSearchResult(path, query))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .limit(resultsLimit)
                    .collect(Collectors.toList());
        } catch (IOException | UncheckedIOException e) {
            logger.error("IOException: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
