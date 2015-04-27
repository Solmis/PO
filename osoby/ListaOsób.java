import java.util.Scanner;

class ListaOsób
{
    private static final int N = 1000;
    private Osoba lista[];
    private int liczba;
    
    public ListaOsób()
    {
        this.lista = new Osoba[N];
        this.liczba = 0;
    }
    
    public void wczytaj(Scanner sc)
    {
        int n = sc.nextInt();
        for (int i = 0; i < n; i++)
            dodajOsobę(Osoba.wczytaj(sc));
    }
    
    public void dodajOsobę(Osoba o1)
    {
        this.lista[this.liczba] = o1;
        this.liczba++;
    }
    
    public void sortuj(Porównywacz p)
    {
        qqsort(p, this.lista, 0, this.liczba-1);
    }
    
    public String toString()
    {
        if (this.liczba > 0)
        {
            StringBuilder sb = new StringBuilder(this.lista[0].toString());
            for (int i = 1; i < this.liczba; i++)
                sb.append("\n").append(this.lista[i].toString());
            return sb.toString();
        }
        else
            return "";
    }

    private int podziel(Porównywacz por, Osoba[] t, int l, int p)
    {
        Osoba x = t[l], tmp;
        int i = l, j = p;

        while (true)
        {
            while (por.mniejsze(x, t[j]))
                j--;
            while (por.mniejsze(t[i], x))
                i++;

            if (i < j)
            {
                tmp = t[j];
                t[j] = t[i];
                t[i] = tmp;
                i++;
                j--;
            }
            else
                return j;
        }
    }

    private void qqsort(Porównywacz por, Osoba[] t, int l, int p)
    {
        if (l < p)
        {
            int k = podziel(por, t, l, p);
            qqsort(por, t, l, k);
            qqsort(por, t, k+1, p);
        }
    }
}