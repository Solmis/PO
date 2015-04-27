package pl.edu.mimuw.wikiontology.ms347246;

import java.util.*;

public class NormalPath extends Path
{
    private ArrayList<PathElem> entList;

    public NormalPath()
    {
        entList = new ArrayList<>();
    }

    void addEntity(PathElem elem)
    {
        entList.add(0, elem);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("");
        sb.append("Path length: ").append(Integer.toString(entList.size() - 1)).append("\n");
        for (PathElem ent: entList)
            sb.append(ent.toString()).append("\n");
        return sb.toString().trim();
    }
}
