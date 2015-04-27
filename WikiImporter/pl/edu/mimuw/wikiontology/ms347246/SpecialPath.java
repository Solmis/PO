package pl.edu.mimuw.wikiontology.ms347246;

import java.util.*;

public class SpecialPath extends Path
{
    private String description;

    public SpecialPath(String s)
    {
        this.description = s;
    }

    public String toString()
    {
        return this.description;
    }
}
