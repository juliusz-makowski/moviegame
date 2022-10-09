package pl.juliuszmakowski.moviegame;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class MovieGame {
    public static void main(String[] args) throws Exception {

        File file = new File("movielist.txt");
        Scanner scanner = new Scanner(file);

        // loop while count how many movie's titles include file.
        int movieNumbers = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            movieNumbers++;
        }
        int choiceLine = lotteryMachine(movieNumbers);

        // assigning the drawn title to the variable
        String title;
        title = Files.readAllLines(Paths.get("movielist.txt")).get(choiceLine);

        System.out.println("Guess the movie title");
        // creating title's patter of words
        int lettersInTitle = hiddenTitleCreator(title);


        Scanner keyboard = new Scanner(System.in);

        String yourLetter;
        int size = 10;    // size is number of chance if you give wrong letter size-1
        int mistakes = 0;

        char[] letters = new char[(lettersInTitle + size)];  //array with all letters
        char[] wrongletters = new char[size];       //array with wrong letters
        char[] create = new char[lettersInTitle]; // array with letters contained in title

        for (int i=0; i<(lettersInTitle + size); i++) {
            System.out.print("Write your letter: ");
            yourLetter = keyboard.nextLine();
            char yourLetter1 = yourLetter.charAt(0);
            letters[i] = yourLetter1;

            int x;
            x = title.indexOf(yourLetter1);
            if (x == -1) {
                wrongletters[mistakes] = yourLetter1;
                mistakes++;             //count wrong letters
            }
                // loop for create a new pattern of title when you set right letter and include this letter in pattern
            for (int j = 0; j < (lettersInTitle); j++) {
                for (int k = 0; k < (lettersInTitle + size); k++) {

                    int position = title.indexOf(" ", j);
                    int location = title.indexOf(letters[k], j);

                    if (position == j) {
                        System.out.print(" ");
                        create[j] = ' ';
                        break;
                    } else if (location == j) {
                        System.out.print(letters[k]);
                        create[j] = letters[k];
                        break;
                    } else if (k == (lettersInTitle + size - 1)) {
                        create[j] = '_';
                        System.out.print("_");
                    }
                }
            }
            System.out.println();

            String checkpoint = new String(create);

            if (mistakes == size) {     // if you got all 10 turns and didn't guess movie title - you lose
                System.out.println("you lose, the title is:");
                System.out.println(title);
                break;
            }
            else if (checkpoint.equals(title)) {    //if creating string from hidden pattern is the same with title
                System.out.println("You win");
                break;
            }
            else {      // if you don't know the title yet and you still have a chance
                System.out.print("You have guessed " + mistakes + " wrong letters: ");
                System.out.println(wrongletters);
            }
        }

        }

        //hiddenTitleCreator changes title to hidden pattern of letters
    private static int hiddenTitleCreator(String yourTitle){

        int lettersInTitle = yourTitle.length();
        System.out.println(lettersInTitle);
        int position;

        for (int i = 0; i < lettersInTitle; i++) {
            position = yourTitle.indexOf(" ", i);
            if (i == position) {
                System.out.print(" ");
            } else System.out.print("_");
        }
        System.out.println();
        return lettersInTitle;

    }
    // lotteryMachine return random number which represent line with movie title in file
    private static int lotteryMachine(int number){

        int randomNumber = (int) (Math.random() * number);

        return randomNumber;

    }




}

