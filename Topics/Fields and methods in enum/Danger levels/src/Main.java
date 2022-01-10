
enum DangerLevel {
    HIGH(3, "HIGH"),
    MEDIUM(2, "MEDIUM"),
    LOW(1, "LOW");

    int level;
    String danger;

    DangerLevel(int level, String danger) {
        this.level = level;
        this.danger = danger;
    }

    public int getLevel() {
        return level;
    }
}
