class PorównywaczAlfabetyczny extends Porównywacz
{
    public boolean mniejsze(Osoba o1, Osoba o2)
    {
    	return (o1.getNazwisko().compareTo(o2.getNazwisko()) < 0);
    }
}