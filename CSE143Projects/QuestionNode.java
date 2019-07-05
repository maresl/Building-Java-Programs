/* Created by Leslie Mares on 07-02-19 in response to CSE 143 HW 7
https://courses.cs.washington.edu/courses/cse143/19sp/handouts/20.html

QuestionNode is used to construct binary tree where each node represents
a question or answer. An answer node is a leaf whereas a question node
has a right and a left subtree.
 */
public class QuestionNode {
    public String data;
    public QuestionNode left;
    public QuestionNode right;

    //pre: takes in an answer
    //post: constructs a new answer leaf with the stored data
    public QuestionNode(String data){
        this(data, null, null);
    }

    //pre: is passed a question, and two question nodes
    //post: creates a new question branch with a right and left node references
    public QuestionNode(String data, QuestionNode left, QuestionNode right){
        this.data = data;
        this.left = left;
        this.right = right;
    }

}
