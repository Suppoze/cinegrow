package hu.zsoki.cinegrow.search.model.request;

import lombok.Data;

@Data
public class SearchRequest {

    private String title;
    private boolean cacheEnabled = true;

}
