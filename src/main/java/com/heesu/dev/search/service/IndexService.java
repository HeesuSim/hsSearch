package com.heesu.dev.search.service;

import com.heesu.dev.search.entity.index.RequestIndexData;
import com.heesu.dev.search.core.IndexAccessor;
import com.heesu.dev.search.core.indexer.parser.IndexPaserFactory;
import com.heesu.dev.search.core.indexer.parser.AbstractIndexParser;
import com.heesu.dev.search.core.indexer.parser.AbstractIndexParserType;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class IndexService {


    @Autowired
    private IndexAccessor indexAccessor;

    @PostConstruct
    public void init() {
    }

    public boolean addDocuments(RequestIndexData indexData, AbstractIndexParserType type) {

        try {
            IndexWriter indexWriter = indexAccessor.getWriter();

            AbstractIndexParser indexParser = IndexPaserFactory.getParser(type);
            List<Document> docs = indexParser.parseData(indexData);
            System.out.println("doc size : " + docs.size());
            indexWriter.addDocuments(docs);

            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }
}
