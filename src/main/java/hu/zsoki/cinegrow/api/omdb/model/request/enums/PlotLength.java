package hu.zsoki.cinegrow.api.omdb.model.request.enums;

public enum PlotLength {
    SHORT("short"),
    FULL("full");

    private final String argString;

    PlotLength(String argString) {
        this.argString = argString;
    }

    public String getArgString() {
        return argString;
    }
}
