package huffmanlab;

import java.io.*;

public class Huffman 
{
    
    public PriorityQueue PQ;
    public character newcharacter, P, Q, R;
    public int totalcharacters;
    public String localbitvalue, localcharacter, tempcharacter = "null";
    
    // setup of the priority queue which will hold the characters
    public void initializehuffman() {
        PQ = new PriorityQueue();
        PQ.initialize();        
    }
    
    //  If the read character is already in the priority queue, its frequency of appearance is updated.
    //  Otherwise, the new character is pushed into the queue.
    public void add(String characterin) {
        if(PQ.search(characterin) == true) {
            PQ.update(characterin);
        }
        else {
            newcharacter = new character();
            newcharacter.character = characterin;
            PQ.push(newcharacter);
            totalcharacters++;
        }
    }
    
    //  Prints out each character and their frequency, used for error checking.
    public void printhuffman() {
        PQ.printqueue();
    }
    
    //  Constructs the huffman tree, by popping off the first two characters (reffered to as P & Q) stored in the priority queue
    //  A new character (R) is created with P & Q as it's children.  It's frequency is equal to the sum of it's children's frequencys.
    //  R is then pushed back into the queue.
    //  When all characters from the priority queue have been added to the tree, it is returned.
    public character codetree() {
        for(int j=1; j<totalcharacters; j++) {
            P = PQ.pop();
            Q = PQ.pop();
            R = new character();
            R.leftchild = P;
            R.rightchild = Q;
            R.frequency = (P.returnfrequency() + Q.returnfrequency());
            PQ.push(R);
        }
        P = PQ.pop();
        return(P);
    }
    
    //  Given a root character, and a bit value
    //  If the given character has no children, it's bit value is set to the given bit value.
    //  Otherwise, assign the character's left child the given bit value, with a 0 appended, and it's right child the bit value with a 1 appended.
    public void assignbitvalue(character characterin, String bitvalue) {
        if(characterin.leftchild == null && characterin.rightchild == null) {
            characterin.setbitvalue(bitvalue);
        }
        else {
            assignbitvalue(characterin.leftchild, bitvalue + "0");
            assignbitvalue(characterin.rightchild, bitvalue + "1");
        }
    }
    
    //  Given a character and an ASCII character,
    //  if the character has no children nodes, then the local bit value is set to that of the node's.
    //  If the character does have children, then recursivly call compress on each of the children until no children are found.
    public void compress(character rootnode, String characterin) {
       if(rootnode.leftchild == null && rootnode.rightchild == null) {
            if(rootnode.character.equals(characterin)) {
                localbitvalue = rootnode.bitvalue;
            }
        }
        else {
            compress(rootnode.leftchild, characterin);
            compress(rootnode.rightchild, characterin);
        }
    }
    
    //  Returns the binary value assigned to a character in the tree.
    public String returnbitvalue() {
        return localbitvalue;
    }
    
    //  Given the a node of the huffman tree and a binary value, return the corresponding ASCII character
    //  If the binary value is 0, check to see if the node has no children, if so return the node's stored character
    //    Otherwise, move to given node's left child.
    //  If the binary value is 1, check to see if the node has no children, if so return the node's stored character
    //    Otherwise, move to given node's right child
    public character decompress(character rootnode, String characterin) {
        localcharacter = "null";
        if (characterin.equals("0")) {
            if(rootnode.leftchild.leftchild == null && rootnode.leftchild.rightchild == null) {
                localcharacter = rootnode.leftchild.returncharacter();
            }
            return rootnode.leftchild;
        }
        else {
            if(rootnode.rightchild.leftchild == null && rootnode.rightchild.rightchild == null) {
                localcharacter = rootnode.rightchild.returncharacter();
            }
            return rootnode.rightchild;
        }
    }
    
    //  Return a node's stored ASCII character.
    public String returncharacter() {
        return localcharacter;
    }
}
