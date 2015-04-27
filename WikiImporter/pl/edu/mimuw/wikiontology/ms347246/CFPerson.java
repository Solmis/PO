package pl.edu.mimuw.wikiontology.ms347246;

import java.util.regex.*;

public class CFPerson extends ClassifFilter
{
    private Pattern pattCategory, pattPersonData, pattOther[];
    private int patt_count;

    public CFPerson()
    {
        this.pattCategory = Pattern.compile("\\[\\[Category:([^\\]]*)\\]\\]");
        this.pattPersonData = Pattern.compile("\\{\\{Persondata");
        this.patt_count = 5;
        this.pattOther = new Pattern[this.patt_count];
        //<year> births
        this.pattOther[0] = Pattern.compile("([0-9]*)\\s(BC\\sbirths|births)");
        //<year> deaths
        this.pattOther[1] = Pattern.compile("([0-9]*)\\s(BC\\sdeaths|deaths)");
        //... mathematicians
        this.pattOther[2] = Pattern.compile("([.]*)\\smathematicians");
        //People from ...
        this.pattOther[3] = Pattern.compile("People\\sfrom\\s");;
        //People with ...
        this.pattOther[4] = Pattern.compile("People\\swith\\s");;
    }

    public boolean load(String article, Vert v)
    {
        Matcher matcher = pattPersonData.matcher(article);
        boolean person;

        if (matcher.find())
            person = true;
        else
            person = false;

        matcher = pattCategory.matcher(article);

        while (matcher.find())
        {
            if (personCategory(matcher.group(1)))
                person = true;
            v.addCategory(matcher.group(1));
        }

        return person;
    }

    private boolean personCategory(String s)
    {
        Matcher m;

        for (Pattern p: this.pattOther)
        {
            m = p.matcher(s);
            if (m.find())
                return true;
        }

        return false;
    }
}
