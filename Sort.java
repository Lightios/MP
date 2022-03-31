package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args)
    {
        Sort sort = new Sort();
        sort.randomizeArrays("binarySearchArrays");
        sort.bubbleSort("binarySearchArrays");
        System.out.println(sort.binarySearch(2, 0));
        System.out.println(sort.firstBinarySearch(2, 0));
        System.out.println(sort.lastBinarySearch(2, 0));
        System.out.println(sort.getQuantity(2, 0));


    }
}


class Sort
{
    int[][] bubbleSortArrays = new int[1][10];
    int[][] binarySearchArrays = new int[1][10];
    Random rand = new Random();

    public void randomizeArrays(String arraysName)
    {
        int[][] arrayToRandomize;

        if (arraysName.equals("bubbleSortArrays"))
            arrayToRandomize = bubbleSortArrays;
        else if (arraysName.equals("binarySearchArrays"))
            arrayToRandomize = binarySearchArrays;
        else return;

        for (int i = 0; i < bubbleSortArrays.length; i++)
        {
            for (int j = 0; j < bubbleSortArrays[i].length; j++)
            {
                arrayToRandomize[i][j] = rand.nextInt(10);
            }
        }
    }

    public void bubbleSort(String arraysName)
    {
        int[][] arrayToSort;

        if (arraysName.equals("bubbleSortArrays"))
            arrayToSort = bubbleSortArrays;
        else if (arraysName.equals("binarySearchArrays"))
            arrayToSort = binarySearchArrays;
        else return;


        for (int arrayIndex = 0; arrayIndex < arrayToSort.length; arrayIndex++)
        {
            System.out.println("Before sort: ");
            System.out.println("\t" + Arrays.toString(arrayToSort[arrayIndex]));

            for (int i = arrayToSort[arrayIndex].length - 1; i >= 1; i--)
            {
                boolean didSwap = false;

                for (int j = 0; j < i; j++)
                {
                    if (arrayToSort[arrayIndex][j] > arrayToSort[arrayIndex][j + 1])
                    {
                        int temp = arrayToSort[arrayIndex][j];
                        arrayToSort[arrayIndex][j] = arrayToSort[arrayIndex][j + 1];
                        arrayToSort[arrayIndex][j + 1] = temp;

                        didSwap = true;
                    }
                }

                if (!didSwap)
                {
                    break;
                }
            }

            System.out.println("After sort: ");
            System.out.println("\t" + Arrays.toString(arrayToSort[arrayIndex]) + "\n");
        }
    }

    public int binarySearch(int lookingFor, int arrayIndex)
    {
        int right = 0;
        int left = binarySearchArrays[arrayIndex].length;
        int current = 0;

        while (right <= left)
        {
            current = (right + left) / 2;

            if (binarySearchArrays[arrayIndex][current] == lookingFor)
                return current;

            else
            {
                if (binarySearchArrays[arrayIndex][current] < lookingFor)
                    right = current + 1;
                else
                    left = current - 1;
            }
        }

        return -1;
    }

    public int firstBinarySearch(int lookingFor, int arrayIndex)
    {
        int right = 0;
        int left = binarySearchArrays[arrayIndex].length;
        int current = 0;

        while (right <= left)
        {
            current = (right + left) / 2;

            if (binarySearchArrays[arrayIndex][current] == lookingFor)
            {
                if (current > 0)
                {
                    if (binarySearchArrays[arrayIndex][current - 1] != lookingFor)
                        return current;
                }
                else
                    return current;
            }


            if (binarySearchArrays[arrayIndex][current] < lookingFor)
                right = current + 1;
            else
                left = current - 1;
        }

        return -1;
    }

    public int lastBinarySearch(int lookingFor, int arrayIndex)
    {
        int right = 0;
        int left = binarySearchArrays[arrayIndex].length;
        int current = 0;

        while (right <= left)
        {
            current = (right + left) / 2;

            if (binarySearchArrays[arrayIndex][current] == lookingFor)
            {
                if (current < binarySearchArrays[arrayIndex].length - 1)
                {
                    if (binarySearchArrays[arrayIndex][current + 1] != lookingFor)
                        return current;
                }
                else
                    return current;
            }


            if (binarySearchArrays[arrayIndex][current] <= lookingFor)
                right = current + 1;
            else
                left = current - 1;
        }

        return -1;
    }

    public int getQuantity(int lookingFor, int arrayIndex)
    {
        int firstOutput = binarySearch(lookingFor, arrayIndex);
        if (firstOutput != -1)
            return lastBinarySearch(lookingFor, arrayIndex) - firstOutput + 1;
        else
            return  -1;
    }
}
