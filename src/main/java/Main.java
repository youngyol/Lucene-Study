import domain.MovieIndexer;
import domain.MovieSearcher;
import util.CsvLoader;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        CsvLoader csvLoader = new CsvLoader();
        MovieIndexer movieIndexer = csvLoader.loadMovies();
        movieIndexer.index();
   
    }
}
