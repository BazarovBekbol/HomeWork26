import java.util.*;
public class Movie {
    private String name;
    private int year;
    private Director director;
    private List<Actor> cast;

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public Director getDirector() {
        return director;
    }

    public List<Actor> getCast() {
        return cast;
    }
}
