
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.ArrayList;

public class EmailMapper
{
    private TreeMap<String,ArrayList<String>> emailWords = new TreeMap<String,ArrayList<String>>(); //holds words of messges, one key holds all messages for
    //each user

    public EmailMapper(){
        wordsInFile("Gobbledegook.txt");//load files of noisewords and email messages
        HashSet<String> noiseWords = loadNoiseWords("NoiseWords.txt");
        int wordFrequency;
        for (Map.Entry<String, ArrayList<String>> entry : emailWords.entrySet()) {
            //loop through emailWords map, create new list of word objects for each email, compare the objects and print top 10 or length if list.size < 10
            ArrayList<Word> words = new ArrayList<Word>();//create ArrayList to hold word objects
            ArrayList<String> emailWordList = new ArrayList<String>();//create ArrayList to hold words from each user's messages
            emailWordList = entry.getValue();//get list of email words for an individual user
            HashSet<String> h = new HashSet<String>(emailWordList);//create hashset of words so as to remove duplicates
            String email = entry.getKey();//get email address of the user
            for(String s : h){//loop through HashSet of email words, creating word object for each word
                if(!noiseWords.contains(s.toLowerCase())){//convert word to lower case for comparrison, add to list if not in noiseWords
                    wordFrequency = Collections.frequency(emailWordList, s);//get count of how many times the word occurs in the user's messages
                    words.add(new Word(s,wordFrequency));//create word object and add to list
                }
            }
            Collections.sort(words);//sort the list of objects
            System.out.print("{"+email +" -> ");
            int count=0;
            for(Word word: words){//print top 10 words, or all words if list length is < 10.
                if(count < 9 && count < words.size()-1){
                    System.out.print(word.toString() + ", ");
                    count++;
                }
                else{
                    System.out.println(word.toString() + "}");//print closing bracket and start new line
                    break;
                }
            }
        }
    }

    private void addToEmailWordsMap(ArrayList<String> wordsInList, String email){
        this.emailWords=emailWords;
        ArrayList<String> words;
        if(emailWords.get(email) != null){//checks if the map already holds messges the email address and adds to existing list if a matching email exists.
            words = emailWords.get(email);//get existing list
            words.addAll(wordsInList);//add new list to the existing list
            emailWords.put(email, words);//put back into map, automatically deleting the original list entry
        }
        else{
            emailWords.put(email, wordsInList);//if email isn't already in map, put entry directly into map
        }
    }

    private void wordsInFile(String emailFilename){
        try{
            FileReader aFileReader = new FileReader(emailFilename);
            BufferedReader aBufferedReader = new BufferedReader(aFileReader);
            String email;
            ArrayList<String> wordList;
            String lineFromFile = aBufferedReader.readLine();//attempt to read a first line from the file
            while(lineFromFile != null){
                wordList= new ArrayList<String>();
                email = lineFromFile.substring(0, lineFromFile.indexOf("@"));
                lineFromFile = lineFromFile.substring(lineFromFile.indexOf(" "), lineFromFile.length());//remove email from the String
                wordList.addAll(wordsInLine(lineFromFile)); //add to map at index of email address
                wordList.remove(0);//remove 'gPostBegin'
                while(!(lineFromFile.contains("GPostEnd"))){//keep looping until end of message
                    lineFromFile = aBufferedReader.readLine();
                    wordList.addAll(wordsInLine(lineFromFile));//add to map at index of email address
                }
                wordList.remove(wordList.size()-1);//remove 'gPostEnd'
                addToEmailWordsMap(wordList, email);//put words into map with their corresponding email- this method checks if email already in map
                //and adds the user's words to existing words if they have messages previously inserted to the map
                lineFromFile = aBufferedReader.readLine();//get next line
            }
            aBufferedReader.close();
            aFileReader.close();
        }
        catch(IOException x){
            System.out.println("unable to read file");
        }
    }

    private ArrayList<String> wordsInLine(String line){
        final String DELIMITERS = " !@#$%^&*()_-+={[}]:;\"'<,>.?/~`|\\" ;//characters to remove from text
        ArrayList<String> wordsInLine = new ArrayList<String>();
        StringTokenizer t = new StringTokenizer(line, DELIMITERS);//create tokenizer from specified String, removing all Delimiters.
        while (t.hasMoreTokens()){//loop through tokenizer for its entire length
            String word = t.nextToken();
            if(word.matches("[a-zA-Z]+")){//allow upper and lower case words
                wordsInLine.add(word);//add to list
            }
        }
        return wordsInLine;
    }

    private HashSet<String> loadNoiseWords(String filename) {//loads noiseWords file
        try {
            FileReader aFileReader = new FileReader(filename);
            BufferedReader aBufferReader = new BufferedReader(aFileReader);
            String lineFromFile;//new String variable to hold each line of words
            HashSet<String> words = new HashSet<String>();//List to hold the words
            lineFromFile = aBufferReader.readLine() ;//attempt to read first line from the file
            while (lineFromFile != null)
            {  
                words.add(lineFromFile.toLowerCase());//add line of words to list, converting to lower case
                lineFromFile = aBufferReader.readLine() ;//get next line of text
            }
            aBufferReader.close();
            aFileReader.close();
            return words ;
        }
        catch(IOException x)
        {
            return null ;
        }
    }
}