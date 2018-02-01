package hu.zsoki.cinegrow.search.model.response;

import hu.zsoki.cinegrow.api.omdb.model.response.OmdbMovieResponse;
import hu.zsoki.cinegrow.data.mongo.document.Movie;
import lombok.Data;

@Data
public class MovieResponse {

    private final String title;
    private final String year;
    private final String runtime;
    private final String genre;
    private final String director;
    private final String writer;
    private final String plot;
    private final String country;
    private final String imdbID;
    private final String type;

    public MovieResponse(OmdbMovieResponse omdbMovieResponse) {
        this.title = omdbMovieResponse.getTitle();
        this.year = omdbMovieResponse.getYear();
        this.runtime = omdbMovieResponse.getRuntime();
        this.genre = omdbMovieResponse.getGenre();
        this.director = omdbMovieResponse.getDirector();
        this.writer = omdbMovieResponse.getWriter();
        this.plot = omdbMovieResponse.getPlot();
        this.country = omdbMovieResponse.getCountry();
        this.imdbID = omdbMovieResponse.getImdbID();
        this.type = omdbMovieResponse.getType();
    }

    public MovieResponse(Movie existingMovie) {
        this.title = existingMovie.getTitle();
        this.year = existingMovie.getYear();
        this.runtime = existingMovie.getRuntime();
        this.genre = existingMovie.getGenre();
        this.director = existingMovie.getDirector();
        this.writer = existingMovie.getWriter();
        this.plot = existingMovie.getPlot();
        this.country = existingMovie.getCountry();
        this.imdbID = existingMovie.getImdbID();
        this.type = existingMovie.getType();
    }

}
