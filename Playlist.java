import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String id, name, description;
    private List<String> songIds = new ArrayList<>();

    public Playlist(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<String> getSongIds() { return songIds; }
}