package util;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import domain.MovieIndexer;
import vo.Movie;

import java.io.FileReader;
import java.io.IOException;

public class CsvLoader {
    private String movieCsvFilePath;

    public CsvLoader() {
        movieCsvFilePath = "src/main/resources/movie.csv";
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public MovieIndexer loadMovies() {
        MovieIndexer movies = null;

        try (CSVReader csvReader = new CSVReader(new FileReader(movieCsvFilePath))) {
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
