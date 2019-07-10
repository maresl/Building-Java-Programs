/* Created by Leslie Mares on 07-05-19 in response to CSE 143 HW 8
https://courses.cs.washington.edu/courses/cse143/19sp/handouts/21.html

 */

import java.io.*;
import java.util.*;
public class HuffmanTree {
    public static final int CHAR_PLACEHOLDER = -1;
    public static final int FREQUENCY_PLACEHOLDER = 0;
    HuffmanNode treeRoot;

    public HuffmanTree(int[] count){
        Queue<HuffmanNode> leaves = new PriorityQueue<>();
        for(int i = 0; i < count.length; i++){
            if(count[i] > 0) {
                HuffmanNode leaf = new HuffmanNode(count[i], i);
                leaves.add(leaf);
                System.out.println(leaf);
            }
        }
        //end-of-file placeholder added
        leaves.add(new HuffmanNode(1, count.length));


        while(leaves.size() != 1){
            HuffmanNode left = leaves.remove();
            HuffmanNode right = leaves.remove();
            HuffmanNode consolidate = new HuffmanNode(left.frequency + right.frequency, CHAR_PLACEHOLDER, left, right);
            leaves.add(consolidate);
        }
        treeRoot = leaves.remove();
    }

    public HuffmanTree(Scanner input){
        while(input.hasNextLine()){
            int asciiChar = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            treeRoot = addToTree(treeRoot, asciiChar, code);
        }
    }

    private HuffmanNode addToTree(HuffmanNode node, int asciiChar, String code){
        if(code.equals("")){
            node = new HuffmanNode(FREQUENCY_PLACEHOLDER, asciiChar);
        } else {
            if (node == null) {
                node = new HuffmanNode(FREQUENCY_PLACEHOLDER, CHAR_PLACEHOLDER);
            }

            if (code.startsWith("0")) {
                node.left = addToTree(node.left, asciiChar, code.substring(1));
            } else {
                node.right = addToTree(node.right, asciiChar, code.substring(1));
            }
        }
        return node;
    }

    public void decode(BitInputStream input, PrintStream output, int eof){
        int code = input.readBit();
        while(code != eof){
            decode(treeRoot, output, Integer.toString(code));
            code = input.readBit();
        }
    }

    private void decode(HuffmanNode node, PrintStream output, String code){
        if(code.equals("")){
            output.write(node.asciiCharacter);
        } else if(code.startsWith("0")){
            decode(node.left, output, code.substring(1));
        } else {
            decode(node.right, output, code.substring(1));
        }
    }


    public void write(PrintStream output){
        writeCode(output, treeRoot, "");
    }

    private void writeCode(PrintStream output, HuffmanNode node, String code){
        if(node.right == null && node.left == null){
            output.println(node.asciiCharacter);
            output.println(code);
        } else {
            writeCode(output, node.left, code + "0");
            writeCode(output, node.right, code + "1");
        }
    }


}
