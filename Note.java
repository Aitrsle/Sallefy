public class Note {
    private int frequency;
    private int durationMs;
    private WaveType type;

    public Note(int frequency, int durationMs, WaveType type) {
        this.frequency = frequency;
        this.durationMs = durationMs;
        this.type = type;
    }

    public int getFrequency() { return frequency; }
    public int getDurationMs() { return durationMs; }
    public WaveType getType() { return type; }
}