package wymiana;

import java.util.LinkedList;

class StrategiaLRU extends Strategia
{
	private LinkedList<Integer> ostatnieOdwołania;

	public StrategiaLRU(int[] kolejneOdwołania, int ileRamek)
	{
		super(kolejneOdwołania, ileRamek);
		this.ostatnieOdwołania = new LinkedList<>();
	}

	public WynikStrategii wykonaj()
	{
		WynikStrategii wynik = new WynikStrategii("LRU", this.ileRamek);

		for (int k: this.kolejneOdwołania)
		{
			wynik.noweOdwołanie();

			if (!istnieje(k))
			{
				if (this.ostatnieOdwołania.size() == 0)
				{
					this.ramki[0] = k;
					wynik.dodajBłąd(0, k);
				}
				else
				{
					if (dodajStronę(k, this.ostatnieOdwołania.getLast()) == CzyZamieniono.ZAMIENIONO)
						wynik.dodajBłąd(this.ostatnieOdwołania.pollLast(), k);
					else
						wynik.dodajBłąd(0, k);
				}
			}
			else
				this.ostatnieOdwołania.remove(new Integer(k));

			this.ostatnieOdwołania.addFirst(new Integer(k));
		}

		return wynik;
	}
}