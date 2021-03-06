/* Created by Leslie Mares on 5-28-19
GuitarString models a string of a given fundamental frequency;
can simulate plucking the string and the resulting vibrations by
using the Karplus-Strong algorithm.

CSE 143 Hw assignment #2 GuitarHero
https://courses.cs.washington.edu/courses/cse143/19sp/homework.shtml
*/

import java.util.*;
public class GuitarString {
    //models dissipation of energy in wave
    private static final double ENERGY_DECAY_FACTOR = 0.996;
    //displacement values of string at N points in time (between -0.5 and 0.5 exclusive)
    private Queue<Double> ringBuffer;
    //used to simulate plucking of string
    private Random r = new Random();

    //pre: passed frequency is greater than zero and size of ring buffer is
    //     greater than one; otherwise will throw an IllegalArgumentException
    //post:constructs a string of given frequency (at rest)
    //     creates a ring buffer of size N(sampling rate / frequency)
    public GuitarString(double frequency){
        if(frequency <= 0){
            throw new IllegalArgumentException("Frequency must be greater" +
                    " than zero/nFrequency = " + frequency);
        }
        int N = (int) Math.rint(StdAudio.SAMPLE_RATE / frequency);
        ringBuffer = createRingBuffer(new double[N], N);
    }

    //pre: passed an array of displacement values for the string
    //     this array has more than one value, otherwise an
    //     IllegalArgumentException is thrown
    //post:constructs a string modeled by the given displacement
    //     values (stored as the ring buffer)
    public GuitarString(double[] init){
        int N = init.length;
        ringBuffer = createRingBuffer(init, N);
    }

    //pre: an array of displacement values and a value for N is passed;
    //     N must be two or greater otherwise an IllegalArgumentException is thrown
    //post:a ring buffer is created and returned, it contains all the displacement
    //     values passed
    private Queue<Double> createRingBuffer(double[] init, int N){
        if(N < 2){
            throw new IllegalArgumentException("N must be two or greater" +
                    "\nN = " + N);
        }
        Queue<Double> ring = new LinkedList<Double>();
        for(double displacement: init){
            ring.add(displacement);
        }
        return ring;
    }

    //post: all values in the ring buffer are replaced with randomly generated
    //      displacement values (between -0.5 and 0.5 exclusive)
    public void pluck(){
        int N = ringBuffer.size();
        for(int i = 0; i < N; i++){
            ringBuffer.remove();
            ringBuffer.add(r.nextDouble() - 0.5);
        }
    }

    //post: simulates vibration of string simulated by performing one step of
    //      the Karplus-Strong algorithm- the first sample of the ring buffer
    //      is deleted and adds to the end of the ring buffer the average of
    //      the average first two samples scaled by the energy decay factor;
    //      size of buffer stays the same
    public void tic(){
        double avgFirstTwo = (ringBuffer.remove() + ringBuffer.peek()) * 0.5;
        ringBuffer.add(ENERGY_DECAY_FACTOR * avgFirstTwo);
    }


    //post: returns the fist sample displacement value in the ring buffer
    public double sample(){
        return ringBuffer.peek();
    }

}

