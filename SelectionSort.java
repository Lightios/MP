import java.util.Arrays;
import java.util.Random;

class SelectionSortClass
{
    public static void main(String[] args)
    {
        Random rand = new Random();

        int[] array = new int[10];
        for (int i = 0; i < 10; i++)
        {
            array[i] = rand.nextInt(10);
        }

        System.out.print("Before sort: ");
        System.out.println(Arrays.toString(array));

        SelectionSort(array);

        System.out.print("After sort: ");
        System.out.println(Arrays.toString(array));


        int[][] arrayPairs = new int[10][2];
        for (int i = 0; i < array.length; i++)
        {
            arrayPairs[i][0] = rand.nextInt(10);
            arrayPairs[i][1] = rand.nextInt(10);
        }

        System.out.print("Before sort: ");
        for (int i = 0; i < array.length; i++)
        {
            System.out.print("(");
            System.out.print(arrayPairs[i][0]);
            System.out.print(", ");
            System.out.print(arrayPairs[i][1]);
            System.out.print("), ");
        }
        System.out.println();

        SelectionSortPairs(arrayPairs);

        System.out.print("After sort: ");
        for (int i = 0; i < array.length; i++)
        {
            System.out.print("(");
            System.out.print(arrayPairs[i][0]);
            System.out.print(", ");
            System.out.print(arrayPairs[i][1]);
            System.out.print("), ");
        }
        System.out.println();

    }

    public static void SelectionSort(int[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            int minimum = i;
            for (int j = i + 1; j < array.length; j++)
            {
                if (array[j] < array[minimum])
                {
                    minimum = j;
                }
            }

            int temp = array[i];
            array[i] = array[minimum];
            array[minimum] = temp;

        }
    }

    public static void SelectionSortPairs(int[][] array)
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            int minimum = i;
            for (int j = i + 1; j < array.length; j++)
            {
                if (array[j][0] < array[minimum][0])
                {
                    minimum = j;
                }
            }

            int[] temp = array[i];
            array[i] = array[minimum];
            array[minimum] = temp;
        }
    }
}
