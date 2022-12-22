import java.util.ArrayList;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Scrabble
 * Program determines what 7 tiles i could place for the highest score
 * July 5, 2022
 * @author Vlad Koval
 * @version 1.0
 */
public class Scrabble{
    private Tile[] tile; //object variable tile

    /**
     * default constructor that intializes the 7 tiles
     */
    public Scrabble(){
        this.tile = new Tile[7]; // intializing the 7 spaces for tiles
        for(int i = 0; i<tile.length;i++){ // for looping through the size of tiles
            tile[i] = new Tile();
            tile[i].pickup(); // calling the pickup method to get the tile at i
        }
    }

    /**
     * second constructor intializing tiles with the parameter
     * @param t a tile object
     */
    public Scrabble(Tile[] t){
        this.tile = t;
    }

    /**
     * gets the indivdual tiles and adds it to a string
     * @return a string of the 7 random tiles
     */
    public String getLetters(){
        String str = "";

        for(int i = 0; i<tile.length;i++){
            str += tile[i].getValue(); // keeps adding to str for the length of the for loop
        }
        return str;
        
    }

    /**
     * gets the valid words for the 7 tiles
     * @return an arraylist of type string of valid words
     * @throws IOException if file operations dont occur
     */
    public ArrayList<String> getWords() throws IOException{
        ArrayList<String> strList = new ArrayList<String>(); // making the array list string
        String[] listOfWords = new String[279496]; // list of all words
        ArrayList<String> listofTrialWords = new ArrayList<String>(); // list of words 7 letter or under
        ArrayList<Character> tempChar = new ArrayList(); //temporary char array list
        ArrayList<Character> myLetters = new ArrayList(); //array list with the 7 letters letters
        int counter = 0; // int counter variable
        for(int i = 0; i< 7;i++){ // looping 7 times
            myLetters.add(getLetters().charAt(i)); // adding letters to my letters 7 times
        }
        //System.out.println(myLetters);

        ArrayList<Character> holdChar = new ArrayList(); // putting marked letters in

        FileReader fileInvc = new FileReader("CollinsScrabbleWords2019.txt"); // creating a file reader for the text file
        BufferedReader readervc = new BufferedReader(fileInvc); // creating a buffered reader for the text file
        wordGetter(listOfWords, listofTrialWords, readervc);// gets the words from the text file

        for(int i = 0;i<listofTrialWords.size();i++){ // looping through the trial words

            //good
            for(int h = 0; h < listofTrialWords.get(i).length(); h++){ // looping through the individual word
                tempChar.add(listofTrialWords.get(i).charAt(h)); // adding each letter to the temp variable
            }
            for(int j = 0; j<tempChar.size();j++){  // looping through the temp char
                for(int x = 0; x<myLetters.size();x++){ // looping through myLetters
                    if(tempChar.get(j).charValue() == (myLetters.get(x).charValue())){ // tempChar at j is equal to my letters at x
                        holdChar.add(myLetters.get(x)); // adding it to holdChar
                        myLetters.remove(x); // removing it because it is already used
                        break; 
                    }
                }
            }
            
            if(listofTrialWords.get(i).length() == holdChar.size()){ // if holdChar is the same length and trail words
                Collections.sort(tempChar); // sorting both arraylists
                Collections.sort(holdChar);
                for(int z =0; z< holdChar.size();z++){ // looping through holdChar
                    if(holdChar.get(z).charValue() == tempChar.get(z).charValue()){ // if each value is equal
                        counter++;
                    }
                }
                if(counter == holdChar.size()){ // if counter is the same length as holdchar
                    strList.add(listofTrialWords.get(i)); // add to the strList
                }
            }

            //clearing all the arrays and arrayLists
            myLetters.clear();
            tempChar.clear();
            holdChar.clear();
            counter = 0; // setting it to 0
            // re-intializing myLetters again
            for(int y = 0; y< 7;y++){
                myLetters.add(getLetters().charAt(y));
            }
        }
        
        return strList;
    }

    /**
     * Gtes the valid words from the text file
     * @param listOfWords all the words in the text file
     * @param listofTrialWords words under 7 length
     * @param readervc the buffered reader variable
     * @throws IOException if operations find an error
     */
    private void wordGetter(String[] listOfWords, ArrayList<String> listofTrialWords, BufferedReader readervc) throws IOException {
        for(int i=0;i<279496;i++){
            while(readervc.ready()){ // while the next line is ready to be read
                listOfWords[i] = readervc.readLine(); // adds all the words to listofWords
                if(listOfWords[i].length() <= 7){ // if the word is leess than 7 characters
                    listofTrialWords.add(listOfWords[i]); //add to  list of trial words
                }
                
            }
        }
    }

    /**
     * Dtermines the score for the total words
     * @return an array of the total scores for each word
     * @throws IOException
     */
    public int[] getScores() throws IOException{
        // arrays for the alphabet and scores
        char[] alp = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; 
        int[] scores = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
        int[] wordScore = new int[getWords().size()];//size of the strList from getWords
        String temp;int score =0;
        
        for(int i =0;i<getWords().size();i++){ // looping through the getWords return
            temp = getWords().get(i); // temp is the string the loop is at
            for(int x =0;x<temp.length();x++){ // looping through the string
                for(int j = 0; j < 26; j++){ // looping through the alphabet
                    if(temp.charAt(x) == alp[j]){ // if the letters are equal
                        score += scores[j]; // add to score
                        break; // break the loop
                    }
                }
            }
            wordScore[i] = score; //puts the total score for the string at i
            score = 0; // set score to 0
        }
        Arrays.sort(wordScore); // sorting it to make it ascending order
        
        return wordScore;

    }

    /**
     * check if two objects are equal
     * @param saar takes in a scrabble type object
     * @return a true or false
     */
    public Boolean equals(Scrabble saar){
        int counter = 0; // set counter to 0
        char[] charArr = getLetters().toCharArray();// gets the 7 tiles and stores it in a char array
        char[] otherCharArr = saar.getLetters().toCharArray(); 
        Boolean isTrue;

        //sorts both arrays
        Arrays.sort(charArr);
        Arrays.sort(otherCharArr);
        
        for(int i = 0; i<7;i++){
            if(charArr[i] == otherCharArr[i]){ // compares both objects
                counter++; // add to counter
            }
        }
        if(counter == 7){
            isTrue = true; // set to true
        }else{
            isTrue = false; //set to false
        }

        return isTrue;
    }


    
}

