class PorównywaczIndeksów extends Porównywacz
{
    public boolean mniejsze(Osoba o1, Osoba o2)
    {
    	return (o1.getNrIndeksu() < o2.getNrIndeksu());
    }
}