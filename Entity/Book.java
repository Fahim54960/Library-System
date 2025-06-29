package Entity;

import java.io.*;
import javax.swing.*;

public class Book {
    private String title;
    private String author;
    private String borrower;

    public Book() {}

    public Book(String title, String author, String borrower) {
        this.title = title;
        this.author = author;
        this.borrower = borrower;
    }

    public void saveRecord() {
        try {
            File file = new File("./Data/borrowers.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            fw.write(title + "," + author + "," + borrower + "\n");
            fw.close();
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving record!");
        }
    }
}
