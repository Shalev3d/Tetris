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
	private Logger logger;
	
    // Constructor: Create the CSV file if it does not exist
    public DBHandler() {
    	this.logger = new Logger(Consts.LOG_LEVEL);
    	
        File file = new File(Consts.FILE_PATH);
        try {
            // Create a new file if it does not exist
            if (!file.exists()) {
                logger.debug("File does not exist, creating a new file...");
                if (file.createNewFile()) {
                    logger.debug("New file created: " + Consts.FILE_PATH);
                    // Write the header if the file is newly created
                    try (FileWriter fileWriter = new FileWriter(file, true); 
                         PrintWriter printWriter = new PrintWriter(fileWriter)) {
                        printWriter.println("playerName,score,date");
                    }
                } else {
                    logger.error("Failed to create new file.");
                }
            } else {
                logger.debug("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("Error creating or accessing file: " + e.getMessage());
        }
    }

    // Save a new score to the CSV file
    public void saveScore(String playerName, int score) {
        String formattedDate = LocalDateTime.now().format(Consts.FORMATTER);
        if (score >0) {
	        try (FileWriter fileWriter = new FileWriter(Consts.FILE_PATH, true); // Append mode
	                PrintWriter printWriter = new PrintWriter(fileWriter)) {
	            printWriter.printf("%s,%d,%s%n", playerName, score, formattedDate);
	            logger.info("Score saved: " + playerName + ", " + score + ", " + formattedDate);
	        } catch (IOException e) {
	            System.err.println("Error saving score: " + e.getMessage());
	        }
        }
        else {
        	logger.info("Score is 0, did not save it");
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