public class Main {
    public static void main(String[] args) {
        MovieManager manager = new MovieManager("src/movies.json");

        System.out.println("All movies:");
        manager.displayMovies();

        System.out.println("\nMovies containing 'Hobbit' in the title:");
        manager.searchMoviesByTitle("Hobbit");

        System.out.println("\nMovies sorted by name ascending:");
        manager.sortMoviesByName(true);

        System.out.println("\nMovies sorted by name descending:");
        manager.sortMoviesByName(false);

        System.out.println("\nMovies sorted by year ascending:");
        manager.sortMoviesByYear(true);

        System.out.println("\nMovies sorted by year descending:");
        manager.sortMoviesByYear(false);

        System.out.println("\nMovies sorted by director's name ascending:");
        manager.sortMoviesByDirector(true);

        System.out.println("\nMovies sorted by director's name descending:");
        manager.sortMoviesByDirector(false);
    }
}