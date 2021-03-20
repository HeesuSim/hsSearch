package com.heesu.dev.search.entity.search;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseData {

    public static String RESULT_OK = "ok";
    public static String RESULT_FAIL = "fail";

    public ResponseData(){
    }

    public ResponseData(String result) {
        this.result = result;
    }

    private String result;
    private String message;
    private List resultData;

}
