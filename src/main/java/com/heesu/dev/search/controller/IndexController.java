package com.heesu.dev.search.controller;

import com.heesu.dev.search.entity.index.RequestFileData;
import com.heesu.dev.search.entity.index.RequestListData;
import com.heesu.dev.search.core.indexer.parser.IndexParserType;
import com.heesu.dev.search.entity.search.SearchResult;
import com.heesu.dev.search.service.IndexService;
import com.heesu.dev.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/index")
@RequiredArgsConstructor
public class IndexController {


    private final IndexService indexService;
    private final SearchService searchService;

    @PostMapping(value = "/documents")
    public String add(@RequestBody RequestListData data) {
        indexService.addDocuments(data, IndexParserType.Basic);
        return "ok";
    }

    @GetMapping(value = "/documents")
    public SearchResult searchAll() {

        return searchService.searchAll();
    }

    //  리스트 타입 요청이랑 통합하는 수정 필
    @PostMapping(value = "/documents/file")
    public String addFile(@RequestParam("file") MultipartFile file) {

        RequestFileData data = new RequestFileData();
        data.setFile(file);
        indexService.addDocuments(data, IndexParserType.CSV);
        return "ok";
    }


}
