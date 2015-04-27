import java.util.*;

class MostCommon
{
	private static final String[] forbidden = {".", ",", "!", "?", ";", ":", "-", "(", ")", "\""};
	private String[] ranking;
	private int[] rankingCount;
	private Map<String, Integer> words;
	private static final int MAX_WORDS = 50;

	public MostCommon(Scanner sc)
	{
		this.words = new TreeMap<String, Integer>();
		String s;
		int val;

		while (sc.hasNext())
		{
			s = sc.next();
			s = normalize(s);
			if (s.length() >= 4)
			{
				if (this.words.containsKey(s))
				{
					val = this.words.get(s);
					val++;
					this.words.put(s, val);
				}
				else
					this.words.put(s, 1);
			}
		}

		rankWords();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder("Najczęstsze słowa: \n\n");

		for (int i = MAX_WORDS-1; i >= 0; i--)
			sb.append(MAX_WORDS-i).append(": ").append(this.ranking[i]).append(" (").append(this.rankingCount[i]).append(" wystąpień)\n");
		return sb.toString();
	}

	private int findNewMin()
	{
		int min = 0;

		for (int i = 1; i < MAX_WORDS; i++)
		{
			if (this.rankingCount[i] < this.rankingCount[min])
				min = i;
		}

		return min;
	}

	private void rankWords()
	{
		this.ranking = new String[MAX_WORDS];
		this.rankingCount = new int[MAX_WORDS];

		String[] all = new String[this.words.size()];
		this.words.keySet().toArray(all);

		//indeks ze słowem o najmniejszej liczbie wystąpień
		int minimum = 0;

		int count;
		for (String s: all)
		{
			count = this.words.get(s);

			if (count > this.rankingCount[minimum])
			{
				this.rankingCount[minimum] = count;
				this.ranking[minimum] = s;
				minimum = findNewMin();
			}
		}

		DuoQuicksort.qsort(this.rankingCount, this.ranking, 0, MAX_WORDS-1);
	}

	private boolean isForbidden(String chr)
	{
		for (String s: forbidden)
		{
			if (s.equals(chr))
				return true;
		}

		return false;
	}

	private String normalize(String s)
	{
		String result = new String();
		String chr;
		
		for (int i = 0; i < s.length(); i++)
		{
			chr = s.substring(i, i+1);
			if (!isForbidden(chr))
				result = result.concat(chr);
		}

		result = result.toLowerCase();

		return result;
	}
}