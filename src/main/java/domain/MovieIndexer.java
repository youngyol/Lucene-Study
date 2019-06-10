package domain;

import config.LuceneConfig;
import field.TermVectorTextField;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
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
        try(IndexWriter writer = new IndexWriter(FSDirectory.open(Paths.get(LuceneConfig.INDEX_PATH)),
                LuceneConfig.INDEX_WRITER_CONFIG)){
            for(Movie movie : movies){
                writer.updateDocument(new Term("key",movie.getKey()), makeDocument(movie));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Document makeDocument(Movie movie) {
        Document document = new Document();
        document.add(new StringField("key", movie.getKey(), Field.Store.YES));
        document.add(new TermVectorTextField("title", movie.getTitle(), Field.Store.YES));
        document.add(new TermVectorTextField("titleEn",movie.getTitleEn(), Field.Store.YES));
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
