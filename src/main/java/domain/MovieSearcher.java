package domain;

import config.LuceneConfig;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class MovieSearcher {

    public TopDocs search(String queryTerm) throws IOException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(LuceneConfig.INDEX_PATH)));
        IndexSearcher searcher = new IndexSearcher(reader);

        Query query = null;
        try {
            query = new MultiFieldQueryParser(LuceneConfig.SEARCHABLE_FIELDS, LuceneConfig.DEFAULT_ANALYZER).parse(queryTerm);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TopDocs result = searcher.search(query, LuceneConfig.ROWS);
        reader.close();

        return  result;
    }
}
