import domain.MovieIndexer;
import util.CsvLoader;


public class Main {
    public static void main(String[] args) {
        CsvLoader csvLoader = new CsvLoader();
        MovieIndexer movieIndexer = csvLoader.loadMovies();
        System.out.println(movieIndexer.toString());
    }
}
