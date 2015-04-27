package wymiana;

public abstract class Strategia
{
	protected int ileRamek;
	protected int[] kolejneOdwołania, ramki;
	public abstract WynikStrategii wykonaj();

	public enum CzyZamieniono
	{
		ZAMIENIONO, DODANO
	}

	public Strategia(int[] kolejneOdwołania, int ileRamek)
	{
		this.ileRamek = ileRamek;
		this.kolejneOdwołania = kolejneOdwołania;
		this.ramki = new int[ileRamek];
	}

	//Pusty konstruktor
	public Strategia()
	{
	}

	//Zwraca true, gdy strona o podanym numerze jest aktualnie załadowana do którejś ramki
	protected boolean istnieje(int strona)
	{
		for (int k: ramki)
		{
			if (k == strona)
				return true;
			else if (k == 0)
				return false;
		}

		return false;
	}

	//Funkcja dostaje nr strony do załadowania oraz nr strony, którą chcemy usunąć z pamięci.
	//Jeśli znajdziemy wolną ramkę, to ładujemy do niej stronę o podanym numerze i zwracamy "DODANO".
	//Jeśli wszystkie ramki są zajęte, to ładujemy stronę do ramki przechowującej stronę
	//o numerze nrDoZamiany i zwracamy "ZAMIENIONO".
	protected CzyZamieniono dodajStronę(int nrStrony, int nrDoZamiany)
	{
		int ind = -1;

		for (int i = 0; i < this.ileRamek; i++)
		{
			if (this.ramki[i] == nrDoZamiany)
				ind = i;
			else if (this.ramki[i] == 0)
			{
				this.ramki[i] = nrStrony;
				return CzyZamieniono.DODANO;
			}
		}

		assert ind > 0;
		this.ramki[ind] = nrStrony;
		return CzyZamieniono.ZAMIENIONO;
	}
}