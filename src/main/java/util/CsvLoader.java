package util;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import domain.MovieIndexer;
import vo.Movie;

import java.io.FileReader;
import java.io.IOException;

import static config.LuceneConfig.MOVIE_CSV_FILE_PATH;

public class CsvLoader {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public MovieIndexer loadMovies() {
        MovieIndexer movies = null;

        try (CSVReader csvReader = new CSVReader(new FileReader(MOVIE_CSV_FILE_PATH))) {
            CsvToBean csvToBean = new CsvToBeanBuilder(csvReader)
                    .withType(Movie.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            movies = new MovieIndexer(csvToBean.parse());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return movies;
    }
}
