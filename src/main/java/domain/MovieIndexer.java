package domain;

import vo.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieIndexer {
    private List<Movie> movies;

    public MovieIndexer(List<Movie> movies) {
        this.movies = movies;
    }


    @Override
    public String toString() {
           return movies.stream()
                   .map(Movie::toString)
                   .collect(Collectors.joining("\n"));
    }
}
