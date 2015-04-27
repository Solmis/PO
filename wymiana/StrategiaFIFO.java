package wymiana;

import java.util.LinkedList;

class StrategiaFIFO extends Strategia
{
	private LinkedList<Integer> kolejka;

	public StrategiaFIFO(int[] kolejneOdwołania, int ileRamek)
	{
		super(kolejneOdwołania, ileRamek);
		this.kolejka = new LinkedList<>();
	}

	public WynikStrategii wykonaj()
	{
		WynikStrategii wynik = new WynikStrategii("FIFO", this.ileRamek);

		for (int k: this.kolejneOdwołania)
		{
			wynik.noweOdwołanie();

			if (!istnieje(k))
			{
				if (this.kolejka.size() == 0)
				{
					this.ramki[0] = k;
					wynik.dodajBłąd(0, k);
				}
				else
				{
					if (dodajStronę(k, this.kolejka.getFirst()) == CzyZamieniono.ZAMIENIONO)
						wynik.dodajBłąd(this.kolejka.pollFirst(), k);
					else
						wynik.dodajBłąd(0, k);
				}

				kolejka.addLast(new Integer(k));
			}
		}

		return wynik;
	}
}