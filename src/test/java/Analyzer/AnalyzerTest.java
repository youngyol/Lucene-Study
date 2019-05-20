package Analyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface AnalyzerTest {

    default List<String> analyze(String text) throws IOException {
        List<String> result = new ArrayList<>();
        EdgeNGramAnalyzer analyzer = new EdgeNGramAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("string", text);
        CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
        try{
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                result.add(attr.toString());
            }
            tokenStream.end();
        } finally {
            tokenStream.close();
        }
        return result;
    }
}


