public class e2020_stack
{
    public static void main( String[] args )
    {
        LinkStack stack = new LinkStack();
        stack.push( 120 );
        stack.push( 121 );
        stack.push( 13 );
        stack.push( 14 );
        stack.push( 15 );
        stack.push( 16 );
        stack.print();

        stack.getAver();
    }
}

class LinkStack
{
    Node top;

    public LinkStack()
    {
        top = null;
    }

    public void push( int x )
    {
        if (top == null)
        {
            top =  new Node( x, x, 0, 0 );
        }

        else
        {
            Node newNode = new Node( x, x, top.elementsCounter, top.sum );
            if (x > top.minValue)
                newNode.minValue = top.minValue;

            newNode.next = top;
            top = newNode;
        }
    }

    public void pop()
    {
        if (top != null)
            top = top.next;
    }

    public void getMin()
    {
        System.out.println( top.minValue );
    }

    public void top()
    {
        System.out.println( top.value );
    }

    public void getAver()
    {
        System.out.println( top.sum / top.elementsCounter );
    }

    public void print()
    {
        Node current = top;
        while (current != null)
        {
            System.out.println( current.value + " " + current.minValue + " " + current.sum + " " + current.elementsCounter );
            current = current.next;
        }
    }
}

class Node
{
    Node next;
    int value;
    int minValue;
    int elementsCounter;
    int sum;

    public Node( int value, int minVal, int counter, int topSum )
    {
        this.value = value;
        this.minValue = minVal;
        this.elementsCounter = counter + 1;
        sum = topSum + value;
    }
}