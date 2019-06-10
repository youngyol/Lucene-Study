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
import java.util.Arrays;
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
        for(String field : LuceneConfig.FIELDS){
            if(isSearchableField(field)){
                document.add(new TermVectorTextField(field,movie.get(field), Field.Store.YES));
            } else {
                document.add(new StringField(field, movie.get(field), Field.Store.YES));
            }
        }
        return document;
    }

    private boolean isSearchableField(String field) {
        return Arrays.asList(LuceneConfig.SEARCHABLE_FIELDS).contains(field);
    }

    @Override
    public String toString() {
           return movies.stream()
                   .map(Movie::toString)
                   .collect(Collectors.joining("\n"));
    }
}
