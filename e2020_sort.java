import java.util.Arrays;

public class e2020_sort
{
    public static Machine machine;
    public static int n;

    public static void main( String[] args )
    {
        int[] elements = { 3, 8, 4, 5, 1, 2 };
        machine = new Machine( elements );
        n = elements.length;

        pancakeSort();

        machine.print();
    }

    public static void move( int i, int j )
    {
        machine.flip( j );
        machine.flip( i - 1 );
        machine.flip( i );
        machine.flip( j );
    }

    public static void pancakeSort()
    {
        for (int a = 0; a < n; a++)
        {
            int maxiumum = machine.get( 0 );
            int index = 0;

            for (int i = 0; i < n - a; i++)
            {
                int current = machine.get( i );
                if (current > maxiumum)
                {
                    maxiumum = current;
                    index = i;
                }

            }

            machine.flip( index );
            machine.flip( n - a - 1 );
        }

    }

}

class Machine
{
    private int[] array;

    public Machine(int[] array)
    {
        this.array = array;
    }

    public void flip( int i )
    {
        for (int j = 0; j <= i / 2; j++)
        {
            int temp = array[ j ];
            array[ j ] = array[ i - j ];
            array[ i - j ] = temp;
        }
    }

    public int get( int i )
    {
        return array[ i ];
    }

    public void print()
    {
        System.out.println( Arrays.toString( array ) );
    }
}
