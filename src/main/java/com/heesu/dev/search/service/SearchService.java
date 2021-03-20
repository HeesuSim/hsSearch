package com.heesu.dev.search.service;

import com.heesu.dev.search.core.IndexAccessor;
import com.heesu.dev.search.entity.search.ResponseData;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchService {

    private Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private IndexAccessor indexAccessor;

    public ResponseData searchAll() {
        ResponseData result = new ResponseData();
        try {
            IndexSearcher searcher = indexAccessor.getSearcher();

            //  --------

            Term term = new Term("LatM", "5");
            Query query = new TermQuery(term);


            TopDocs docs = searcher.search(query, 200);
            System.out.println("hit : " + docs.totalHits);

            for(ScoreDoc sd : docs.scoreDocs) {
                Document d = searcher.doc(sd.doc);

                System.out.println("city : " + d.get("City"));

            }


            // -----------------
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ---------

        return result;
    }
}
