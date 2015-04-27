package wymiana;

import java.util.LinkedList;

class StrategiaCLOCK extends Strategia
{
	private Ramka[] ramki;
	private LinkedList<Ramka> kolejka;

	public StrategiaCLOCK(int[] kolejneOdwołania, int ileRamek)
	{
		super();
		this.kolejneOdwołania = kolejneOdwołania;
		this.ileRamek = ileRamek;
		this.ramki = new Ramka[ileRamek];
		this.kolejka = new LinkedList<>();
	}

	//Sprawdza, czy strona o podanym numerze jest załadowana do którejś z ramek.
	//Jeśli jest, to odwołuje się do niej (i zwraca true).
	@Override
	protected boolean istnieje(int strona)
	{
		for (Ramka r: ramki)
		{
			if (r == null)
				return false;
			else if (r.getNrStrony() == strona)
			{
				r.odwołajSię();
				return true;
			}
		}

		return false;
	}

	//Funkcja najpierw sprawdza, czy jest jakaś wolna ramka.
	//Jeśli jest, to ładuje do niej podaną stronę i zwraca "DODANO".
	//Jeśli nie ma wolnej ramki, to w kolejce znajduje zajętą ramkę,
	//do której załadujemy podaną stronę i zwraca "ZAŁADOWANO"
	protected CzyZamieniono dodajStronę(int nrStrony)
	{
		for (int i = 0; i < this.ileRamek; i++)
		{
			if (this.ramki[i] == null)
			{
				this.ramki[i] = new Ramka(nrStrony);
				this.kolejka.addLast(this.ramki[i]);
				return CzyZamieniono.DODANO;
			}
		}

		Ramka temp;

		while (this.kolejka.size() > 0)
		{
			temp = this.kolejka.getFirst();
			if (temp.getBit())
			{
				temp.wykorzystajSzansę();
				this.kolejka.removeFirst();
				this.kolejka.addLast(temp);
			}
			else
			{
				for (int i = 0; i < this.ileRamek; i++)
				{
					if (this.ramki[i].getNrStrony() == temp.getNrStrony())
					{
						this.ramki[i] = new Ramka(nrStrony);
						this.kolejka.addLast(this.ramki[i]);
						return CzyZamieniono.ZAMIENIONO;
					}
				}
			}
		}

		//Tu nie powinniśmy dojść
		return CzyZamieniono.ZAMIENIONO;
	}

	public WynikStrategii wykonaj()
	{
		WynikStrategii wynik = new WynikStrategii("CLOCK", this.ileRamek);

		for (int k: this.kolejneOdwołania)
		{
			wynik.noweOdwołanie();
			
			if (!istnieje(k))
			{
				if (this.kolejka.size() == 0)
				{
					this.ramki[0] = new Ramka(k);
					wynik.dodajBłąd(0, k);
					kolejka.addFirst(this.ramki[0]);
				}
				else
				{
					if (dodajStronę(k) == CzyZamieniono.ZAMIENIONO)
						wynik.dodajBłąd(this.kolejka.pollFirst().getNrStrony(), k);
					else
						wynik.dodajBłąd(0, k);
				}
			}
		}

		return wynik;
	}
}