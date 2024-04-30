import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieManager {
    private MovieCollection movieCollection;

    public MovieManager(String filePath) {
        this.movieCollection = loadMovies(filePath);
    }

    private MovieCollection loadMovies(String filePath) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            Gson gson = new Gson();
            return gson.fromJson(json, MovieCollection.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void displayMovies() {
        movieCollection.getMovies().forEach(movie -> System.out.printf("Name: %s, Year: %d, Director: %s\n", movie.getName(), movie.getYear(), movie.getDirector().getFullName()));
    }

    public void searchMoviesByTitle(String query) {
        List<Movie> foundMovies = movieCollection.getMovies().stream()
                .filter(movie -> movie.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        if (foundMovies.isEmpty()) {
            System.out.println("No movies found.");
        } else {
            foundMovies.forEach(movie -> System.out.printf("Found: %s (%d)\n", movie.getName(), movie.getYear()));
        }
    }

    public void sortMoviesByYear(boolean ascending) {
        if (ascending) {
            movieCollection.getMovies().sort(Comparator.comparingInt(Movie::getYear));
        } else {
            movieCollection.getMovies().sort(Comparator.comparingInt(Movie::getYear).reversed());
        }
        displayMovies();
    }

    public void sortMoviesByName(boolean ascending) {
        if (ascending) {
            movieCollection.getMovies().sort(Comparator.comparing(Movie::getName));
        } else {
            movieCollection.getMovies().sort(Comparator.comparing(Movie::getName).reversed());
        }
        displayMovies();
    }

    public void sortMoviesByDirector(boolean ascending) {
        if (ascending) {
            movieCollection.getMovies().sort(Comparator.comparing(movie -> movie.getDirector().getFullName()));
        } else {
            movieCollection.getMovies().sort(Comparator.comparing(movie -> movie.getDirector().getFullName()).reversed());
        }
        displayMovies();
    }
}
