package com.company;

import java.util.Random;
import java.util.Arrays;

public class QuickSort
{
    static Random rand = new Random();
    public static void main(String[] args)
    {
        int[] testArray = {10, 2, 3, 1, 5, 11, 14};
        int[] testArrayTwo = {10, 2, 3, 1, 5, 11, 14};

        quickSort(testArray, 0, testArray.length - 1);
        probabilisticQuickSort(testArrayTwo, 0, testArray.length - 1);

        System.out.println(Arrays.toString(testArray));
        System.out.println(Arrays.toString(testArrayTwo));
    }


    private static void swap(int[] array, int i,  int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static int splitArray(int[] array, int begin, int end)
    {
        int pivot = array[begin];

        int i = begin - 1;
        int j = end + 1;

        while (true)
        {
            do
            {
                j -= 1;
            }
            while (array[j] > pivot);

            do
            {
                i += 1;
            }
            while (array[i] < pivot);

            if (i < j)
            {
               swap(array, i, j);
            }

            else
                return j;
        }
    }

    public static void quickSort(int[] array, int begin, int end)
    {
        if (begin < end)
        {
            int pivot = splitArray(array, begin, end);
            quickSort(array, begin, pivot);
            quickSort(array, pivot + 1, end);
        }
    }

    public static void probabilisticQuickSort(int[] array, int begin, int end)
    {
        if (begin < end)
        {
            int pivot = rand.nextInt(array.length);
            swap(array, pivot, begin);

            quickSort(array, begin, pivot);
            quickSort(array, pivot + 1, end);
        }
    }

    public static void probabilisticQuickSort2(int[] array, int begin, int end)
    {
        if (begin < end)
        {
            int pivot = rand.nextInt(array.length);
            swap(array, pivot, 0);

            quickSort(array, begin, pivot);
            quickSort(array, pivot + 1, end);
        }
    }
}