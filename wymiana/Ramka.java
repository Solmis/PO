package wymiana;

class Ramka
{
	private boolean bitOdwołania;
	private int nrStrony;

	public Ramka(int nrStrony)
	{
		this.bitOdwołania = true;
		this.nrStrony = nrStrony;
	}

	public boolean getBit()
	{
		return this.bitOdwołania;
	}

	public int getNrStrony()
	{
		return this.nrStrony;
	}

	public void wykorzystajSzansę()
	{
		this.bitOdwołania = false;
	}

	public void odwołajSię()
	{
		this.bitOdwołania = true;
	}
}