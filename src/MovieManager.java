import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;

public class MovieManager {
    private MovieCollection movieCollection;

    public MovieManager(String filePath) {
        this.movieCollection = loadMovies(filePath);
    }
    private MovieCollection loadMovies(String filePath) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath))); // Чтение JSON файла
            Gson gson = new Gson();
            return gson.fromJson(json, MovieCollection.class); // Десериализация JSON в объект MovieCollection
        } catch (Exception e) {
            e.printStackTrace();
            return new MovieCollection(); // В случае ошибки возвращаем пустую коллекцию
        }
    }

    // Пример метода для отображения фильмов
    public void displayMovies() {
        if (movieCollection != null && movieCollection.getMovies() != null) {
            movieCollection.getMovies().forEach(movie ->
                    System.out.printf("Name: %s, Year: %d, Director: %s\n",
                            movie.getName(), movie.getYear(), movie.getDirector().getFullName()));
        }
    }

    public void displayMoviesByActor(String actorName) {
        movieCollection.getMovies().stream()
                .filter(movie -> movie.getCast().stream().anyMatch(actor -> actor.getFullName().equalsIgnoreCase(actorName)))
                .forEach(movie -> System.out.println(movie.getName()));
    }

    public void displayMoviesByDirector(String directorName) {
        movieCollection.getMovies().stream()
                .filter(movie -> movie.getDirector().getFullName().equalsIgnoreCase(directorName))
                .forEach(movie -> System.out.println(movie.getName()));
    }

    public void displayMoviesByYear(int year) {
        movieCollection.getMovies().stream()
                .filter(movie -> movie.getYear() == year)
                .forEach(movie -> System.out.println(movie.getName()));
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
}
