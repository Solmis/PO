package pl.edu.mimuw.wikiontology.ms347246;

import java.util.*;
import java.io.*;
import java.util.regex.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

public class WikiOntology
{
    private int entNum;
    private Graph entGraph;

    public void loadFromFile(String path, ClassifFilter cf) throws Exception
    {
        File f = new File(path);
        if (f.isFile())
        {
            this.entGraph = new Graph();
            try
            {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                final ClassifFilter clFlt = cf;
                DefaultHandler handler = new DefaultHandler()
                {
                    boolean page = false, title = false, text = false;
                    StringBuilder sb;
                    Vert v;

                    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
                    {
                        if (qName.equalsIgnoreCase("PAGE"))
                            page = true;
                        if (qName.equalsIgnoreCase("TITLE"))
                            title = true;
                        if (qName.equalsIgnoreCase("TEXT"))
                            text = true;
                    }

                    public void endElement(String uri, String localName, String qName) throws SAXException
                    {
                        if (qName.equalsIgnoreCase("PAGE"))
                            page = false;
                        if (qName.equalsIgnoreCase("TITLE"))
                            title = false;
                        if (qName.equalsIgnoreCase("TEXT"))
                        {
                            if (clFlt.load(sb.toString(), v))
                                entGraph.addVert(v);
                            text = false;
                        }
                    }

                    public void characters(char ch[], int start, int length) throws SAXException
                    {
                        if (title)
                        {
                            v = new Vert(new String(ch, start, length));
                            sb = new StringBuilder();
                        }

                        if (text)
                            sb.append(ch, start, length);
                    }
                };

                DefaultHandler linkHandler = new DefaultHandler()
                {
                    boolean page = false, title = false, text = false, first = true;
                    Vert v;
                    ArrayList<String> titles;
                    Matcher matcher;
                    String str;
                    Pattern patt;
                    StringBuilder sb;

                    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
                    {
                        if (first)
                        {
                            titles = entGraph.getList();
                            first = false;
                        }
                        if (qName.equalsIgnoreCase("PAGE"))
                            page = true;
                        if (qName.equalsIgnoreCase("TITLE"))
                            title = true;
                        if (qName.equalsIgnoreCase("TEXT"))
                            text = true;
                    }

                    public void endElement(String uri, String localName, String qName) throws SAXException
                    {
                        if (qName.equalsIgnoreCase("PAGE"))
                            page = false;
                        if (qName.equalsIgnoreCase("TITLE"))
                            title = false;
                        if (qName.equalsIgnoreCase("TEXT"))
                        {
                            if (v != null)
                            {
                                String str = sb.toString();

                                for (String s: titles)
                                {
                                    if (!s.equals(v.getName()))
                                    {
                                        patt = Pattern.compile(s, Pattern.CASE_INSENSITIVE);
                                        matcher = patt.matcher(str);

                                        if (matcher.find())
                                            v.addLink(matcher.group(0));
                                    }
                                }
                            }
                            text = false;
                        }
                    }

                    public void characters(char ch[], int start, int length) throws SAXException
                    {
                        if (title)
                        {
                            v = entGraph.getEntity(new String(ch, start, length));
                            sb = new StringBuilder();
                        }

                        if (text)
                            sb.append(ch, start, length);
                    }
                };

                saxParser.parse(path, handler);
                saxParser.parse(path, linkHandler);
                this.entGraph.makeConnections();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
            throw new Exception("Wrong path to XML file (" + path + "). File does not exist.");
    }

    public int getEntNum()
    {
        return this.entGraph.getVertNum();
    }

    public Path findPath(String o1, String o2, SearchFilter sf) throws Exception
    {
        if (this.entGraph == null)
            throw new Exception("Error: WikiOntology is empty, load data from XML file first.");
        else
            return this.entGraph.findPath(o1, o2, sf);
    }
}
