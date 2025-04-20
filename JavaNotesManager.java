import java.io.*;
import java.util.*;

public class JavaNotesManager {
    public static void main(String[] args) throws IOException {
        String file1 = "JavaFile1.txt";
        String file2 = "JavaFile2.txt";

        createNote1(file1);
        System.out.println("Contents of " + file1 + ":");
        displayFile(file1);

        createNote2(file2);
        System.out.println("\nContents of " + file2 + ":");
        displayFile(file2);

        copyFileContents(file1, file2);
        System.out.println("\nContents of " + file2 + " after copying:");
        displayFile(file2);

        analyzeFile(file1);
        searchWord(file1, "polymorphism");
    }

    private static void createNote1(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write("Java is an object-oriented programming language."); bw.newLine();
        bw.write("It supports encapsulation, inheritance, and polymorphism."); bw.newLine();
        bw.write("File handling in Java allows for efficient reading and searching of text."); bw.newLine();
        bw.write("Keep learning and mastering Java!"); bw.newLine();
        bw.close();
    }

    private static void createNote2(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write("This is the first line in this JavaFile2.txt file."); bw.newLine();
        bw.close();
    }

    private static void displayFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    private static void copyFileContents(String source, String destination) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination, true);
        fos.write(System.lineSeparator().getBytes());
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b);
        }
        fis.close(); fos.close();
    }

    private static void analyzeFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        int chars = 0, words = 0, lines = 0;
        String line;
        while ((line = br.readLine()) != null) {
            lines++;
            chars += line.length();
            if (!line.isEmpty()) {
                words += line.trim().split("\\s+").length;
            }
        }
        br.close();
        System.out.println("\nTotal characters: " + chars);
        System.out.println("Total words: " + words);
        System.out.println("Total lines: " + lines);
    }

    private static void searchWord(String filename, String word) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        int occurrences = 0;
        List<Integer> linesFound = new ArrayList<>();
        String line;
        int lineNum = 0;
        while ((line = br.readLine()) != null) {
            lineNum++;
            String lower = line.toLowerCase();
            if (lower.contains(word.toLowerCase())) {
                linesFound.add(lineNum);
                int index = 0;
                while ((index = lower.indexOf(word.toLowerCase(), index)) != -1) {
                    occurrences++;
                    index += word.length();
                }
            }
        }
        br.close();
        if (occurrences > 0) {
            System.out.println("\n'" + word + "' found on lines " + linesFound);
            System.out.println("Total occurrences: " + occurrences);
        } else {
            System.out.println("\n'" + word + "' not found");
        }
    }
}
