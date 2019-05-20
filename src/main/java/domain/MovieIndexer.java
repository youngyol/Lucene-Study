package domain;

import Analyzer.EdgeNGramAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import util.PropertyUtil;
import vo.Movie;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MovieIndexer {
    private List<Movie> movies;

    public MovieIndexer(List<Movie> movies) {
        this.movies = movies;
    }

    public void index(){
        PropertyUtil propertyUtil = new PropertyUtil("movie");
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new EdgeNGramAnalyzer());
        String indexPath = propertyUtil.getValue("INDEX_PATH");
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        try(IndexWriter writer = new IndexWriter(FSDirectory.open(Paths.get(indexPath)) ,indexWriterConfig)){
            for(Movie movie : movies){
                writer.addDocument(makeDocument(movie));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Document makeDocument(Movie movie) {
        Document document = new Document();
        document.add(new StringField("key", movie.getKey(), Field.Store.YES));
        document.add(new TextField("title", movie.getTitle(), Field.Store.YES));
        document.add(new TextField("titleEn",movie.getTitleEn(), Field.Store.YES));
        document.add(new StringField("releaseYear",movie.getReleaseYear(), Field.Store.YES));
        document.add(new StringField("country",movie.getRelease(), Field.Store.YES));
        document.add(new StringField("runtime",movie.getCountry(), Field.Store.YES));
        document.add(new StringField("release",movie.getRuntime(), Field.Store.YES));
        document.add(new StringField("director",movie.getDirector(), Field.Store.YES));
        document.add(new StringField("production",movie.getProduction(), Field.Store.YES));
        return document;
    }

    @Override
    public String toString() {
           return movies.stream()
                   .map(Movie::toString)
                   .collect(Collectors.joining("\n"));
    }
}
