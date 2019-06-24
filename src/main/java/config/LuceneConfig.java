package config;

import analyzer.EdgeNGramAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriterConfig;
import util.PropertyUtil;

public class LuceneConfig {
    public static final int ROWS = 10;
    public static final IndexWriterConfig INDEX_WRITER_CONFIG;
    public static final String MOVIE_CSV_FILE_PATH;
    public static final String INDEX_PATH;
    public static final String[] FIELDS ={"key","title","titleEn","releaseYear","country","runtime","release","director","production"};
    public static final String[] SEARCHABLE_FIELDS = {"title","titleEn"};
    public static final Analyzer DEFAULT_ANALYZER = new EdgeNGramAnalyzer();
    public static final Analyzer SEARCH_ANALYZER = new StandardAnalyzer();

    static {
        String propertyName = "movie";
        String indexPathValue = "INDEX_PATH";
        String movieCsvPathValue = "MOVIE_CSV_PATH";
        PropertyUtil propertyUtil = new PropertyUtil(propertyName);
        INDEX_PATH = propertyUtil.getValue(indexPathValue);
        INDEX_WRITER_CONFIG = new IndexWriterConfig(DEFAULT_ANALYZER);
        INDEX_WRITER_CONFIG.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        MOVIE_CSV_FILE_PATH = propertyUtil.getValue(movieCsvPathValue);
    }

}
