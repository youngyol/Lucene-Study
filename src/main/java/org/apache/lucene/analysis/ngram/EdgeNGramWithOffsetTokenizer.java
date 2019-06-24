package org.apache.lucene.analysis.ngram;


import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.util.AttributeFactory;

/**
 * Tokenizes the input from an edge into n-grams of given size(s).
 * <p>
 * This {@link Tokenizer} create n-grams from the beginning edge of a input token.
 * <p><a name="match_version"></a>As of Lucene 4.4, this class supports
 * {@link #isTokenChar(int) pre-tokenization} and correctly handles
 * supplementary characters.
 */
public class EdgeNGramWithOffsetTokenizer extends NGramTokenizer {
    public static final int DEFAULT_MAX_GRAM_SIZE = 1;
    public static final int DEFAULT_MIN_GRAM_SIZE = 1;

    /**
     * Creates EdgeNGramTokenizer that can generate n-grams in the sizes of the given range
     *
     * @param minGram the smallest n-gram to generate
     * @param maxGram the largest n-gram to generate
     */
    public EdgeNGramWithOffsetTokenizer(int minGram, int maxGram) {
        super(minGram, maxGram, true);
    }

    /**
     * Creates EdgeNGramTokenizer that can generate n-grams in the sizes of the given range
     *
     * @param factory {@link org.apache.lucene.util.AttributeFactory} to use
     * @param minGram the smallest n-gram to generate
     * @param maxGram the largest n-gram to generate
     */
    public EdgeNGramWithOffsetTokenizer(AttributeFactory factory, int minGram, int maxGram) {
        super(factory, minGram, maxGram, true);
    }

    @Override
    protected boolean isTokenChar(int chr) {
        return Character.isLetterOrDigit(chr);
    }


}

