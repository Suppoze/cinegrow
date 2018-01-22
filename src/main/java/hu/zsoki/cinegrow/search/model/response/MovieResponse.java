package hu.zsoki.cinegrow.search.model.response;

import hu.zsoki.cinegrow.api.omdb.model.response.OmdbMovieResponse;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbSearchResponse;
import lombok.Data;

@Data
public class MovieResponse {

    private final OmdbMovieResponse omdbMovieResponse;  // TODO: temporary while no frontend

    public MovieResponse(OmdbMovieResponse omdbMovieResponse) {
        this.omdbMovieResponse = omdbMovieResponse;
    }
}
