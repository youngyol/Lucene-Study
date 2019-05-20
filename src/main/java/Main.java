import domain.MovieIndexer;
import domain.MovieSearcher;
import util.CsvLoader;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        doSearch();
    }

    private static void makeIndex() {
        CsvLoader csvLoader = new CsvLoader();
        MovieIndexer movieIndexer = csvLoader.loadMovies();
        movieIndexer.index();
    }

    private static void doSearch() {
        MovieSearcher movieSearcher = new MovieSearcher();
        try {
            movieSearcher.search("매니페스토");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
