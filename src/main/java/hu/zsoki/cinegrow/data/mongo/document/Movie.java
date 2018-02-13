package hu.zsoki.cinegrow.data.mongo.document;

import hu.zsoki.cinegrow.api.omdb.model.response.OmdbMovieRatingEntry;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbMovieResponse;
import hu.zsoki.cinegrow.api.omdb.model.response.OmdbSearchResultEntry;
import hu.zsoki.cinegrow.data.mongo.exception.MongoDocumentMappingException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "movies")
public class Movie {

    @Id
    private String imdbID;

    @Indexed
    private String title;

    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private List<OmdbMovieRatingEntry> ratings;
    private String type;
    private String dvd;
    private String boxOffice;
    private String production;
    private String website;

    public Movie() {
    }

    public Movie(OmdbMovieResponse movieResponse) {
        if (movieResponse == null
                || movieResponse.getTitle() == null
                || movieResponse.getYear() == null
                || movieResponse.getImdbID() == null
                || movieResponse.getType() == null) {
            throw new MongoDocumentMappingException("Error while mapping OMDb movie response to MongoDB document");
        }

        this.title = movieResponse.getTitle();
        this.imdbID = movieResponse.getImdbID();
        this.year = movieResponse.getYear();
        this.type = movieResponse.getType();
        this.poster = movieResponse.getPoster();
        this.rated = movieResponse.getRated();
        this.released = movieResponse.getReleased();
        this.runtime = movieResponse.getRuntime();
        this.genre = movieResponse.getGenre();
        this.director = movieResponse.getDirector();
        this.writer = movieResponse.getWriter();
        this.actors = movieResponse.getActors();
        this.plot = movieResponse.getPlot();
        this.language = movieResponse.getLanguage();
        this.country = movieResponse.getCountry();
        this.awards = movieResponse.getAwards();
        this.ratings = movieResponse.getRatings();
        this.dvd = movieResponse.getDvd();
        this.boxOffice = movieResponse.getBoxOffice();
        this.production = movieResponse.getProduction();
        this.website = movieResponse.getWebsite();
    }

    public Movie(OmdbSearchResultEntry searchResultEntry) {
        if (searchResultEntry == null
                || searchResultEntry.getTitle() == null
                || searchResultEntry.getYear() == null
                || searchResultEntry.getImdbID() == null
                || searchResultEntry.getType() == null) {
            throw new MongoDocumentMappingException("Error while mapping OMDb search response to MongoDB document");
        }

        this.title = searchResultEntry.getTitle();
        this.year = searchResultEntry.getYear();
        this.imdbID = searchResultEntry.getImdbID();
        this.type = searchResultEntry.getType();
        this.poster = searchResultEntry.getPoster();
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<OmdbMovieRatingEntry> getRatings() {
        return ratings;
    }

    public void setRatings(List<OmdbMovieRatingEntry> ratings) {
        this.ratings = ratings;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDvd() {
        return dvd;
    }

    public void setDvd(String dvd) {
        this.dvd = dvd;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(imdbID, movie.imdbID) &&
                Objects.equals(year, movie.year) &&
                Objects.equals(rated, movie.rated) &&
                Objects.equals(released, movie.released) &&
                Objects.equals(runtime, movie.runtime) &&
                Objects.equals(genre, movie.genre) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(writer, movie.writer) &&
                Objects.equals(actors, movie.actors) &&
                Objects.equals(plot, movie.plot) &&
                Objects.equals(language, movie.language) &&
                Objects.equals(country, movie.country) &&
                Objects.equals(awards, movie.awards) &&
                Objects.equals(poster, movie.poster) &&
                Objects.equals(ratings, movie.ratings) &&
                Objects.equals(type, movie.type) &&
                Objects.equals(dvd, movie.dvd) &&
                Objects.equals(boxOffice, movie.boxOffice) &&
                Objects.equals(production, movie.production) &&
                Objects.equals(website, movie.website);
    }

    @Override
    public int hashCode() {

        return Objects
                .hash(title, imdbID, year, rated, released, runtime, genre, director, writer, actors, plot, language, country, awards, poster, ratings, type,
                        dvd,
                        boxOffice, production, website);
    }
}
