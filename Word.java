
public class Word implements Comparable<Word>
{
    private String word;
    private Integer count;
    
    public Word(String word, Integer count){//constructor to add a word and count
        this.word=word;
        this.count= count;
    }
    
    public String getWord(){
        return this.word;
    }

    public int getCount(){
        return this.count;
    }
    //comparator compares based on count, and if count the same, alpabetically & case-insensitive
    public int compareTo(Word other){
        int compareVal =(int) (other.count- this.count);
        if(compareVal != 0){
            return compareVal;
        }
        return this.word.compareToIgnoreCase(other.word);
    }
    
    public String toString(){
        return "{"+ this.word+","+this.count+"}";
    }
    
}
