package hu.zsoki.cinegrow.data.mongo;

import hu.zsoki.cinegrow.data.mongo.document.Movie;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Profile("live")
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

    List<Movie> findMoviesByTitleContains(String title);

    Movie findMovieByImdbID(String imdbId);
}
