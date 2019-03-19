/* Written by Leslie Mares on 3-17-19 in response to following prompt
from Building Java Programs: A Back to Basics Approach, 3rd Ed.:

Chapter 7 Programming Project and CSE HW:
https://courses.cs.washington.edu/courses/cse142/18au/homework/7/assign7.pdf
Write a program that reads a file of DNA data and searches for protein sequences.
DNA data consists of long Strings of the letters A, C, G, and T, corresponding to
chemical nucleotides called adenine, cytosine, guanine, and thymine. Proteins can
be identified by looking for special triplet sequences of nucleotides that indicate
the start and stop of a protein range. Store relevant data in arrays as you make
your computation. See our textbook's website for example DNA input file and
more details about heuristics for identifying proteins.
 */
import java.util.*;
import java.io.*;
public class DNA {
    public static final int MIN_N_CODONS= 5;
    public static final int C_AND_G_PERCENT = 30;
    public static final int UNIQUE_NUCLEOTIDES = 4;
    public static final int NUCLEOTIDES_PER_CODON = 3;

    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("This program reports information about DNA");
        System.out.println("nucleotide sequences that may encode proteins.");
        Scanner console = new Scanner(System.in);

        System.out.print("Input file name: ");
        Scanner input = new Scanner(new File(console.nextLine()));

        System.out.print("Output file name: ");
        PrintStream output = new PrintStream(new File(console.nextLine()));

        while(input.hasNextLine()){
            String regionName = input.nextLine();
            String sequence = input.nextLine().toUpperCase();
            int[] count = makeNucCount(sequence);
            int junk = isJunk(sequence, count);

            double[] massForEach = calculateMass(sequence, count);
            double totalMass = findTotalMass(massForEach, junk);
            findPercentMass(massForEach, totalMass);

            String[] codons = makeCodons(sequence);
            boolean protein = isProtein(massForEach, codons);
            printAll(output, regionName, sequence, count, massForEach, totalMass, codons, protein);
        }
    }

    //reads a String of the nucleotide sequences and returns an array of nucleotide counts
    public static int[] makeNucCount(String sequence){
        int[] count = new int[UNIQUE_NUCLEOTIDES];
        String nucleotides = "ACGT";
        for(int i = 0; i < sequence.length(); i ++){
            int index = nucleotides.indexOf(sequence.charAt(i));
            if(index >= 0){
                count[index]++;
            }
        }
        return count;
    }

    //calculates and returns an array of mass for each type of nucleotide
    public static double[] calculateMass(String sequence, int[] count){
        double[] nucWeight = {135.128, 111.103, 151.128, 125.107};
        double[] massForEach = new double[UNIQUE_NUCLEOTIDES];
        for(int i = 0; i < count.length; i++){
            massForEach[i] = count[i] * nucWeight[i];
        }
        return massForEach;
    }

    //calculates and returns how many "junk" occurences are in the DNA sequence
    public static int isJunk(String sequence, int[] count){
        int junk = sequence.length();
        for(int x: count){
            junk -= x;
        }
        return junk;
    }

    //calculates and returns total mass for entire DNA sequence
    public static double findTotalMass(double[] mass, int junk){
        double totalMass = junk * 100.00;
        for(double x: mass){
            totalMass += x;
        }
        return totalMass;
    }

    //calculate percent of mass for each nucleotide in the DNA sequence
    public static void findPercentMass(double[] massForEach, double totalMass){
        for(int i = 0; i < massForEach.length; i++){
            massForEach[i] = massForEach[i] / totalMass * 100;
        }
    }

    //creates and returns an array containing all the codons in the DNA sequence
    public static String[] makeCodons(String sequence){
        String modifiedSequence = removeJunk(sequence);
        String[] codons = new String[modifiedSequence.length() / NUCLEOTIDES_PER_CODON];
        for(int i = 0; i < codons.length; i ++){
            int index = i * NUCLEOTIDES_PER_CODON;
            codons[i] = modifiedSequence.substring(index, index + NUCLEOTIDES_PER_CODON);
        }
        return codons;
    }

    //modifies and returns the DNA sequence with no "junk" spaces
    public static String removeJunk(String sequence){
        String modifiedSequence = sequence;
        int findJunk = modifiedSequence.indexOf('-');
        while (findJunk >= 0){
            modifiedSequence = modifiedSequence.substring(0, findJunk)
                    + modifiedSequence.substring(findJunk + 1);
            findJunk = modifiedSequence.indexOf('-');
        }
        return modifiedSequence;
    }

    //evaluates whether the sequence codes for a protein and returns a boolean value
    public static boolean isProtein(double[] mass, String[] codons){
        int lastCodon = codons.length - 1;
        boolean hasStop = codons[lastCodon].equals("TAA") || codons[lastCodon].equals("TAG")
                || codons[lastCodon].equals("TGA");
        double cAndGPercentSum = mass[1] + mass[2];
        return (codons.length >= MIN_N_CODONS) && codons[0].equals("ATG") &&
                (hasStop) && (cAndGPercentSum >= C_AND_G_PERCENT);
    }

    //prints name of region, the sequence of DNA, counts, percent of mass, codons, and whether
    //sequence codes for a protein to an output file
    public static void printAll(PrintStream output, String regionName, String sequence,
                                int[] count, double[] mass, double totalMass,
                                String[] codons, boolean protein){
        output.println("Region Name: " + regionName);
        output.println("Nucleotides: " + sequence);
        output.println("Nuc. Counts: " + Arrays.toString(count));
        output.printf("Total Mass%%: [%.1f, %.1f, %.1f, %.1f] of %.1f\n",
                mass[0], mass[1], mass[2], mass[3], totalMass);
        output.println("Codons List: " + Arrays.toString(codons));
        output.print("Is Protein?: ");
        if(protein){
            output.println("YES");
        } else {
            output.println("NO");
        }
        output.println();
    }
}
