/*
This code is used for the leaderboard. It uses the 'scores.csv' file to get the top-scores. It only shows the top 5.
*/

package kdg.be.parchis.model.menu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Leaderboards {
    private static List<Score> scores;

    public static void addScore(Score score) {
        if (scores == null) {
            read();
        }
        scores.add(score);
        store();
    }

    public static void read() {
        ArrayList<Score> newList = new ArrayList<>();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(Paths.get("resources/data/scores.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String line = fileScanner.nextLine();
        while (fileScanner.hasNext()) {
            line = fileScanner.nextLine();
            String[] parts = line.split(", ");
            String name = parts[0].substring(1, parts[0].length() - 1);
            int score = Integer.parseInt(parts[1]);
            newList.add(new Score(name, score));
        }
        fileScanner.close();
        scores = new ArrayList<>(newList);
    }

    public static void store() {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter(Paths.get("resources/data/scores.csv").toFile()));
            for (Score s : scores) {
                //Write in file
                writer.printf("\"%s\", %d\n", s.name(), s.score());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.close();
    }

    public static String printScores() {
        StringBuilder sb = new StringBuilder();
        scores.sort(Collections.reverseOrder());
        for (int i = 0; i < 5; i++) {
            sb.append(i + 1).append(". ").append(scores.get(i)).append("\n");
        }
        return sb.toString();
    }
}