package vo;

import com.opencsv.bean.CsvBindByPosition;

public class Movie {
    @CsvBindByPosition(position = 0)
    private String key;
    @CsvBindByPosition(position = 1)
    private String title;
    @CsvBindByPosition(position = 2)
    private String titleEn;
    @CsvBindByPosition(position = 3)
    private String releaseYear;
    @CsvBindByPosition(position = 4)
    private String country;
    @CsvBindByPosition(position = 5)
    private String runtime;
    @CsvBindByPosition(position = 6)
    private String genre;
    @CsvBindByPosition(position = 7)
    private String release;
    @CsvBindByPosition(position = 8)
    private String director;
    @CsvBindByPosition(position = 9)
    private String production;

    public Movie() {
    }

    public Movie(String key, String title, String titleEn, String releaseYear, String country, String runtime, String genre, String release, String director, String production) {
        this.key = key;
        this.title = title;
        this.titleEn = titleEn;
        this.releaseYear = releaseYear;
        this.country = country;
        this.runtime = runtime;
        this.genre = genre;
        this.release = release;
        this.director = director;
        this.production = production;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    @Override
    public String toString() {
        return "MovieIndexer {" +
                "key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", titleEn='" + titleEn + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", country='" + country + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", release='" + release + '\'' +
                ", director='" + director + '\'' +
                ", production='" + production + '\'' +
                "}\n";
    }

    public String get(String field) {
        switch (field){
            case "key" :
                return  this.key;
            case "title" :
                return this.title;
            case "titleEn" :
                return this.titleEn;
            case "releaseYear" :
                return this.releaseYear;
            case "country" :
                return this.country;
            case "runtime" :
                return this.runtime;
            case "genre" :
                return this.genre;
            case "release" :
                return this.release;
            case "director" :
                return this.director;
            case "production" :
                return this.production;
            default:
                return null;
        }
    }
}
