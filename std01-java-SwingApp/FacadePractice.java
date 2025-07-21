/**
INSTRUCTIONS: 
    Demonstrates the Facade design pattern...

COMPILE & EXECUTE & CLEANUP (Java):

     javac  -d out              FacadePractice*.java
     java           -cp out     FacadePractice
     rm -rf out/
     












 */
import java.io.*;

public class FacadePractice {
    public static void main(String[] args) {
        // statically-typed language (e.g. Java, C/C++, C#)
        FileIOFacade f;

        ///////////////////////////////////////////////////
        // Open file in write mode
        f = open("sample2.txt", "w");
        f.write("This is the first line.\n");
        f.write("This is the second line.\n");
        // Manually close the file
        f.close();

        ///////////////////////////////////////////////////
        // Open file in read mode
        f = open("sample2.txt", "r");
        try {
            String content = f.read();
            System.out.println("File content:");
            System.out.println(content);
        } finally {
            // Close the file
            f.close();
        }
    }














    // Custom open() function like Python
    public static FileIOFacade open(String filename, String mode) {
        return new FileIOFacade(filename, mode);
    }
}
class FileIOFacade {
    // Field (Variable inside Class)
    private Writer writer;
    private Reader reader;
    private String mode;
    private String filename;
    // Constructor (special method when called by `new` keyword)
    public FileIOFacade(String filename, String mode){
        this.filename = filename;
        this.mode = mode;
        try {
            if (mode.equals("w")) writer = new FileWriter(filename);
            if (mode.equals("r")) reader = new FileReader(filename);
        } catch (IOException e) { throw new RuntimeException("Error opening file: " + filename); }
    }
    /*
     * Method (Function inside Class)
     */
    // Mimic Python's write() function
    public void write(String text) {
        if (!mode.equals("w")) throw new RuntimeException("File not opened in write mode!");
        try {
            writer.write(text);
        } catch (IOException e) { throw new RuntimeException("Error writing to file: " + filename); }
    }
    // Mimic Python's read() function
    public String read() {
        if (!mode.equals("r")) throw new RuntimeException("File not opened in read mode!");
        try {
            BufferedReader br = new BufferedReader(reader);
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) { throw new RuntimeException("Error reading file: " + filename); }
    }
    // Mimic Python's close() function
    public void close() {
        try {
            if (writer != null) writer.close();
            if (reader != null) reader.close();
        } catch (IOException e) { throw new RuntimeException("Error closing file: " + filename); }
    }
}