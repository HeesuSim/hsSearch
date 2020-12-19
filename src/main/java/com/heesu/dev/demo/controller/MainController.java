package com.heesu.dev.demo.controller;

import org.apache.lucene.index.IndexWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/home")
    public String aaa() {

        return "sss";
    }
}
