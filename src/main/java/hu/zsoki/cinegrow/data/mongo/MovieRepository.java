package hu.zsoki.cinegrow.data.mongo;

import hu.zsoki.cinegrow.data.mongo.document.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

    Movie findMovieByTitle(String title);

    Movie findMovieByImdbID(String imdbId);

}
