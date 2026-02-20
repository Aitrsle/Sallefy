import java.util.*;

public class LasallefyController {
    private Map<String, Song> library = new HashMap<>();
    private Map<String, Playlist> playlists = new HashMap<>();
    private SoundSynth synth = new SoundSynth();

    public void addSong(Song s) { library.put(s.getId(), s); }
    public Collection<Song> getLibrary() { return library.values(); }

    public void createPlaylist(String id, String name, String desc) {
        playlists.put(id, new Playlist(id, name, desc));
    }

    public void deletePlaylist(String id) { playlists.remove(id); }

    public void addSongToPlaylist(String pId, String sId) {
        if (playlists.containsKey(pId) && library.containsKey(sId)) {
            playlists.get(pId).getSongIds().add(sId);
        }
    }

    public void removeSongFromPlaylist(String pId, String sId) {
        if (playlists.containsKey(pId)) playlists.get(pId).getSongIds().remove(sId);
    }

    public void playSong(String id) {
        Song s = library.get(id);
        if (s != null && s.isPlayable()) {
            try {
                for (Note n : s.getNotes()) {
                    synth.makeSound(n.getType(), n.getFrequency(), n.getDurationMs());
                }
            } catch (Exception e) {
                System.out.println("Error de audio.");
            }
        } else {
            System.out.println("Error: Canción no reproducible.");
        }
    }

    public void printPlaylistStats(String pId) {
        Playlist p = playlists.get(pId);
        if (p == null) return;
        int dur = 0, play = 0, noPlay = 0;
        for (String sId : p.getSongIds()) {
            Song s = library.get(sId);
            if (s != null) {
                dur += s.getDurationSeconds();
                if (s.isPlayable()) play++; else noPlay++;
            }
        }
        System.out.println("Playlist: " + p.getName() + " | Duración: " + dur + "s | Playables: " + play + " | No Playables: " + noPlay);
    }

    public Collection<Playlist> getPlaylists() { return playlists.values(); }
}