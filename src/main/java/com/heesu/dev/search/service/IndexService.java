package com.heesu.dev.search.service;

import com.heesu.dev.search.entity.index.CommonRequestData;
import com.heesu.dev.search.core.IndexAccessor;
import com.heesu.dev.search.core.indexer.parser.IndexPaserFactory;
import com.heesu.dev.search.core.indexer.parser.AbstractIndexParser;
import com.heesu.dev.search.core.indexer.parser.IndexParserType;
import com.heesu.dev.search.entity.search.ResponseData;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndexService {

    private final IndexAccessor indexAccessor;

    @PostConstruct
    public void init() {
    }

    public ResponseData addDocuments(CommonRequestData indexData, IndexParserType type) {

        try {
            IndexWriter indexWriter = indexAccessor.getWriter();

            AbstractIndexParser indexParser = IndexPaserFactory.getParser(type);
            List<Document> docs = indexParser.parseData(indexData);
            indexWriter.addDocuments(docs);

            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseData(ResponseData.RESULT_OK);
    }
}
