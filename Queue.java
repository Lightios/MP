class QueueFIFO
{
    private Node firstElement = null;

    public void offer(Node element)
    {
        if (firstElement == null)
        {
            firstElement = element;
        }
        else
        {
            Node currentElement = firstElement;

            while (currentElement.getNext() != null)
            {
                currentElement = currentElement.getNext();
            }

            currentElement.setNext(element);
        }
    }

    public Node poll()
    {
        Node temp = firstElement;

        firstElement = firstElement.getNext();

        return temp;
    }

    public Node peek()
    {
        return firstElement;
    }

    public boolean isEmpty()
    {
        return firstElement == null;
    }

    public int size()
    {
        int output = 0;

        Node currentElement = firstElement;

        while (currentElement != null)
        {
            currentElement = currentElement.getNext();
            output++;
        }

        return output;
    }

    @Override
    public String toString()
    {
        String output = "";

        Node currentElement = firstElement;

        while (currentElement != null)
        {
            output += currentElement.toString() + ", ";
            currentElement = currentElement.getNext();
        }

        return "QueueFIFO: " + output;
    }
}

class Node
{
    private Node next;
    private int value;

    public Node(int value)
    {
        this.value = value;
        next = null;
    }

    @Override
    public String toString()
    {
        return "Node{value=" + value + "}";
    }

    public Node getNext()
    {
        return next;
    }

    public void setNext(Node element)
    {
        next = element;
    }
}


class QueueLIFO
{
    private Node firstElement = null;

    public void offer(Node element)
    {
        if (firstElement == null)
        {
            firstElement = element;
        }
        else
        {
            Node currentElement = firstElement;

            firstElement = element;
            firstElement.setNext(currentElement);
        }
    }

    public Node poll()
    {
        Node temp = firstElement;

        firstElement = firstElement.getNext();

        return temp;
    }

    public Node peek()
    {
        return firstElement;
    }

    public boolean isEmpty()
    {
        return firstElement == null;
    }

    public int size()
    {
        int output = 0;

        Node currentElement = firstElement;

        while (currentElement != null)
        {
            currentElement = currentElement.getNext();
            output++;
        }

        return output;
    }

    @Override
    public String toString()
    {
        String output = "";

        Node currentElement = firstElement;

        while (currentElement != null)
        {
            output += currentElement.toString() + ", ";
            currentElement = currentElement.getNext();
        }

        return "QueueLIFO: " + output;
    }
}

class Main
{
    public static void main(String[] args)
    {
        QueueFIFO queue = new QueueFIFO();

        System.out.println(queue.isEmpty());

        Node firstNode = new Node(5);
        queue.offer(firstNode);

        Node secondNode = new Node(10);
        queue.offer(secondNode);

        System.out.println(queue.isEmpty());
        System.out.println(queue.size());

        System.out.println(queue);

        System.out.println(queue.poll());

        System.out.println(queue);
    }
}