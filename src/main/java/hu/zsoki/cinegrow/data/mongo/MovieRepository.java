package hu.zsoki.cinegrow.data.mongo;

import hu.zsoki.cinegrow.data.mongo.document.Movie;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

@Profile("live")
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

    Movie findMovieByTitle(String title);

    Movie findMovieByImdbID(String imdbId);

    // TODO use
    Movie findMovieByTitleContains(String title);
}
