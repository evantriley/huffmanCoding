package huffmanlab;

public class PriorityQueue 
{
    public character[] priorityqueue;
    public int front, back;
    
    public void initialize()
    {
        priorityqueue = new character[100];
        front = 0;
        back = 0;
    }
    
    public void push(character characterin)
    {
        priorityqueue[back] = characterin;
        back++;
        rearrange();
    }
    
    public character pop()
    {
        return priorityqueue[front++];
    }
    
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
    
    public character peek()
    {
        return priorityqueue[front];
    }
    
    public boolean empty()
    {
        if(front == back)
            return true;
        else
            return false;
    }
    
    public void printqueue()
    {
        System.out.println("Front");
        for(int p = front; p<back; p++)
        {
            System.out.println("Character: [" + priorityqueue[p].returncharacter() + "] Frequency: [" + priorityqueue[p].returnfrequency() + "]");
        }
        System.out.println("Back");
    }
    
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

