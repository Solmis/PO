class PorównywaczWieku extends Porównywacz
{
    public boolean mniejsze(Osoba o1, Osoba o2)
    {
    	return (o1.getWiek() < o2.getWiek());
    }
}