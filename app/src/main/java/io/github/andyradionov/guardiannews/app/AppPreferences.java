package io.github.andyradionov.guardiannews.app;

/**
 * @author Andrey Radionov
 */

public class AppPreferences {

    public static final String API_KEY = "test";
    public static final String NEWS_BASE_URL = "http://content.guardianapis.com";
    public static final String SEARCH_NEWS_QUERY = "search?api-key=" + API_KEY + "&page-size=30&order-by=newest&show-fields=thumbnail,trailText";
    public static final String ALL_NEWS_QUERY = "";
    public static final String QUERY_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private AppPreferences() {
    }
}
