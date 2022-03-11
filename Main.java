package com.company;
import java.util.Random;
import java.util.Scanner;


// Tablica jednowymiarowa
//==============================================

class Wektor{
    public int[] vec; // referencja do wektora
    public int maxSize; // maksymalna długość wektora
    public int currentSize; // aktualna długość wektora

    //============================================= konstruktor
    public Wektor (int maxSize_) {
        maxSize = maxSize_;
        currentSize = 0;
        vec = new int[maxSize];
    }

    //==================================== ustawienie wektora
    public void setVector(int[] vector) {
        if(maxSize < vector.length){
            this.vec = vector;
            // Nadpisuje wcześniejsze ustawienie maxSize.
            maxSize = vector.length;
        }
        else {
            System.arraycopy(vector, 0, this.vec, 0, vector.length);
        }
        // Nadpisuje wcześniejsze ustawienie currentSize dla obu powyższych przypadków.
        currentSize = vector.length;
    }

    //==================================== wczytywanie wektora
    public void readVector(int nrOfIntegers, Scanner sc) {
        for (int i = 0; i < nrOfIntegers; ) {
            if (sc.hasNextInt()) {
                vec[i] = sc.nextInt();
                ++i;
            } else {
                // Zbiera to, co zostało wpisane i nie było typu int.
                sc.nextLine();
            }
        }
    }

    //===================================== losowanie wektora
    /** Losowanie nrOfIntegers liczb z przedziału [min,max] do wektora vector.*/
    public void randVector(int nrOfIntegers, int min, int max) {
        System.out.println ("Losowanie "+nrOfIntegers+" liczb z przedzialu [" +min+","+max+"]");
        for( int i=0; i<nrOfIntegers; i++)
            // max+1, żeby przedział był domknięty z góry.
            vec[i] = new Random().nextInt(max+1-min)+min;
    }

    //===================================== wyświetlanie wektora
    public void display(){
        System.out.println("Liczba elementow = " + currentSize);
        System.out.println("Zawartosc wektora ");
        for (int i = 0; i<currentSize; i++) {
            System.out.print(vec[i]+", ");
            if((i+1)%10==0) System.out.println();
        }
        System.out.println();
    }

    public void display(int begin, int end){
        int size = end - begin + 1;
        if(size>currentSize) {
            System.out.println("Liczba elementów nie może być większa niż " + currentSize);
            return;
        }
        System.out.println("Liczba elementow = " + size);
        System.out.print("Indeks początku=" + begin + " Indeks końca=" + end +". ");
        System.out.println("Zawartość podwektora ");
        // i<=end mniejsze lub równe, ponieważ end jest indeksem.
        for (int i = begin; i<=end; i++) {
            System.out.print(vec[i]+", ");
            if((i-begin+1)%10==0) System.out.println();
        }
        System.out.println();
    }

    public void addAtEnd(int value)
    {
        if (currentSize < maxSize)
        {
            vec[currentSize] = value;
        }
        else
        {
            maxSize += 1;
            int[] vecTemp = new int[maxSize];
            System.arraycopy(vec, 0, vecTemp, 0, vec.length);

            this.vec = vecTemp;
        }

        currentSize += 1;
    }

    public void addAtEnd(int value, int percentOfIncrease)
    {
        // TODO: check if float
//        System.out.print(value * (1 + percentOfIncrease));
//        System.out.print(value * (1 + percentOfIncrease) * 1.0);
//
//        if (value * (1 + percentOfIncrease) == value * (1 + percentOfIncrease) * 1.0)
//        {
//            System.out.println("Inv");
//        }
        maxSize *= (1 + percentOfIncrease);

        int[] vecTemp = new int[maxSize];
        System.arraycopy(vec, 0, vecTemp, 0, vec.length);
        this.vec = vecTemp;

        addAtEnd(value);
    }

    void deleteElement(int value)
    {
        for (int i = 0; i < vec.length; i++)
        {
              if (vec[i] == value)
              {
                  
              }
        }
    }

}//End of class wektor
////////////////////////////////////////////////////////////
/**
 *     Klasa wektorApp, zawierająca metodę main(), pozwalającą na wybór i ilustrację
 *     działania podanych operacji na tablicy.
 */
class Main {
    /** Zapobiega błędom gdy nie int.*/
    public static int getInt(Scanner sc) {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        }
        // Zbiera to, co wpisane i nie typu int.
        sc.nextLine();
        return -1;
    }
    /** Ustawia i wyświetla elementy wektora (ręcznie, losowo, predefiniowany) */
    public static void setVectorElements(Scanner sc, Wektor wektor, int[] vec) {
        System.out.print("Podaj aktualną dlugosc wektora, "+" <= "+wektor.maxSize +": ");
        int currentSizeTmp = getInt(sc);
        while(currentSizeTmp<=0 || currentSizeTmp>wektor.maxSize) {
            System.out.println("Niepoprawna długość wektora");
            System.out.print("Podaj nowa dlugosc wektora: ");
            currentSizeTmp = getInt(sc);
        }
        wektor.currentSize = currentSizeTmp;
        System.out.println("Wybierz: 1-czytanie, 2-losowanie, 3-predefiniowany, inne - koniec");
        int choice = getInt(sc);

        switch(choice){
            case 1:
                System.out.println("Czytanie "+currentSizeTmp+" liczb integer");
                wektor.readVector(currentSizeTmp, sc );
                break;
            case 2:
                System.out.println("Losowanie "+currentSizeTmp+" liczb integer");
                System.out.print("Podaj minimum: ");
                int min = sc.nextInt();
                System.out.print("Podaj maksimum: ");
                int max = sc.nextInt();
                if(max<min) max=min;
                wektor.randVector(currentSizeTmp,min,max);
                break;
            case 3:
                wektor.setVector(vec);
                break;
            case 4:
                System.out.print("Podaj nową wartość: ");
                int value = getInt(sc);
                wektor.addAtEnd(value, 1);
                break;
            default:
                return;
        }
        wektor.addAtEnd(10, 50);
        wektor.display();
    }
    /** setVectorElements(Scanner sc, Wektor wektor, int[] vec = new int[]{2,-6,2,-1}) */
    public static void setVectorElements(Scanner sc, Wektor wektor) {
        setVectorElements(sc, wektor, new int[]{2,-6,2,-1});
    }
    public static void main(String[] args) {
        Wektor testVector = new Wektor(4);
        //Tworzy jeden obiekt sc.
        Scanner sc = new Scanner(System.in);
        Main.setVectorElements(sc, testVector);
    }
}//End of class wektorApp
