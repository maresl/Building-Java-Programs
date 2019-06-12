/* Created by Leslie Mares on 5-29-19

 */
import java.util.*;
public class Guitar37 implements Guitar {

    public static final String KEYBOARD =
            "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    //number of strings mapped to characters
    private static final int NUM_OF_STRINGS = KEYBOARD.length();
    //reference to concert A within the array
    private static final int INDEX_OF_CONCERT_A = 24;
    //contains all strings of this guitar object
    private GuitarString[] strings;
    //tracks the current time in terms of number of tics forward
    private int time;

    //post: creates guitar instrument with 37 strings with notes on the chromatic scale
    //      and frequencies between 110Hz and 880Hz
    public Guitar37(){
        strings = new GuitarString[NUM_OF_STRINGS];
        for(int i = 0; i < NUM_OF_STRINGS; i++){
            double exponent = (i - INDEX_OF_CONCERT_A) /
                    (NUM_OF_STRINGS - INDEX_OF_CONCERT_A - 1.0);
            double frequency = 440 * Math.pow(2, exponent);
            strings[i] = new GuitarString(frequency);
        }
    }

    //pre: a pitch is passed as a parameter (0 represents concert A)
    //post: string of corresponding pitch value is played;
    //      ignores pitches that cannot be played
    public void playNote(int pitch){
        int index = pitch + INDEX_OF_CONCERT_A;
        if(index >= 0 && index < NUM_OF_STRINGS){
            strings[index].pluck();
        }
    }

    //pre: a character is passed
    //post: returns whether the character is mapped to a string
    public boolean hasString(char key){
        return KEYBOARD.indexOf(key) > -1;
    }

    //pre: a character is passed as a parameter
    //post: string corresponding to the passed character is played
    //      otherwise throws an IllegalArgumentException
    public void pluck(char key){
        if(!hasString(key)){
            throw new IllegalArgumentException("Key is not mapped to a note");
        }
        int index = KEYBOARD.indexOf(key);
        strings[index].pluck();
    }

    //post: returns a double representing the sound sample
    //      (sum of the samples of the entire guitar object)
    public double sample(){
        double sampleSum = 0.0;
        for(GuitarString g : strings){
            sampleSum += g.sample();
        }
        return sampleSum;
    }

    //post: advances all strings in guitar and time by one tic
    public void tic(){
        time++;
        for(GuitarString g : strings){
            g.tic();
        }
    }

    //post: returns the time in terms of number of tics
    public int time(){
        return time;
    }

}
