package com.heesu.dev.search.core.indexer.parser;

import com.heesu.dev.search.entity.index.CommonRequestData;
import com.heesu.dev.search.entity.index.RequestFileData;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class CsvIndexParser extends AbstractIndexParser{

    Logger logger = LoggerFactory.getLogger(CsvIndexParser.class);

    @Override
    public List<Document> parseData(CommonRequestData indexData) {

        String delimiter = ",";
        RequestFileData fileData = (RequestFileData) indexData;
        MultipartFile file = fileData.getFile();
        List<Document> result = new ArrayList<Document>();

        try {
            InputStream stream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

            String[] headers = bufferedReader.readLine().split(delimiter);

            AtomicInteger count = new AtomicInteger();
            bufferedReader.lines().forEach(text -> {

                Document doc = new Document();

                if (!StringUtils.isEmpty(text)) {
                    String[] fields = text.split(delimiter);
                    IntStream.range(0, fields.length)
                            .forEach(i -> {

                                String key = headers[i].trim();
                                String value = fields[i].trim();

                                // data saved from csv file has default analyzed, stored value of boolean true.
                                Field field = new Field(key, value, getIndexableFieldType(true, true));
                                doc.add(field);
                            });
                }
                result.add(doc);
                count.incrementAndGet();

            });

            logger.info("#### " + count + " documents are added to index");
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("#### An error occured during inserting documents to index");
        }

        return result;
    }
}
