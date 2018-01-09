package hu.zsoki.cinegrow.api.omdb.model.request;

public enum DataType {
    JSON("json"),
    XML("xml");

    private final String argString;

    DataType(String argString) {
        this.argString = argString;
    }

    public String getArgString() {
        return argString;
    }
}
