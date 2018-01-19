package hu.zsoki.cinegrow.api.omdb.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class OmdbSearchResponse implements OmdbResponse {

    @JsonProperty("Search")
    private List<OmdbSearchResultEntry> searchResults;

    @JsonProperty("totalResults")
    private String totalResults;

    @Getter(AccessLevel.NONE) // implemented through hasError
    @JsonProperty("Response")
    private String response;

    @Getter(AccessLevel.NONE) // implemented through getErrorMessage
    @JsonProperty("Error")
    private String errorMessage;

    @Override
    public boolean hasError() {
        return Boolean.getBoolean(response);
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}

/* Example response
{
    "Search": [
        {
            "Title": "Blade Runner",
            "Year": "1982",
            "imdbID": "tt0083658",
            "Type": "movie",
            "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg"
        },
        {
            "Title": "Blade",
            "Year": "1998",
            "imdbID": "tt0120611",
            "Type": "movie",
            "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BMTQ4MzkzNjcxNV5BMl5BanBnXkFtZTcwNzk4NTU0Mg@@._V1_SX300.jpg"
        },
        {
            "Title": "Blade II",
            "Year": "2002",
            "imdbID": "tt0187738",
            "Type": "movie",
            "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BOWVjZTIzNDYtNTBlNC00NTJjLTkzOTEtOTE0MjlhYzI2YTcyXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg"
        },
        {
            "Title": "Blade Runner 2049",
            "Year": "2017",
            "imdbID": "tt1856101",
            "Type": "movie",
            "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BNzA1Njg4NzYxOV5BMl5BanBnXkFtZTgwODk5NjU3MzI@._V1_SX300.jpg"
        },
        {
            "Title": "Blade: Trinity",
            "Year": "2004",
            "imdbID": "tt0359013",
            "Type": "movie",
            "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BMjE0Nzg2MzI3MF5BMl5BanBnXkFtZTYwMjExODQ3._V1_SX300.jpg"
        },
        {
            "Title": "Sling Blade",
            "Year": "1996",
            "imdbID": "tt0117666",
            "Type": "movie",
            "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BNGY5NWIxMjAtODBjNC00MmZhLTk1ZTAtNGRhYThlOTNjMTQwXkEyXkFqcGdeQXVyNTc1NTQxODI@._V1_SX300.jpg"
        },
        {
            "Title": "Dragon Blade",
            "Year": "2015",
            "imdbID": "tt3672840",
            "Type": "movie",
            "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BMTk0MjgxOTQ5MF5BMl5BanBnXkFtZTgwODA3NTUwNjE@._V1_SX300.jpg"
        },
        {
            "Title": "Shinobi: Heart Under Blade",
            "Year": "2005",
            "imdbID": "tt0475723",
            "Type": "movie",
            "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BMjAxMjE1NTEyMF5BMl5BanBnXkFtZTcwMDgxODkzMQ@@._V1_SX300.jpg"
        },
        {
            "Title": "The Hidden Blade",
            "Year": "2004",
            "imdbID": "tt0442286",
            "Type": "movie",
            "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BOTFjYTljMDYtZWY0OC00MTg3LWFhOWUtYjQzNjg2Njk0MjIxXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg"
        },
        {
            "Title": "Blade: The Series",
            "Year": "2006",
            "imdbID": "tt0823333",
            "Type": "series",
            "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BMTcxNjg5OTI5OF5BMl5BanBnXkFtZTcwNDczNjk0MQ@@._V1_SX300.jpg"
        }
    ],
    "totalResults": "228",
    "Response": "True"
}
*/