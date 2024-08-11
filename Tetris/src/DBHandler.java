import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DBHandler {

    // Constructor: Create the CSV file if it does not exist
    public DBHandler() {
        File file = new File(Consts.FILE_PATH);
        try {
            // Create a new file if it does not exist
            if (!file.exists()) {
                System.out.println("File does not exist, creating a new file...");
                if (file.createNewFile()) {
                    System.out.println("New file created: " + Consts.FILE_PATH);
                    // Write the header if the file is newly created
                    try (FileWriter fileWriter = new FileWriter(file, true); 
                         PrintWriter printWriter = new PrintWriter(fileWriter)) {
                        printWriter.println("playerName,score,date");
                    }
                } else {
                    System.out.println("Failed to create new file.");
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("Error creating or accessing file: " + e.getMessage());
        }
    }

    // Save a new score to the CSV file
    public void saveScore(String playerName, int score) {
        String formattedDate = LocalDateTime.now().format(Consts.FORMATTER);

        try (FileWriter fileWriter = new FileWriter(Consts.FILE_PATH, true); // Append mode
                PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.printf("%s,%d,%s%n", playerName, score, formattedDate);
            System.out.println("Score saved: " + playerName + ", " + score + ", " + formattedDate);
        } catch (IOException e) {
            System.err.println("Error saving score: " + e.getMessage());
        }
    }

    // Get the highest score from the CSV file
    public int getHighScore() {
        int highScore = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(Consts.FILE_PATH))) {
            String line;
            // Skip the header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    try {
                        int score = Integer.parseInt(parts[1].trim());
                        if (score > highScore) {
                            highScore = score;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid score format: " + parts[1]);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return highScore;
    }
    // Fetch the high scores from the DBHandler
    public List<String[]> getHighScores() {
        List<String[]> scoresList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Consts.FILE_PATH))) {
            String line;
            // Skip header
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    scoresList.add(parts);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Sort the list by score in descending order
        Collections.sort(scoresList, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int score1 = Integer.parseInt(o1[1]);
                int score2 = Integer.parseInt(o2[1]);
                return Integer.compare(score2, score1); // Sort descending
            }
        });
        return scoresList;
    }
    
}