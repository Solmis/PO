package wymiana;

class StrategiaOPT extends Strategia
{
	public StrategiaOPT(int[] kolejneOdwołania, int ileRamek)
	{
		super(kolejneOdwołania, ileRamek);
	}

	//Zwraca indeks w tablicy this.ramki, pod którym znajduje się strona do usunięcia
	private int znajdźNieużywaną(int nrOdwołania)
	{
		int max = 0, indRamki = 0, count;

		for (int i = 0; i < this.ileRamek; i++)
		{
			count = 0;

			for (int j = nrOdwołania; j < this.kolejneOdwołania.length; j++)
			{

				if (this.ramki[i] == this.kolejneOdwołania[j])
				{
					if (count > max)
					{
						max = count;
						indRamki = i;
					}

					break;
				}
				count++;
			}

			if (count == this.kolejneOdwołania.length-nrOdwołania)
			{
				if (count > max)
				{
					max = count;
					indRamki = i;
				}
				else if (this.ramki[i] < this.ramki[indRamki])
					indRamki = i;
			}
		}

		return indRamki;
	}

	//Funkcja ładuje stronę o podanym numerze do pierwszej pustej ramki i zwraca "DODANO".
	//Jeśli wszystkie ramki są zajęte, zwraca "ZAMIENIONO".
	private CzyZamieniono dodajStronę(int nrStrony)
	{
		for (int i = 0; i < this.ileRamek; i++)
		{
			if (this.ramki[i] == 0)
			{
				this.ramki[i] = nrStrony;
				return CzyZamieniono.DODANO;
			}
		}

		return CzyZamieniono.ZAMIENIONO;
	}

	public WynikStrategii wykonaj()
	{
		WynikStrategii wynik = new WynikStrategii("OPT", this.ileRamek);
		int ind, k;

		for (int i = 0; i < this.kolejneOdwołania.length; i++)
		{
			wynik.noweOdwołanie();
			k = this.kolejneOdwołania[i];

			if (!istnieje(k))
			{
				if (dodajStronę(k) == CzyZamieniono.ZAMIENIONO)
				{
					ind = znajdźNieużywaną(i+1);
					wynik.dodajBłąd(this.ramki[ind], k);
					this.ramki[ind] = k;
				}
				else
					wynik.dodajBłąd(0, k);
			}
		}

		return wynik;
	}
}