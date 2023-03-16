package kdg.be.parchis.model.menu;

public class Score implements Comparable<Score> {
    private String name;
    private int score;

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(o.score, score);
    }

    @Override
    public String toString() {
        return String.format("%-20s %d", name, score);
    }
}
