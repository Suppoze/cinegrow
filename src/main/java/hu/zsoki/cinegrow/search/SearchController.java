package hu.zsoki.cinegrow.search;

import hu.zsoki.cinegrow.search.model.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("search")
    public ResponseEntity<?> search(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(searchService.search(searchRequest));
    }

    @PostMapping("find")
    public ResponseEntity<?> find(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(searchService.find(searchRequest));
    }

}