public class Main {
    public static void main(String[] args) {
        MovieManager manager = new MovieManager("src/movies.json");

        System.out.println("Movies by actor 'Ian McKellen':");
        manager.displayMoviesByActor("Ian McKellen");

        System.out.println("\nMovies directed by 'Peter Jackson':");
        manager.displayMoviesByDirector("Peter Jackson");

        System.out.println("\nMovies released in 2019:");
        manager.displayMoviesByYear(2019);

        System.out.println("\nRoles played by 'Martin Freeman':");
        manager.displayRolesByActor("Martin Freeman");

        System.out.println("\nAll actors sorted:");
        manager.displayAllActorsSorted();
    }
}