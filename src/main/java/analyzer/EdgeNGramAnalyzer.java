package analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.ngram.*;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class EdgeNGramAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer stream = new EdgeNGramWithOffsetTokenizer(1,30);
        TokenStream result = new LowerCaseFilter(stream);
        return new TokenStreamComponents(stream, result);
    }
}
