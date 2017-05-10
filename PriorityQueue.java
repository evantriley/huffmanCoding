package huffmanlab;

public class PriorityQueue 
{
    public character[] priorityqueue;
    public int front, back;
    
    //  Creates an array large enough to hold all possible ASCII characters, creates front and back pointer values.
    public void initialize() {
        priorityqueue = new character[256];
        front = 0;
        back = 0;
    }
    
    //  Given a character, pushes the character to the back of the queue.
    //    The back pointer is incremented, and the queue is then checked to make sure that all characters are in descending order of frequency.
    public void push(character characterin) {
        priorityqueue[back] = characterin;
        back++;
        rearrange();
    }
    
    //  Returns the character at the front of the queue. Increments front pointer.
    public character pop() {
        return priorityqueue[front++];
    }
    
    //  If the value added at the back of the queue, is less than the value in front of it.  Swap the two characters.
    public void rearrange()
    {
        for(int check = back-1; check>front; check--)
        {
            character temp = priorityqueue[check];
            if(temp.returnfrequency()<priorityqueue[check-1].returnfrequency())
            {
                priorityqueue[check] = priorityqueue[check-1];
                priorityqueue[check-1] = temp;
            }
        }
    }
    
    //  Given a character, check to see if any characters with the same stored ASCII character are already in the queue.
    //    Used for checking if a new character needs to be pushed on the stack, or if a character's frequency needs to be updated.
    public boolean search(String characterin)
    {
        for(int find= back-1; find>=front; find--)
        {
            if(priorityqueue[find].returncharacter().equals(characterin))
            {
                return true;
            }
        }
        return false;
    }
    
    //  Return the character at the front of the queue.
    public character peek()
    {
        return priorityqueue[front];
    }
    
    //  Checks if the queue is empty.
    public boolean empty()
    {
        if(front == back)
            return true;
        else
            return false;
    }
    
    //  Prints out the contents of the queue (each character and their frequency).
    //    Used for error checking.
    public void printqueue()
    {
        System.out.println("Front");
        for(int p = front; p<back; p++)
        {
            System.out.println("Character: [" + priorityqueue[p].returncharacter() + "] Frequency: [" + priorityqueue[p].returnfrequency() + "]");
        }
        System.out.println("Back");
    }
    
    //  Increments a character's stored value for its frequency.
    public void update(String characterin)
    {
        for(int q = back-1; q>=front; q--)
        {
            if(priorityqueue[q].returncharacter().equals(characterin))
            {
                priorityqueue[q].updatefrequency();
            }
        }
        rearrange();
    }
}

