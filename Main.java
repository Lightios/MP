package com.company;

public class Main
{
    public static int[] fibonacciValues;

    public static void main(String[] args)
    {
    	System.out.println(fibonacciRec(10, true));
    	System.out.println(fibonacciIter(6));
    }

    public static void initValues()
    {
        for (int i = 2; i < fibonacciValues.length; i++)
            fibonacciValues[i] = -1;

        fibonacciValues[0] = 0;
        fibonacciValues[1] = 1;
    }

    public static int fibonacciRec(int n, boolean firstUse)
    {
        if (firstUse)
        {
            fibonacciValues = new int[n + 1];
            initValues();
        }

        int value = fibonacciValues[n];
        if (value != -1)
            return value;

        value = fibonacciRec(n - 1, false) + fibonacciRec(n - 2, false);
        fibonacciValues[n] = value;
        return value;
    }

    public static int fibonacciIter(int n)
    {
        int one = 0;
        int two = 1;

        for (int i = 0; i < n; i++)
        {
            if (i % 2 == 0)
            {
                one = one + two;
            }
            else
            {
                two = one + two;
            }
        }

        if (n % 2 == 0)
        {
            return one;
        }
        else
        {
            return two;
        }
    }
}
