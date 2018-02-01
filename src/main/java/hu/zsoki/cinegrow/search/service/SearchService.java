package hu.zsoki.cinegrow.search.service;

import hu.zsoki.cinegrow.search.model.request.SearchRequest;
import hu.zsoki.cinegrow.search.model.response.MovieResponse;
import hu.zsoki.cinegrow.search.model.response.SearchResponse;

public interface SearchService {

    SearchResponse search(SearchRequest searchRequest);

    MovieResponse getByTitle(String title);

    MovieResponse getById(String id);

}
