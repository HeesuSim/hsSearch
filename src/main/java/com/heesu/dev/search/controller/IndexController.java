package com.heesu.dev.search.controller;

import com.heesu.dev.search.entity.index.RequestIndexData;
import com.heesu.dev.search.core.indexer.parser.AbstractIndexParserType;
import com.heesu.dev.search.entity.search.SearchResult;
import com.heesu.dev.search.service.IndexService;
import com.heesu.dev.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/index")
@RequiredArgsConstructor
public class IndexController {


    private final IndexService indexService;
    private final SearchService searchService;

    @PostMapping(value = "/documents")
    public String add(@RequestBody RequestIndexData data) {

        indexService.addDocuments(data, AbstractIndexParserType.Basic);
        return "ok";
    }

    @PostMapping(value = "/add/csv")

    @GetMapping(value = "")
    public SearchResult searchAll() {

        return searchService.searchAll();
    }
}
