package hu.zsoki.cinegrow.api.omdb.model.request;

public class OmdbTitleOrIdRequestBuilder {

    public OmdbTitleOrIdRequest buildWithTitle(String title) {
        OmdbTitleOrIdRequest newInstance = new OmdbTitleOrIdRequest();
        newInstance.setTitle(title);
        newInstance.setId(null);
        return newInstance;
    }

    public OmdbTitleOrIdRequest buildWithId(String id) {
        OmdbTitleOrIdRequest newInstance = new OmdbTitleOrIdRequest();
        newInstance.setId(id);
        newInstance.setTitle(null);
        return newInstance;
    };

}