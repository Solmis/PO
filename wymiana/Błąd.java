package wymiana;

class Błąd
{
	private int nrStrUsuwanej, nrStrSprowadzanej;

	public Błąd(int nrStrUsuwanej, int nrStrSprowadzanej)
	{
		this.nrStrUsuwanej = nrStrUsuwanej;
		this.nrStrSprowadzanej = nrStrSprowadzanej;
	}

	public String toString()
	{
		return ("Błąd: " + this.nrStrUsuwanej + " --> " + this.nrStrSprowadzanej);
	}
}