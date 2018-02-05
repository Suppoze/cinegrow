package hu.zsoki.cinegrow.search.controller;

import hu.zsoki.cinegrow.search.model.request.SearchRequest;
import hu.zsoki.cinegrow.search.model.response.MovieResponse;
import hu.zsoki.cinegrow.search.model.response.SearchResponse;
import hu.zsoki.cinegrow.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("search")
    public ResponseEntity<SearchResponse> search(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(searchService.search(searchRequest));
    }

    @GetMapping("view")
    public ResponseEntity<?> view(@RequestParam(required = false) String id, @RequestParam(required = false) String title) {
        // Optionallal megoldani
        if (id != null) {
            return ResponseEntity.ok(searchService.getById(id));
        }
        if (title != null) {
            return ResponseEntity.ok(searchService.getByTitle(title));
        }
        return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.getReasonPhrase());
    }

}