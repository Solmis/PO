import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class test
{
	public static void main(String args[])
	{
		ListaOsób lista = new ListaOsób();
		File f = new File("osoby.txt");

		try
		{
			Scanner input = new Scanner(f);
			lista.wczytaj(input);
			PorównywaczAlfabetyczny p_w = new PorównywaczAlfabetyczny();
			System.out.println(lista.toString());
			lista.sortuj(p_w);
			System.out.println(lista.toString());
			input.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}