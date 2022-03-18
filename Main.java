import java.util.Random;
import java.util.Scanner;


// Tablica jednowymiarowa
//==============================================

class Wektor{
    public int[] vector; // referencja do wektora
    public int maxSize; // maksymalna długość wektora
    public int currentSize; // aktualna długość wektora

    //============================================= konstruktor
    public Wektor (int maxSize_) {
        maxSize = maxSize_;
        currentSize = 0;
        vector = new int[maxSize];
    }

    //==================================== ustawienie wektora
    public void setVector(int[] vector) {
        if(maxSize < vector.length){
            this.vector = vector;
            // Nadpisuje wcześniejsze ustawienie maxSize.
            maxSize = vector.length;
        }
        else {
            System.arraycopy(vector, 0, this.vector, 0, vector.length);
        }
        // Nadpisuje wcześniejsze ustawienie currentSize dla obu powyższych przypadków.
        currentSize = vector.length;
    }

    //==================================== wczytywanie wektora
    public void readVector(int nrOfIntegers, Scanner sc) {
        for (int i = 0; i < nrOfIntegers; ) {
            if (sc.hasNextInt()) {
                vector[i] = sc.nextInt();
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
            vector[i] = new Random().nextInt(max+1-min)+min;
    }

    //===================================== wyświetlanie wektora
    public void display(){
        System.out.println("Liczba elementow = " + currentSize);
        System.out.println("Zawartosc wektora ");
        for (int i = 0; i<currentSize; i++) {
            System.out.print(vector[i]+", ");
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
            System.out.print(vector[i]+", ");
            if((i-begin+1)%10==0) System.out.println();
        }
        System.out.println();
    }

    /** Dołącza określony element na końcu niecałkowicie wypełnionego wektora. */
    public void addIfNotFull(int element) {
        if(currentSize<maxSize){
            vector[currentSize++] = element;
        }
        else {
            System.out.println("Wektor jest pełen. Przed dodaniem usuń jakiś element.");
        }
    }

    /** addEvenWhenFull z domyślną wartością wyrażoną w procentach używaną do rozszerzenia tablicy. */
    public void addEvenWhenFull(int element) {
        addEvenWhenFull(element, 25);
    }

    /** Dołącza określony element na końcu wektora, nawet gdy jest pełny. W takim przypadku rozmiar wektora zostanie zwiększony. */
    public void addEvenWhenFull(int element, int extendByPercent) {
        if(currentSize==maxSize) {
            if(extendByPercent<1 || extendByPercent>100){
                System.out.println("Procent o ile ma być powiększony maksyamly rozmiar wektora musi znajdować się w przedziale [1, 100]");
                return;
            }
            int extendBy = maxSize*extendByPercent/100;
            // Zwiększamy przynajmniej o jeden.
            maxSize += (extendBy==0?1:extendBy); // Math.max(extendBy, 1)
            int[] vectorTmp = new int[maxSize];
            System.arraycopy(vector, 0, vectorTmp, 0, vector.length);
            vector = vectorTmp;
            vector[currentSize++] = element;
        }
        else {
            addIfNotFull(element);
        }
    }


    //================================== b. Usuwanie z tablicy zadanych elementów
    /** Usuwa  z wektora pierwsze wystąpienie określonego elementu, jeśli jest obecny. */
    public void remove(int element){
        for(int i = 0; i < currentSize; i++) {
            if(vector[i]==element){
                System.arraycopy(vector, i+1, vector, i, --currentSize-i);
                break;
            }
        }
    }
    /** Usuwa z wektora wszystkie wystąpienia określonego elementu, jeśli jest obecny. */
    public void removeAll(int element){
        for(int i = 0; i < currentSize; i++) {
            if(vector[i]==element){
                System.arraycopy(vector, i+1, vector, i, --currentSize-i);
                // Zmniejszamy i, żeby nie pominąć przesuniętego elementu.
                --i;
            }
        }
    }

    //================================== c. Sprawdzanie porządku w tablicy
    public boolean ascendingOrder() {
        for(int i = 0; i < currentSize-1; i++) {
            if(vector[i]>vector[i+1])
                return false;
        }
        return true;
    }
    public boolean descendingOrder() {
        for(int i = 0; i < currentSize-1; i++) {
            if(vector[i]<vector[i+1])
                return false;
        }
        return true;
    }
    public String order() {
        if(currentSize>1){
            boolean asc = ascendingOrder();
            boolean dsc = descendingOrder();
            if (asc && dsc) {
                return "stała";
            }
            if (asc) {
                return "rosnąca";
            }
            if (dsc) {
                return "malejąca";
            }
        }
        return "brak porządku";
    }

    public int getMaxElement()
    {
        int maximum = vector[0];
        for (int i = 1; i < currentSize; i++)
        {
            if (vector[i] > maximum)
                maximum = vector[i];
        }
        return maximum;
    }

    public int getMinElement()
    {
        int minimum = vector[0];
        for (int i = 1; i < currentSize; i++)
        {
            if (vector[i] < minimum)
                minimum = vector[i];
        }
        return minimum;
    }

    public int hornersMethod(int x)
    {
        int output = 0;
        for (int i = 0; i < currentSize; i++)
        {
            output *= x;
            output += vector[i];
        }
        return output;
    }

    public void deleteDuplicatesN2()
    {
        int[] tempVector = new int[currentSize];
        boolean alreadyIn = false;
        int tempSize = 0;

        for (int i = 0; i < currentSize; i++)
        {
            alreadyIn = false;
            for (int j = 0; j < tempSize; j++)
            {
                if (tempVector[j] == vector[i])
                    alreadyIn = true;
            }

            if (!alreadyIn)
            {
                tempVector[tempSize] = vector[i];
                tempSize += 1;
            }
        }
        vector = tempVector;
        currentSize = tempSize;
    }

    public int[] maxSubArray()
    {
        // TODO: dokończyć
        int[] tempSubArray = new int[currentSize];
        tempSubArray[0] = vector[0];
        int tempSize = 1;

        for (int i = 0; i < currentSize; i++)
        {
            if (vector[i] <= vector[i + 1])
            {
                tempSubArray[tempSize] += 1;
                tempSize += 1;
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
                break;
            case 5:
                System.out.println(wektor.getMaxElement());
                break;
            default:
                return;
        }
        wektor.display();
    }
    /** setVectorElements(Scanner sc, Wektor wektor, int[] vec = new int[]{2,-6,2,-1}) */
    public static void setVectorElements(Scanner sc, Wektor wektor) {
        setVectorElements(sc, wektor, new int[]{2,-6,2,-1});
    }
    public static void main(String[] args) {
        Wektor testVector = new Wektor(100);
        //Tworzy jeden obiekt sc.
        Scanner sc = new Scanner(System.in);

        Wektor forDuplicates = new Wektor(100);
        Main.setVectorElements(sc, forDuplicates, new int[]{1, 1, 2, 10, 1, 1, 10, 10, 10, 12, 10, 10, 1});

        forDuplicates.deleteDuplicatesN3();
        forDuplicates.display();

        //Main.setVectorElements(sc, testVector);
    }
}//End of class wektorApp
