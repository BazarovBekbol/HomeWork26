import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MovieManager manager = new MovieManager("src/movies.json");

        System.out.println("Movies by actor 'Ian McKellen':");
        List<Movie> moviesByActor = manager.getMoviesByActor("Ian McKellen");
        manager.sortAndDisplayMovies(moviesByActor, Comparator.comparing(Movie::getName));

        System.out.println("\nMovies directed by 'Peter Jackson':");
        List<Movie> moviesByDirector = manager.getMoviesByDirector("Peter Jackson");
        manager.sortAndDisplayMovies(moviesByDirector, Comparator.comparing(Movie::getYear));

        System.out.println("\nMovies released in 2019:");
        List<Movie> moviesByYear = manager.getMoviesByYear(2019);
        manager.sortAndDisplayMovies(moviesByYear, Comparator.comparing(Movie::getYear).reversed());
    }
}