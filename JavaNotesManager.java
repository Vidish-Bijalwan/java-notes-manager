import java.io.*;
import java.util.*;

public class JavaNotesManager {

    public static void main(String[] args) throws IOException {

        String f1 = "JavaFile1.txt";
        String f2 = "JavaFile2.txt";

        createFile1(f1);
        System.out.println("File1 (" + f1 + ") contents:");
        showFile(f1);

        createFile2(f2);
        System.out.println("\nFile2 (" + f2 + ") contents:");
        showFile(f2);

        copyStuff(f1, f2);
        System.out.println("\nAfter copying to File2:");
        showFile(f2);

        analyze(f1);
        findWord(f1, "polymorphism");
    }

    static void createFile1(String fname) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fname));
        bw.write("Java is an object-oriented programming language.");
        bw.newLine();
        bw.write("It supports encapsulation, inheritance, and polymorphism.");
        bw.newLine();
        bw.write("File handling in Java allows for efficient reading and searching of text.");
        bw.newLine();
        bw.write("Keep learning and mastering Java!");
        bw.newLine();
        bw.close();
    }

    static void createFile2(String fname) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fname));
        bw.write("This is the first line in JavaFile2.");
        bw.newLine();
        bw.close();
    }

    static void showFile(String fname) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fname));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    static void copyStuff(String src, String dest) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest, true);
        fos.write(System.lineSeparator().getBytes());

        int ch;
        while ((ch = fis.read()) != -1) {
            fos.write(ch);
        }
        fis.close();
        fos.close();
    }

    static void analyze(String fname) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fname));
        int chars = 0, words = 0, lines = 0;
        String line = null;

        while ((line = br.readLine()) != null) {
            lines++;
            chars += line.length();

            if (!line.trim().isEmpty()) {
                words += line.trim().split("\\s+").length;
            }
        }

        br.close();
        System.out.println("\nStats for " + fname + ":");
        System.out.println("Chars: " + chars);
        System.out.println("Words: " + words);
        System.out.println("Lines: " + lines);
    }

    static void findWord(String fname, String word) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fname));
        int occ = 0, lineNo = 0;
        List<Integer> foundLines = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {
            lineNo++;
            String lower = line.toLowerCase();
            if (lower.contains(word.toLowerCase())) {
                foundLines.add(lineNo);
                int idx = 0;
                while ((idx = lower.indexOf(word.toLowerCase(), idx)) != -1) {
                    occ++;
                    idx += word.length();
                }
            }
        }

        br.close();

        if (occ > 0) {
            System.out.println("\nWord '" + word + "' found on lines: " + foundLines);
            System.out.println("Total Occurrences: " + occ);
        } else {
            System.out.println("\nWord '" + word + "' not found");
        }
    }
}
