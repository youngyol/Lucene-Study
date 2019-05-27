package config;

import analyzer.EdgeNGramAnalyzer;
import org.apache.lucene.index.IndexWriterConfig;
import util.PropertyUtil;

public class LuceneConfig {
    public static final int ROWS = 10;
    public static final IndexWriterConfig indexWriterConfig;
    public static final String MOVIE_CSV_FILE_PATH;
    public static final String INDEX_PATH;


    static {
        String propertyName = "movie";
        String indexPathValue = "INDEX_PATH";
        String movieCsvPathValue = "MOVIE_CSV_PATH";
        PropertyUtil propertyUtil = new PropertyUtil(propertyName);
        INDEX_PATH = propertyUtil.getValue(indexPathValue);
        indexWriterConfig = new IndexWriterConfig(new EdgeNGramAnalyzer());
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        MOVIE_CSV_FILE_PATH = propertyUtil.getValue(movieCsvPathValue);
    }

}
