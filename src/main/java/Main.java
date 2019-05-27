import domain.MovieIndexer;
import domain.MovieSearcher;
import org.apache.lucene.search.TopDocs;
import util.CsvLoader;
import view.InputView;
import view.ResultView;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String command = InputView.inputCommand();
        switch (command.toLowerCase()){
            case "index" :
                makeIndex();
                break;
            case "search":
               TopDocs topDocs = doSearch();
               ResultView.printResult(topDocs);
               break;
            default:
                break;
        }
    }

    private static void makeIndex() {
        CsvLoader csvLoader = new CsvLoader();
        MovieIndexer movieIndexer = csvLoader.loadMovies();
        movieIndexer.index();
    }

    private static TopDocs doSearch() throws IOException {
        MovieSearcher movieSearcher = new MovieSearcher();
        String queryTerm = InputView.inputSearchTerm();
        return movieSearcher.search(queryTerm);
    }
}
