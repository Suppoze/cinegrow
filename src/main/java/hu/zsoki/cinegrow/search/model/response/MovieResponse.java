package hu.zsoki.cinegrow.search.model.response;

import hu.zsoki.cinegrow.api.omdb.model.response.OmdbMovieResponse;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbSearchResponse;
import hu.zsoki.cinegrow.data.mongo.document.Movie;
import lombok.Data;

@Data
public class MovieResponse {

    private final String title;  // TODO: temporary while no frontend

    public MovieResponse(OmdbMovieResponse omdbMovieResponse) {
        this.title = omdbMovieResponse.getTitle();
    }

    public MovieResponse(Movie existingMovie) {
        this.title = existingMovie.getTitle();
    }

}
