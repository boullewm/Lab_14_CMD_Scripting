import javax.swing.*;
import java.io.*;
import java.nio.file.*;
public class FileScan {
    public static void main(String[] args) {
        String fileName;
        if (args.length > 0) {
            fileName = args[0];
        } else {
            JFileChooser chooser = new JFileChooser("src");
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                fileName = chooser.getSelectedFile().getAbsolutePath();
            } else {
                System.out.println("No file selected. Exiting program.");
                return;
            }
        }
        try {
            processFile(fileName);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
    private static void processFile(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        if (!Files.exists(filePath)) {
            System.out.println("File does not exist: " + fileName);
            return;
        }
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();
                wordCount += line.split("\\s+").length;
            }
        }
        System.out.println("File Name: " + fileName);
        System.out.println("Lines: " + lineCount);
        System.out.println("Words: " + wordCount);
        System.out.println("Characters: " + charCount);
    }
}
