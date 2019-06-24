import domain.MovieIndexer;
import domain.MovieSearcher;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.TopDocs;
import util.CsvLoader;
import view.InputView;
import view.ResultView;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException{
        String command = InputView.inputCommand();
        switch (command.toLowerCase()){
            case "index" :
                makeIndex();
                break;
            case "search":
               ResultView.printResult(doRegularSearch());
               break;
            case "highlight":
                ResultView.printHighlightResult(doHighlightSearch());
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

    private static TopDocs doRegularSearch() throws IOException, ParseException {
        MovieSearcher movieSearcher = new MovieSearcher();
        String queryTerm = InputView.inputSearchTerm();
        return movieSearcher.search(queryTerm);
    }

    private static List<String> doHighlightSearch() throws IOException, ParseException {
        MovieSearcher movieSearcher = new MovieSearcher();
        String queryTerm = InputView.inputSearchTerm();
        return movieSearcher.searchResultHighlight(queryTerm);
    }
}
