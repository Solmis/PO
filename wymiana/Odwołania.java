package wymiana;

public class Odwołania
{
	private Strategia strategia;
	private WynikStrategii wynik;

    public Odwołania (int[] tab, String s, int rozmiar)
    {
        try
        {
            stwórzStrategię(tab, s, rozmiar);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void stwórzStrategię(int[] tab, String s, int rozmiar) throws Exception
    {
        if (s.equals("OPT"))
            this.strategia = new StrategiaOPT(tab, rozmiar);
        else if (s.equals("FIFO"))
            this.strategia = new StrategiaFIFO(tab, rozmiar);
        else if (s.equals("LRU"))
            this.strategia = new StrategiaLRU(tab, rozmiar);
        else if (s.equals("CLOCK"))
            this.strategia = new StrategiaCLOCK(tab, rozmiar);
        else
            throw new Exception("Niepoprawna nazwa strategii");
    }

    public void wykonaj()
    {
    	wynik = strategia.wykonaj();
    	System.out.println(wynik.toString());
    }
}