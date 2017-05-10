package huffmanlab;

import java.io.*;
import java.util.*;
import java.util.Scanner;

public class HuffmanLab 
{
    
    // Huffman Lab - By reading in a plain text file and tracking the appearance frequency of each characer,
    //    a huffman tree may be created.  Using this binary tree, each character will be assigned a bit value based
    //    on it's frequency in the file.  A new file will then be created, and each character will be replaced by its
    //    binary equivalent.  This compressed file will then be reread, and converted back to ASCII to check for parity.
    public static void main(String[] args) throws FileNotFoundException {
        character rootnode, topnode;
        Huffman huffman = new Huffman();
        // reads a text file titled "originalfile.txt"
        String filename = "originalfile.txt";
        File file = new File(filename);
        Scanner reader = new Scanner(file);
        huffman.initializehuffman();
        //  If the next line of the text file is not blank, it is read and then split over blank space into an array.
        //  Each character is then passed to the huffman method to either be pushed into the queue,
        //    or so that the characters frequency value may be updated.
        while (reader.hasNextLine())
        {
            String linein = (reader.nextLine());
            String [] linearray = linein.split("");
            for (int i=1; i<linearray.length; i++)
            {
                // creates a priority queue of the letters found from the text file
                huffman.add(linearray[i]);
            }
        }
        reader.close();
        // codetree - creates a binary tree from the priority queue previously created
        rootnode = huffman.codetree();
        // assignbitvalue - assigns a bit value to each character
        huffman.assignbitvalue(rootnode, "");
        System.out.println("Huffman tree created");
        // Compress - reads the original text file and creates a new compressed file titled "compressedfile.txt"
        PrintWriter out = new PrintWriter("compressedfile.txt");
        Scanner compressionreader = new Scanner(file);
        while (compressionreader.hasNextLine())
        {
            String linetobecompressed = (compressionreader.nextLine());
            String [] compressionarray = linetobecompressed.split("");
            for (int j=1; j<compressionarray.length; j++)
            {
                huffman.compress(rootnode, compressionarray[j]);
                out.print(huffman.returnbitvalue());
            }
            out.println();
        }
        compressionreader.close();
        out.close();
        System.out.println("compressed file created");
        // Uncompress - reads a compressed text file and creates a new uncompressed file titled "uncompressedfile.txt"
        PrintWriter decompressed = new PrintWriter("uncompressedfile.txt");
        filename = "compressedfile.txt";
        file = new File(filename);
        Scanner decompressionreader = new Scanner(file);
        topnode = rootnode;
        while (decompressionreader.hasNextLine())
        {
            String decompressedlinein = (decompressionreader.nextLine());
            String [] decompressedarray = decompressedlinein.split("");
            for (int k=1; k<decompressedarray.length; k++)
            {
               rootnode = huffman.decompress(rootnode, decompressedarray[k]);
               if(huffman.returncharacter().equals("null"))
               {
               }
               else
               {
                   decompressed.print(huffman.returncharacter());
                   rootnode = topnode;
               }
            }
            decompressed.println();
        }
        decompressionreader.close();
        decompressed.close();
        System.out.println("Uncompressed file created");
    }
}
