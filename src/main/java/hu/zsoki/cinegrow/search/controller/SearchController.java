package hu.zsoki.cinegrow.search.controller;

import hu.zsoki.cinegrow.search.model.request.SearchRequest;
import hu.zsoki.cinegrow.search.model.response.MovieResponse;
import hu.zsoki.cinegrow.search.model.response.SearchResponse;
import hu.zsoki.cinegrow.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<MovieResponse> view(@RequestParam String id) {
        return ResponseEntity.ok(searchService.getById(id));
    }

}