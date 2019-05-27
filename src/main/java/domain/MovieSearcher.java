package domain;

import config.LuceneConfig;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class MovieSearcher {

    public TopDocs search(String queryTerm) throws IOException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(LuceneConfig.INDEX_PATH)));
        IndexSearcher searcher = new IndexSearcher(reader);

        TermQuery termQuery = new TermQuery(new Term("title", queryTerm));
        TermQuery termQuery2 = new TermQuery(new Term("titleEn", queryTerm));


        BooleanQuery query = new BooleanQuery
                .Builder()
                .add(new BooleanClause(termQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(termQuery2, BooleanClause.Occur.SHOULD))
                .build();

        TopDocs result = searcher.search(query, LuceneConfig.ROWS);
        reader.close();

        return  result;
    }
}
