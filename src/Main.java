import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static MovieManager manager;

    public static void main(String[] args) {
        System.out.println("Welcome to the Movie Manager Application!");
        while (true) {
            System.out.println("\nAvailable actions:");
            System.out.println("1 - Load Movies");
            System.out.println("2 - Display All Movies");
            System.out.println("3 - Search Movies by Actor");
            System.out.println("4 - Search Movies by Director");
            System.out.println("5 - Search Movies by Year");
            System.out.println("6 - Display Roles by Actor");
            System.out.println("7 - Display All Actors Sorted");
            System.out.println("8 - Exit");
            System.out.print("Choose an action: ");
            int action = scanner.nextInt();
            scanner.nextLine(); 

            switch (action) {
                case 1:
                    loadMovies();
                    break;
                case 2:
                    displayMovies();
                    break;
                case 3:
                    searchMoviesByActor();
                    break;
                case 4:
                    searchMoviesByDirector();
                    break;
                case 5:
                    searchMoviesByYear();
                    break;
                case 6:
                    displayRolesByActor();
                    break;
                case 7:
                    displayAllActorsSorted();
                    break;
                case 8:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid action, please try again.");
            }
        }
    }

    private static void loadMovies() {
        System.out.print("Enter the path to the movies file (for example: src/movies.json): ");
        String path = scanner.nextLine();
        manager = new MovieManager(path);
        System.out.println("Movies loaded successfully.");
    }

    private static void displayMovies() {
        if (manager != null) {
            manager.displayMovies();
        } else {
            System.out.println("Please load movies first.");
        }
    }

    private static void searchMoviesByActor() {
        System.out.print("Enter actor's name: ");
        String name = scanner.nextLine();
        List<Movie> movies = manager.getMoviesByActor(name);
        manager.sortAndDisplayMovies(movies, Comparator.comparing(Movie::getName));
    }

    private static void searchMoviesByDirector() {
        System.out.print("Enter director's name: ");
        String name = scanner.nextLine();
        List<Movie> movies = manager.getMoviesByDirector(name);
        manager.sortAndDisplayMovies(movies, Comparator.comparing(Movie::getName));
    }

    private static void searchMoviesByYear() {
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        List<Movie> movies = manager.getMoviesByYear(year);
        manager.sortAndDisplayMovies(movies, Comparator.comparing(Movie::getName));
    }

    private static void displayRolesByActor() {
        System.out.print("Enter actor's name: ");
        String name = scanner.nextLine();
        manager.displayRolesByActor(name);
    }

    private static void displayAllActorsSorted() {
        if (manager != null) {
            manager.displayAllActorsSorted();
        } else {
            System.out.println("Please load movies first.");
        }
    }
}