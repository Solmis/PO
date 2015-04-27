package pl.edu.mimuw.wikiontology.ms347246;

import java.util.*;

public class Graph
{
    private ArrayList<Vert> vertList;
    private int vertNum;

    public Graph()
    {
        this.vertList = new ArrayList<>();
    }

    public boolean addVert(Vert v)
    {
        if (!this.vertList.contains(v))
        {
            this.vertList.add(v);
            return true;
        }
        else
            return false;
    }

    public int getVertNum()
    {
        return this.vertNum;
    }

    public ArrayList<String> getList()
    {
        ArrayList<String> resu = new ArrayList<>();
        for (Vert v: this.vertList)
            resu.add(v.getName());
        return resu;
    }

    public void makeConnections()
    {
        int i = 0;
        for (Vert v: this.vertList)
        {
            for (String s: v.links)
                connectVerts(v, s);
            i++;
        }
        this.vertNum = i;
    }

    public Vert getEntity(String name)
    {
        Vert resu = null;
        name = name.toLowerCase();

        for (Vert v: this.vertList)
        {
            if (name.equals(v.getName()))
            {
                resu = v;
                break;
            }
        }

        return resu;
    }

    public Path findPath(String o1, String o2, SearchFilter sf)
    {
        Vert start = getEntity(o1);
        Vert finish = getEntity(o2);

        if (start == null)
            return new SpecialPath("Entity " + o1 + " does not exist.");
        else if (finish == null)
            return new SpecialPath("Entity " + o2 + " does not exist.");
        else if (!sf.isProper(start))
            return new SpecialPath("Entity " + o1 + " does not fulfill requirements of filter '" + sf.toString() + "'.");
        else if (!sf.isProper(finish))
            return new SpecialPath("Entity " + o2 + " does not fulfill requirements of filter '" + sf.toString() + "'.");
        else if (start.equals(finish))
            return new SpecialPath("Path length: 0");
        else
        {
            LinkedList<Vert> vertQueue = new LinkedList<>();

            resetVerts();
            start.visit(null);
            for (Vert v: start.getNeighbors())
            {
                if (sf.isProper(v))
                {
                    v.visit(start);
                    vertQueue.addLast(v);
                }
            }

            boolean found = false;
            Vert vt = null;

            while ((!found) && (vertQueue.size() > 0))
            {
                vt = vertQueue.pollFirst();
                if (vt.equals(finish))
                {
                    found = true;
                }
                else
                {
                    for (Vert v: vt.getNeighbors())
                    {
                        if ((!v.isVisited()) && (sf.isProper(v)))
                        {
                            v.visit(vt);
                            vertQueue.addLast(v);
                        }
                    }
                }
            }

            if (!found)
                return new SpecialPath("Path between " + o1 + " and " + o2 + " does not exist (using filter '" + sf.toString() + "').");
            else
            {
                NormalPath np = new NormalPath();

                while ((vt != null) && (vt.isVisited()))
                {
                    np.addEntity(new PathElem(vt.toString()));
                    vt = vt.getPrev();
                }

                return np;
            }
        }
    }

    private void resetVerts()
    {
        for (Vert v: vertList)
            v.resetVisit();
    }

    private void connectVerts(Vert v1, String lnk)
    {
        if (this.vertList.contains(v1))
        {
            Vert vert = null;

            for (Vert v: this.vertList)
            {
                if (lnk.equals(v.getName()))
                {
                    vert = v;
                    break;
                }
            }
            if (vert != null)
                v1.addNeighbor(vert);
        }
    }
}
