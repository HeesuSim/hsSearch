package com.heesu.dev.search.entity.index;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class RequestFileData extends CommonRequestData{

    private MultipartFile file;
}
