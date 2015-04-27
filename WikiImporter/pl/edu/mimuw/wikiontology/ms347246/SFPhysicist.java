package pl.edu.mimuw.wikiontology.ms347246;

public class SFPhysicist extends SearchFilter
{
    public boolean isProper(Vert v)
    {
        for (String s: v.getCategories())
        {
            if (s.contains("physicist"))
                return true;
        }
        return false;
    }

    public String toString()
    {
        return "being physicist";
    }
}
