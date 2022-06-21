import java.util.Arrays;

public class e2020_heap
{
    public static void main( String[] args )
    {
        SortedList listOne = new SortedList();
        listOne.add(1);
        listOne.add(5);
        listOne.add(6);
        listOne.add(7);

        SortedList listTwo = new SortedList();
        listTwo.add(3);
        listTwo.add(4);
        listTwo.add(8);

        SortedList listThree = new SortedList();
        listThree.add(1);
        listThree.add(2);
        listThree.add(4);
        listThree.add(9);

        listOne.print();
        listTwo.print();
        listThree.print();

        SortedList[] lists = new SortedList[ 3 ];
        lists[ 0 ] = listOne;
        lists[ 1 ] = listTwo;
        lists[ 2 ] = listThree;


        Heap heap = new Heap( 11, lists );
    }
}

class Heap
{
    NodeL[] elements;
    int[] output;
    int n;
    int currentLength = 0;

    public Heap( int n, SortedList[] lists )
    {
        elements = new NodeL[ n ];
        output = new int[ n ];
        this.n = n;
        currentLength = 0;

        int i = 0;
        for (; i < lists.length; i++)
        {
            currentLength += 1;
            elements[ i ] = lists[ i ].first;
        }

        // build heap
        for (i = currentLength / 2; i >= 0; i--)
        {
            heapify( i );
        }

        i = 0;
        while (i < n)
        {
            output[ i ] = elements[ 0 ].value;

            elements[ 0 ] = elements[ 0 ].next;
            if (elements[ 0 ] == null)
            {
                elements[ 0 ] = new NodeL( Integer.MAX_VALUE );
            }

            heapify( 0 );
            i++;
        }

        System.out.println(Arrays.toString(output));
    }

    int parent( int index )
    {
        return (index - 1) / 2;
    }

    int left( int index )
    {
        return index * 2 + 1;
    }

    int right( int index )
    {
        return index * 2 + 2;
    }

    void swap( int a, int b )
    {
        NodeL temp = elements[ a ];
        elements[ a ] = elements[ b ];
        elements[ b ] = temp;
    }

    public void heapify( int index )
    {
        int min = elements[ index ].value;
        int minIndex = index;
        int leftChild = left( index );
        int rightChild = right( index );


        if ( leftChild < currentLength && elements[ leftChild ].value < min )
        {
            minIndex = leftChild;
            min = elements[ leftChild ].value;
        }

        if ( rightChild < currentLength && elements[ rightChild ].value < min )
        {
            minIndex = rightChild;
        }

        if (index != minIndex)
        {
            swap( index, minIndex );
            heapify( index );
        }

    }
}

class SortedList
{
    NodeL first;

    void add( int x )
    {
        if (first == null)
            first = new NodeL( x );

        else
        {
            NodeL current = first;
            while (current.next != null)
                current = current.next;

            current.next = new NodeL( x );
        }
    }

    void print()
    {
        NodeL current = first;
        while (current != null)
        {
            System.out.print( current.value + " " );
            current = current.next;
        }

        System.out.println();
    }
}

class NodeL
{
    int value;
    NodeL next;

    NodeL( int x )
    {
        value = x;
    }
}