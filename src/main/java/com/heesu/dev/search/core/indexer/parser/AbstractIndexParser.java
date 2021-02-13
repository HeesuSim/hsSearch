package com.heesu.dev.search.core.indexer.parser;


import com.heesu.dev.search.entity.index.CommonRequestData;
import com.heesu.dev.search.entity.index.RequestListData;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableFieldType;

import java.util.List;

/**
 * Parser Interface that changes list data into list<Document>
 */
public abstract class AbstractIndexParser {


    public IndexableFieldType getIndexableFieldType(boolean isAnalyzed, boolean isStored) {
        if (isAnalyzed) {
            if (isStored) {
                return TextField.TYPE_STORED;
            } else {
                return TextField.TYPE_NOT_STORED;
            }
        } else {
            if (isStored) {
                return StringField.TYPE_STORED;
            } else {
                return StringField.TYPE_NOT_STORED;
            }
        }
    }

    public abstract List<Document> parseData(CommonRequestData indexData);
}
