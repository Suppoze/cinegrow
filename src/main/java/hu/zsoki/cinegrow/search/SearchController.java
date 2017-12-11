package hu.zsoki.cinegrow.search;

import hu.zsoki.cinegrow.search.model.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("byTitle")
    public ResponseEntity<?> search(@RequestBody SearchRequest searchRequest) {
        try {
            return ResponseEntity.ok(searchService.search(searchRequest));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error");
        }
    }

}