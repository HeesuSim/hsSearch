package com.heesu.dev.search.core.indexer.parser;

public class IndexPaserFactory {

    public static AbstractIndexParser getParser(IndexParserType type) {
        if (type == IndexParserType.Basic) {
            return new BasicIndexParser();
        }

        else if (type == IndexParserType.CSV) {
            return new CsvIndexParser();
        }

        return null;
    }
}
