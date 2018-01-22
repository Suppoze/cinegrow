package hu.zsoki.cinegrow.data.mongo.document;

import hu.zsoki.cinegrow.api.omdb.model.response.OmdbMovieResponse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// TODO: add more fields.
@Document(collection = "movie")
public class Movie {

    @Id
    private long id;

    @Indexed
    private String title;

    @Indexed
    private String imdbID;

    private String year;
    private String type;
    private String poster;

    public Movie(OmdbMovieResponse movieResponse) {
        title = movieResponse.getTitle();
        imdbID = movieResponse.getImdbID();
        year = movieResponse.getYear();
        type = movieResponse.getType();
        poster = movieResponse.getPoster();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
