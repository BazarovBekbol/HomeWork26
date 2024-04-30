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
            return new MovieCollection();
        }
    }

    public void displayMovies() {
        movieCollection.getMovies().forEach(movie ->
                System.out.printf("Name: %s, Year: %d, Director: %s\n", movie.getName(), movie.getYear(), movie.getDirector().getFullName()));
    }

    public List<Movie> getMoviesByActor(String actorName) {
        return movieCollection.getMovies().stream()
                .filter(movie -> movie.getCast().stream().anyMatch(actor -> actor.getFullName().equalsIgnoreCase(actorName)))
                .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByDirector(String directorName) {
        return movieCollection.getMovies().stream()
                .filter(movie -> movie.getDirector().getFullName().equalsIgnoreCase(directorName))
                .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByYear(int year) {
        return movieCollection.getMovies().stream()
                .filter(movie -> movie.getYear() == year)
                .collect(Collectors.toList());
    }

    public void displayRolesByActor(String actorName) {
        movieCollection.getMovies().stream()
                .flatMap(movie -> movie.getCast().stream())
                .filter(actor -> actor.getFullName().equalsIgnoreCase(actorName))
                .distinct()
                .forEach(actor -> System.out.println(actor.getFullName() + " - " + actor.getRole()));
    }

    public void displayAllActorsSorted() {
        movieCollection.getMovies().stream()
                .flatMap(movie -> movie.getCast().stream())
                .distinct()
                .sorted(Comparator.comparing(Actor::getFullName))
                .forEach(actor -> System.out.println(actor.getFullName() + " - " + actor.getRole()));
    }
    public void sortAndDisplayMovies(List<Movie> movies, Comparator<Movie> comparator) {
        movies.stream()
                .sorted(comparator)
                .forEach(movie -> System.out.printf("Name: %s, Year: %d, Director: %s\n",
                        movie.getName(), movie.getYear(), movie.getDirector().getFullName()));
    }
}
