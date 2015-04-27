package wymiana;

import java.util.ArrayList;

class WynikStrategii
{
	private String skrót;
	private int ileRamek, ileOdwołań;
	private ArrayList<Błąd> błędy;

	public WynikStrategii(String skrót, int ileRamek)
	{
		this.skrót = skrót;
		this.ileRamek = ileRamek;
		this.błędy = new ArrayList<>();
		this.ileOdwołań = 0;
	}

	public void dodajBłąd(int nrUsuwanej, int nrSprowadzanej)
	{
		błędy.add(new Błąd(nrUsuwanej, nrSprowadzanej));
	}

	public void noweOdwołanie()
	{
		this.ileOdwołań++;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Strategia: ").append(this.skrót);
		sb.append(", ramki: ").append(this.ileRamek).append("\n");
		for (Błąd b: błędy)
			sb.append(b.toString()).append("\n");
		sb.append("Odwołania: ").append(this.ileOdwołań);
		sb.append(", błędy: ").append(this.błędy.size());

		return sb.toString();
	}
}