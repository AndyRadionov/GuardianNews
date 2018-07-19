package io.github.andyradionov.guardiannews.data.dto;

import java.util.List;

/**
 * DTO for deserialization all articles from JSON
 *
 * @author Andrey Radionov
 */

public class GetArticlesResponseDto {

    private Response response;

    public List<Article> getResults() {
        return response.results;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    private class Response {
        List<Article> results;
    }
}
