package huffmanlab;

public class Node 
{
    public String letter, bitvalue;
    
    public Node(String letterin, String bitvaluein)
    {
        letter = letterin;
        bitvalue = bitvaluein;
    }
    
    public String returnletter()
    {
        return letter;
    }
    
    public String returnbitvalue()
    {
        return bitvalue;
    }
}
