import java.util.Random;

/**
 * Tile
 * makes the individual tile for the scrabble game
 * July 4, 2022
 * @author Vlad Koval
 * @version 1.0
 */
public class Tile {
    private char value; // private char variable

    /**
     * default constructor to intialize value
     */
    public Tile(){
        this.value = ' '; // set it empty
    }

    /**
     * second constructor 
     * @param letter of type char
     */
    public Tile(char letter){
        this.value = letter; // sets this value to letter
    }

    /**
     * randomizes the alphabet and gets a letter
     */
    public void pickup(){
        Random r = new Random(); // using the randomize function
        value = (char)(r.nextInt(26) + 'A');
    }

    /**
     * getter method to return value
     * @return the letter value
     */
    public char getValue(){
        return value;
    }
    
}
