import java.util.ArrayList;
import java.util.List;

public class Song {
    private String id, title, artist, style;
    private int durationSeconds;
    private Mood mood;
    private List<Note> notes = new ArrayList<>();

    public Song(String id, String title, String artist, int durationSeconds, Mood mood, String style) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
        this.mood = mood;
        this.style = style;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getDurationSeconds() { return durationSeconds; }
    public List<Note> getNotes() { return notes; }
    public boolean isPlayable() { return !notes.isEmpty(); }

    @Override
    public String toString() {
        String status = isPlayable() ? "[PLAYABLE]" : "[NOT PLAYABLE]";
        return String.format("%s | %-15s | %-12s | %ds | %-8s | %s", 
                id, title, artist, durationSeconds, mood, status);
    }
}