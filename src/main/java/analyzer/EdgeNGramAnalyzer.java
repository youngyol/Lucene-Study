package analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ngram.EdgeNGramTokenFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class EdgeNGramAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        StandardTokenizer stream = new StandardTokenizer();
        TokenStream result = new LowerCaseFilter(stream);
        result = new EdgeNGramTokenFilter(result, 1, 30, true);
        return new TokenStreamComponents(stream, result);
    }

}
