import java.util.Scanner;

class Osoba
{
    private String imię, nazwisko;
    private int nr_indeksu, wiek, stypendium;
    
    public Osoba(String imię, String nazwisko, int nr_indeksu, int wiek, int stypendium)
    {
        this.imię = imię;
        this.nazwisko = nazwisko;
        this.nr_indeksu = nr_indeksu;
        this.wiek = wiek;
        this.stypendium = stypendium;
    }
    
    public static Osoba wczytaj(Scanner sc)
    {
        String imię = sc.next();
        String nazwisko = sc.next();
        int nr_indeksu = sc.nextInt();
        int wiek = sc.nextInt();
        int stypendium = sc.nextInt();
        return new Osoba(imię, nazwisko, nr_indeksu, wiek, stypendium);
    }
    
    public String toString()
    {
        String wynik = this.imię + " " + this.nazwisko + " (" + this.nr_indeksu + "), " + this.wiek + " lat [stypendium: " + this.stypendium + "]";
        return wynik;
    }
    
    public String getImię()
    {
        return this.imię;
    }
    
    public String getNazwisko()
    {
        return this.nazwisko;
    }
    
    public int getNrIndeksu()
    {
        return this.nr_indeksu;
    }
    
    public int getWiek()
    {
        return this.wiek;
    }
    
    public int getStypendium()
    {
        return this.stypendium;
    }
}