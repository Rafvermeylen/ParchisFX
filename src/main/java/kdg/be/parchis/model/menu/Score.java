package kdg.be.parchis.model.menu;

public class Score implements Comparable<Score> {
    private final String name;
    private final int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(o.score, score);
    }

    @Override
    public String toString() {
        return String.format("%-20s %d", name, score);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
