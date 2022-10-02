package pl.juliuszmakowski.moviegame;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MovieGame {
    public static void main(String[] args) throws Exception{

        File file = new File("movielist.txt");
        Scanner scanner = new Scanner(file);


       int movieNumbers = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            movieNumbers++;
        }

        int choiceLine = (int) (Math.random() * movieNumbers);
        System.out.println(choiceLine);

        String title;
        title = Files.readAllLines(Paths.get("movielist.txt")).get(choiceLine);
        System.out.println(title);

        int lettersInTitle = title.length();
        System.out.println(lettersInTitle);


    }
}
