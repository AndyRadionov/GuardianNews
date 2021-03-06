package io.github.andyradionov.guardiannews.data.network;

import io.github.andyradionov.guardiannews.app.AppPreferences;
import io.github.andyradionov.guardiannews.data.dto.GetArticlesResponseDto;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Guardian news REST API
 *
 * @author Andrey Radionov
 */

public interface NewsApi {

    @GET(AppPreferences.SEARCH_NEWS_QUERY)
    Observable<GetArticlesResponseDto> searchNews(@Query("q") String searchQuery);
}
