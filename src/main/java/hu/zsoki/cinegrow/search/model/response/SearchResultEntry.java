package hu.zsoki.cinegrow.search.model.response;

import hu.zsoki.cinegrow.api.omdb.model.response.OmdbSearchResultEntry;
import hu.zsoki.cinegrow.data.mongo.document.Movie;
import lombok.Data;

@Data
public class SearchResultEntry {

    private final String title;
    private final String imdbID;
    private final String year;

    SearchResultEntry(OmdbSearchResultEntry omdbSearchResultEntry) {
        this.title = omdbSearchResultEntry.getTitle();
        this.imdbID = omdbSearchResultEntry.getImdbID();
        this.year = omdbSearchResultEntry.getYear();
    }

    public SearchResultEntry(Movie movie) {
        this.title = movie.getTitle();
        this.imdbID = movie.getImdbID();
        this.year = movie.getYear();
    }
}
