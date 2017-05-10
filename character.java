package huffmanlab;

public class character 
{
    public String character;
    public int frequency;
    public String bitvalue;
    public character rightchild;
    public character leftchild;
    
    public character()
    {
        frequency = 1;
    }
    
    public void setrightchild(character rightchildin)
    {
        rightchild = rightchildin;
    }
    
    public void setleftchild(character leftchildin)
    {
        leftchild = leftchildin;
    }
    
    public void updatefrequency()
    {
        frequency++;
    }
    
    public int returnfrequency()
    {
        return frequency;
    }
    
    public String returncharacter()
    {
        return character;
    }
    
    public void setbitvalue(String bitvaluein)
    {
        bitvalue = bitvaluein;
    }
}
