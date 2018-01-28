package io.github.andyradionov.guardiannews.model.network;

import io.github.andyradionov.guardiannews.app.App;
import io.github.andyradionov.guardiannews.model.dto.GetArticlesResponseDto;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Guardian news REST API
 *
 * @author Andrey Radionov
 */

public interface NewsApi {

    @GET(App.SEARCH_NEWS_QUERY)
    Observable<GetArticlesResponseDto> searchNews(@Query("q") String searchQuery);
}
