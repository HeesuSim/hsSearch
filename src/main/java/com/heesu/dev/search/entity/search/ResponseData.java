package com.heesu.dev.search.entity.search;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResult {

    private boolean result;
    private String message;
    private List resultData;

}
