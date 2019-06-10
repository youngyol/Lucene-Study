package domain;

import config.LuceneConfig;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.vectorhighlight.FastVectorHighlighter;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> searchResultHighlight(String queryTerm) throws IOException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(LuceneConfig.INDEX_PATH)));
        IndexSearcher searcher = new IndexSearcher(reader);

        Query query = null;
        try {
            query = new MultiFieldQueryParser(LuceneConfig.SEARCHABLE_FIELDS, LuceneConfig.DEFAULT_ANALYZER).parse(queryTerm);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        List<String> result = new ArrayList<>();
        TopDocs topDocs = searcher.search(query, LuceneConfig.ROWS);
        FastVectorHighlighter fastVectorHighlighter = new FastVectorHighlighter();

        for (int i = 0; i < topDocs.scoreDocs.length; i++) {
            int docId = topDocs.scoreDocs[i].doc;
            StringBuilder docHighlight = new StringBuilder();
            for(String field : LuceneConfig.SEARCHABLE_FIELDS){
                String fragment = fastVectorHighlighter.getBestFragment(fastVectorHighlighter.getFieldQuery(query, reader),
                        reader, docId, field, 30);
                docHighlight.append("\t").append(field).append(":").append(fragment);
            }
            result.add(docHighlight.toString().trim());
        }
        reader.close();

        return result;
    }

    private Highlighter getHighlighter(Query query){
        Formatter formatter = new SimpleHTMLFormatter();
        QueryScorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, scorer);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer, 10);
        highlighter.setTextFragmenter(fragmenter);

        return highlighter;
    }
}
