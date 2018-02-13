db = db.getSiblingDB('db1');
sh.enableSharding("movies");
sh.shardCollection("db1.movies", {"imdbID": "hashed"});