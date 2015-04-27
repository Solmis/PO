import pl.edu.mimuw.wikiontology.ms347246.*;
import java.util.*;

public class WikiImporter
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        SFAll fltAll = new SFAll();
        SFPhysicist fltPhysicist = new SFPhysicist();
        String all = new String("all");
        String physicist = new String("physicist");
        WikiOntology wo = new WikiOntology();
        try
        {
            wo.loadFromFile(args[0], new CFPerson());
            System.out.println(Integer.toString(wo.getEntNum()) + " entities imported.");
            String s;
            while (sc.hasNext())
            {
                s = sc.next();
                System.out.println("***");
                if (s.equals(all))
                    System.out.println(wo.findPath(norm(sc.next()), norm(sc.next()), fltAll).toString());
                else if (s.equals(physicist))
                    System.out.println(wo.findPath(norm(sc.next()), norm(sc.next()), fltPhysicist).toString());
                System.out.println("***");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static String norm(String s)
    {
        Scanner scan = new Scanner(s);
        scan.useDelimiter("_");
        String resu = new String("");
        while (scan.hasNext())
            resu = resu.concat(scan.next()).concat(" ");
        resu = resu.trim();
        return resu;
    }
}
