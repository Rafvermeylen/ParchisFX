package kdg.be.parchis.model.menu;

public record Score(String name, int score) implements Comparable<Score> {
    /**
     * This record compares the scores. It gives back a to-string method to print it in a nice way.
     * Authors: Rui Daniel Gomes Vieira & Raf Vermeylen
     */
    @Override
    public int compareTo(Score o) {
        return Integer.compare(o.score, score);
    }

    @Override
    public String toString() {
        return String.format("%-20s %d", name, score);
    }
}