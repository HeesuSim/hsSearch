package com.heesu.dev.search.core;

import com.heesu.dev.search.entity.config.HsSearchConfig;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * class for creating writer, saving documents
 */
@Service
public class IndexAccessor {

    @Autowired
    private HsSearchConfig hsSearchConfig;
    private Directory directory;

    @PostConstruct
    public void init() {
        initDirectory();
    }

    public IndexWriter getWriter() throws IOException {

        Directory directory = null;

        directory = this.directory;
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig writerConfig = new IndexWriterConfig();

        return new IndexWriter(directory, writerConfig);
    }

    public IndexSearcher getSearcher() throws IOException {


        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        System.out.println("num : " + reader.numDocs());
        return indexSearcher;

    }

    private void initDirectory() {
        if (directory == null) {
            File dir = new File(hsSearchConfig.getIndexPath());
            try {
                this.directory = FSDirectory.open(dir.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
