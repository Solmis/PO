import java.math.*;
import java.lang.*;

class quicks
{
	public static final int MAX = 1000000;

	static public void main (String args[])
	{
		long czas = 0;
		int[] tab = new int[MAX];

		for (int i = 0; i < MAX; i++)
			tab[i] = (int)(Math.random()*65536+1);

		czas = System.currentTimeMillis();
		qqsort(tab, 0, MAX-1);
		czas = System.currentTimeMillis()-czas;

		System.out.println("Czas wykonania [ms]: "+czas);
	}

	static public int podziel(int[] t, int l, int p)
	{
		int x = t[l], i = l, j = p, tmp;

		while (1 == 1)
		{
			while (t[j] > x)
				j--;
			while (t[i] < x)
				i++;

			if (i < j)
			{
				tmp = t[j];
				t[j] = t[i];
				t[i] = tmp;
				i++;
				j--;
			}
			else
				return j;
		}
	}

	static public void qqsort(int[] t, int l, int p)
	{
		if (l < p)
		{
			int k = podziel(t, l, p);
			qqsort(t, l, k);
			qqsort(t, k+1, p);
		}
	}
}