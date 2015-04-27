class DuoQuicksort
{
	static public int podziel(int[] t, String[] s, int l, int p)
	{
		int x = t[l], i = l, j = p, tmp;
		String s_tmp;

		while (1 == 1)
		{
			while (t[j] > x)
				j--;
			while (t[i] < x)
				i++;

			if (i < j)
			{
				tmp = t[j];
				s_tmp = s[j];
				t[j] = t[i];
				s[j] = s[i];
				t[i] = tmp;
				s[i] = s_tmp;
				i++;
				j--;
			}
			else
				return j;
		}
	}

	static public void qsort(int[] t, String[] s, int l, int p)
	{
		if (l < p)
		{
			int k = podziel(t, s, l, p);
			qsort(t, s, l, k);
			qsort(t, s, k+1, p);
		}
	}
}