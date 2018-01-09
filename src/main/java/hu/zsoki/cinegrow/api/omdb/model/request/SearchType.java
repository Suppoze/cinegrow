package hu.zsoki.cinegrow.api.omdb.model.request;

public enum SearchType {
    MOVIE("movie"),
    SERIES("series"),
    EPISODE("episode");

    private final String argString;

    SearchType(String argString) {
        this.argString = argString;
    }

    public String getArgString() {
        return argString;
    }
}
