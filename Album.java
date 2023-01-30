import java.util.ArrayList;

public class Album implements Comparable<Album>{

    private int id;
    private ArrayList <String> artists;
    private String title;
    private int numOfSongs;

    public Album()
    {
        id = 0;
        artists = new ArrayList<>();
        title = "";
        numOfSongs = 0;
    }

    public Album(int id, ArrayList <String> artists, String title, int numOfSongs)
    {
        this.id = id;
        this.artists = artists;
        this.title = title;
        this.numOfSongs = numOfSongs;
    }

    @Override
    public int compareTo(Album other) {
        int check = 0;

        if (this.getNumOfSongs() < other.getNumOfSongs())
        {
            check = -1;
        }

        else if (this.getNumOfSongs() == other.getNumOfSongs())
        {
            check = 0;
        }

        else {
            check = 1;
        }

        return check;
    }

    public int getNumOfSongs()
    {
        return numOfSongs;
    }

    public String toString()
    {
        return "ID: " + this.numOfSongs + " -- " + this.artists;
    }
}
