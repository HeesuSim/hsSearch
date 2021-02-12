package com.heesu.dev.search.core.indexer.parser;

import com.heesu.dev.search.entity.index.RequestIndexData;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * parse data that has format of [ [ {}, {}, {} ...] , [{}, {}, {} ...] ...]
 * Each index of nested list represents one document.
 * Each JSON in nested List reporesents one field.
 *
 * A field should have keys below
 *  - key: name of field:
 *  - value: value of field:
 *  - isAnalyzed: boolean value that determine if a field should be analyzed:
 *  - isStored: boolean value that determine if a field should be stored:
 *
 * */
public class BasicIndexParser extends AbstractIndexParser {
    @Override
    public List<Document> parseData(RequestIndexData indexData) {

        List<Document> result = new ArrayList<>();

        indexData.getList().stream().forEach(row -> {
            Document doc = new Document();
            ((List) row).stream().forEach(m -> {
                Map<String, Object> map = (Map<String, Object>) m;

                String fieldKey = (String) map.get(IndexKeys.INDEX_FIELD_KEY);
                String fieldValue = (String) map.get(IndexKeys.INDEX_FIELD_VALUE);
                boolean isAnalyzed = (boolean) map.get(IndexKeys.INDEX_FIELD_IS_ANALYZED);
                boolean isSotred = (boolean) map.get(IndexKeys.INDEX_FIELD_IS_STORED);

                Field field = new Field(fieldKey, fieldValue, super.getIndexableFieldType(isAnalyzed, isSotred));
                doc.add(field);
            });

            result.add(doc);

        });

        return result;
    }
}
