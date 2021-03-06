package hu.zsoki.cinegrow.api.omdb.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OmdbMovieResponse implements OmdbResponse {

    @Getter(AccessLevel.NONE) // implemented through hasError
    @JsonProperty("Response")
    private String response;

    @Getter(AccessLevel.NONE) // implemented through getErrorMessage
    @JsonProperty("Error")
    private String errorMessage;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Rated")
    private String rated;

    @JsonProperty("Released")
    private String released;

    @JsonProperty("Runtime")
    private String runtime;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Writer")
    private String writer;

    @JsonProperty("Actors")
    private String actors;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Language")
    private String language;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Awards")
    private String awards;

    @JsonProperty("Poster")
    private String poster;

    @JsonProperty("Ratings")
    private List<OmdbMovieRatingEntry> ratings;

    @JsonProperty("imdbID")
    private String imdbID;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("DVD")
    private String dvd;

    @JsonProperty("BoxOffice")
    private String boxOffice;

    @JsonProperty("Production")
    private String production;

    @JsonProperty("Website")
    private String website;

    @Override
    public boolean hasError() {
        return !Boolean.parseBoolean(response);
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}

/* Example response
{
   "Title":"Blade",
   "Year":"1998",
   "Rated":"R",
   "Released":"21 Aug 1998",
   "Runtime":"120 min",
   "Genre":"Action, Horror",
   "Director":"Stephen Norrington",
   "Writer":"David S. Goyer",
   "Actors":"Wesley Snipes, Stephen Dorff, Kris Kristofferson, N'Bushe Wright",
   "Plot":"A half-vampire, half-mortal man becomes a protector of the mortal race, while slaying evil vampires.",
   "Language":"English, Russian",
   "Country":"USA",
   "Awards":"4 wins & 8 nominations.",
   "Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMTQ4MzkzNjcxNV5BMl5BanBnXkFtZTcwNzk4NTU0Mg@@._V1_SX300.jpg",
   "Ratings":[
      {
         "Source":"Internet Movie Database",
         "Value":"7.1/10"
      },
      {
         "Source":"Rotten Tomatoes",
         "Value":"54%"
      },
      {
         "Source":"Metacritic",
         "Value":"45/100"
      }
   ],
   "Metascore":"45",
   "imdbRating":"7.1",
   "imdbVotes":"208,821",
   "imdbID":"tt0120611",
   "Type":"movie",
   "DVD":"22 Dec 1998",
   "BoxOffice":"N/A",
   "Production":"New Line Cinema",
   "Website":"N/A",
   "Response":"True"
}
*/