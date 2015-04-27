package pl.edu.mimuw.wikiontology.ms347246;

import java.util.*;

public class Vert
{
    private String name, lowerName;
    private ArrayList<Vert> neighbors;
    ArrayList<String> links, categories;
    private boolean visited;
    private Vert prev;

    public Vert(String name)
    {
        this.name = name;
        this.lowerName = name.toLowerCase();
        this.neighbors = new ArrayList<>();
        this.links = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.visited = false;
        this.prev = null;
    }

    public void addNeighbor(Vert v)
    {
        if (!this.neighbors.contains(v))
            this.neighbors.add(v);
    }

    public ArrayList<Vert> getNeighbors()
    {
        return this.neighbors;
    }

    public ArrayList<String> getCategories()
    {
        return this.categories;
    }

    public void addLink(String lnk)
    {
        if (!this.links.contains(lnk.toLowerCase()))
            this.links.add(lnk.toLowerCase());
    }

    public void addCategory(String cat)
    {
        if (!this.categories.contains(cat.toLowerCase()))
            this.categories.add(cat.toLowerCase());
    }

    public boolean isVisited()
    {
        return this.visited;
    }

    public void visit(Vert prev)
    {
        this.visited = true;
        this.prev = prev;
    }

    public void resetVisit()
    {
        this.visited = false;
        this.prev = null;
    }

    public Vert getPrev()
    {
        return this.prev;
    }

    public boolean equals(Vert v)
    {
        return getName().equals(v.getName());
    }

    public String toString()
    {
        return this.name;
    }

    public String getName()
    {
        return this.lowerName;
    }
}
