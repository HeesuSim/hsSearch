package com.heesu.dev.search.core.indexer.parser;

public class IndexPaserFactory {

    public static AbstractIndexParser getParser(AbstractIndexParserType type) {
        if (type == AbstractIndexParserType.Basic) {
            return new BasicIndexParser();
        }

        return null;
    }
}
