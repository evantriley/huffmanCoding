package huffmanlab;

import java.io.*;

public class Huffman 
{
    
    public PriorityQueue PQ;
    public character newcharacter, P, Q, R;
    public int totalcharacters;
    public String localbitvalue, localcharacter, tempcharacter = "null";
    
    public void initializehuffman()
    {
        PQ = new PriorityQueue();
        PQ.initialize();
        
    }
    
    public void add(String characterin)
    {
        if(PQ.search(characterin) == true)
        {
            PQ.update(characterin);
        }
        else
        {
            newcharacter = new character();
            newcharacter.character = characterin;
            PQ.push(newcharacter);
            totalcharacters++;
        }
    }
    
    public void printhuffman()
    {
        PQ.printqueue();
    }
    
    public character codetree()
    {
        for(int j=1; j<totalcharacters; j++)
        {
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
    
    public void assignbitvalue(character characterin, String bitvalue)
    {
        if(characterin.leftchild == null && characterin.rightchild == null)
        {
            characterin.setbitvalue(bitvalue);
        }
        else
        {
            assignbitvalue(characterin.leftchild, bitvalue + "0");
            assignbitvalue(characterin.rightchild, bitvalue + "1");
        }
    }
    
    public void compress(character rootnode, String characterin)
    {
       if(rootnode.leftchild == null && rootnode.rightchild == null)
        {
            if(rootnode.character.equals(characterin))
            {
                localbitvalue = rootnode.bitvalue;
            }
        }
        else
        {
            compress(rootnode.leftchild, characterin);
            compress(rootnode.rightchild, characterin);
        }
    }
    
    public String returnbitvalue()
    {
        return localbitvalue;
    }
    
    public character decompress(character rootnode, String characterin)
    {
        localcharacter = "null";
        if (characterin.equals("0"))
        {
            if(rootnode.leftchild.leftchild == null && rootnode.leftchild.rightchild == null)
            {
                localcharacter = rootnode.leftchild.returncharacter();
            }
            return rootnode.leftchild;
        }
        else
        {
            if(rootnode.rightchild.leftchild == null && rootnode.rightchild.rightchild == null)
            {
                localcharacter = rootnode.rightchild.returncharacter();
            }
            return rootnode.rightchild;
        }
    }
    
    public String returncharacter()
    {
        return localcharacter;
    }
}
