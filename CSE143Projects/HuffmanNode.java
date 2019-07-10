/* Created by Leslie Mares on 07-05-19

 */
public class HuffmanNode implements Comparable<HuffmanNode>{
    public int frequency;
    public int asciiCharacter;
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(int frequency, int asciiCharacter){
        this(frequency, asciiCharacter, null, null);
    }

    public HuffmanNode(int frequency, int asciiCharacter, HuffmanNode left, HuffmanNode right){
        this.frequency = frequency;
        this.asciiCharacter = asciiCharacter;
        this.left = left;
        this.right = right;
    }

    public int compareTo(HuffmanNode other){
        return this.frequency - other.frequency;
    }

    public String toString(){
        return "" + asciiCharacter + " " + frequency;
    }
}
