package ru.itmo.sdcourse.hw9.search;

public record SearchResult(String searchEngineName, String title, String link, String content) {

    public SearchResult withEngineName(String searchEngineName) {
        return new SearchResult(searchEngineName, title, link, content);
    }
    public static SearchResult of(String title, String link, String content) {
        return new SearchResult(null, title, link, content);
    }
}
