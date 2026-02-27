import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SoundSynth synth = new SoundSynth();

        try {
            synth.makeSound(WaveType.SINE, 440);
            synth.makeSound(WaveType.SINE, 440, 200);
            synth.makeSound(WaveType.SINE, 700, 500);
            synth.makeSound(WaveType.SINE, 440, 1000);

            synth.makeSound(WaveType.SQUARE, 440, 1000);
            synth.makeSound(WaveType.SQUARE, 440, 200);
            synth.makeSound(WaveType.SQUARE, 700, 500);
            synth.makeSound(WaveType.SQUARE, 440);

            synth.makeSound(WaveType.TRIANGLE, 440);
            synth.makeSound(WaveType.TRIANGLE, 440, 200);
            synth.makeSound(WaveType.TRIANGLE, 700, 500);
            synth.makeSound(WaveType.TRIANGLE, 440, 1000);

            synth.makeSound(WaveType.SAWTOOTH, 440, 1000);
            synth.makeSound(WaveType.SAWTOOTH, 440, 200);
            synth.makeSound(WaveType.SAWTOOTH, 700, 500);
            synth.makeSound(WaveType.SAWTOOTH, 440, 1000);
        } catch(Exception e) {
            System.out.println(e);
        }
        
        LasallefyController controller = new LasallefyController();
        Scanner sc = new Scanner(System.in);
        setupData(controller);

        String op = "";
        while (!op.equals("Q")) {
            System.out.println("LASALLEFY - Tu música, tu estilo");
            System.out.println("1. Canciones | 2. Playlists | 3. Reproducir | Q. Salir");
            op = sc.nextLine().toUpperCase();

            switch (op) {
                case "1" -> controller.getLibrary().forEach(System.out::println);
                case "2" -> managePlaylists(controller, sc);
                case "3" -> {
                    System.out.print("ID Canción: ");
                    controller.playSong(sc.nextLine());
                }
            }
        }
    }

    private static void setupData(LasallefyController controller) {
        Song s1 = new Song("1", "CANCIÓN 1 ", "ARTISTA 1", 10, Mood.HAPPY, "Synth");
        s1.getNotes().add(new Note(440, 400, WaveType.SINE));
        s1.getNotes().add(new Note(523, 400, WaveType.SINE));
        controller.addSong(s1);
        controller.addSong(new Song("2", "CANCIÓN 2", "ARTISTA 2", 120, Mood.RELAX, "Pop"));
        controller.createPlaylist("P1", "Favoritos", "Mis temas");
    }

    private static void managePlaylists(LasallefyController controller, Scanner sc) {
        System.out.println("A. Crear | B. Listar | C. Añadir Canción | D. Borrar Playlist");
        String sub = sc.nextLine().toUpperCase();
        if (sub.equals("A")) {
            System.out.print("ID y Nombre: ");
            controller.createPlaylist(sc.nextLine(), sc.nextLine(), "");
        } else if (sub.equals("B")) {
            controller.getPlaylists().forEach(p -> controller.printPlaylistStats(p.getId()));
        } else if (sub.equals("C")) {
            System.out.print("ID Playlist e ID Canción: ");
            controller.addSongToPlaylist(sc.nextLine(), sc.nextLine());
        } else if (sub.equals("D")) {
            controller.deletePlaylist(sc.nextLine());
        }
    }
}