package com.cjcore.cmdb.xml;

import com.cjcore.cmdb.bean.Movie;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jpereira on 12/26/2014.
 */
public class XMLHandler extends DefaultHandler{

    String elementValue = null;
    Boolean elementOn = false;
    public static XMLGettersSetters data = null;
    public Movie movie;

    public static XMLGettersSetters getXMLData() {
        return data;
    }

    public static void setXMLData(XMLGettersSetters data) {
        data = data;
    }

    /**
     * This will be called when the tags of the XML starts.
     **/
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        elementOn = true;

        if (localName.equals("tblMovies")){
            data = new XMLGettersSetters();
            movie = new Movie();
        }

       // } else if (localName.equals("CD")) {
            /**
             * We can get the values of attributes for eg. if the CD tag had an attribute( <CD attr= "band">Akon</CD> )
             * we can get the value "band". Below is an example of how to achieve this.
             *
             * String attributeValue = attributes.getValue("attr");
             * data.setAttribute(attributeValue);
             *
             * */
        //s}
    }

    /**
     * This will be called when the tags of the XML end.
     **/
    @Override
    public void endElement(String uri, String localName, String qName)
      {

        elementOn = false;

        /**
         * Sets the values after retrieving the values from the XML tags
         * */
        if (localName.equalsIgnoreCase("MovieName"))
            movie.setName(elementValue);

        else if (localName.equalsIgnoreCase("Summary"))
            movie.setSummary(elementValue);

        else if (localName.equalsIgnoreCase("Stars"))
            movie.setStars(elementValue);

        else if (localName.equalsIgnoreCase("YearRelease")){
            String yearStr = elementValue;
            Integer yearRelease = null;
            if(yearStr != null && !yearStr.isEmpty()){
                yearRelease = Integer.parseInt(yearStr);
            }
            movie.setYearRelease(yearRelease);
        }
        else if (localName.equalsIgnoreCase("Rate")){
            String rateStr = elementValue;
            Double rate = null;
            if(rateStr != null && !rateStr.isEmpty()){
                rate = Double.parseDouble(rateStr);
            }
            movie.setRate(rate);
        }
        else if (localName.equalsIgnoreCase("Location"))
            movie.setLocation(elementValue);
        else if (localName.equalsIgnoreCase("Categories")){
            movie.setCategories(elementValue);
        }else if(localName.equalsIgnoreCase("Director")){
            movie.setDirector(elementValue);
        }else if(localName.equalsIgnoreCase("Rated")){
            movie.setRated(elementValue);
        }else if(localName.equalsIgnoreCase("RunTime")) {
            movie.setRuntime(elementValue);
        }else if(localName.equalsIgnoreCase("IMDBID")){
            movie.setImdbId(elementValue);
        }else if(localName.equalsIgnoreCase("FileName")){
            movie.setFileName(elementValue);
        }else if(localName.equalsIgnoreCase("FileSize")){
            movie.setFileSize(elementValue);
        }else if(localName.equalsIgnoreCase("Remarks")){
            movie.setRemarks(elementValue);
        }else if(localName.equalsIgnoreCase("Reviews")){
            movie.setReviews(elementValue);
        }else if(localName.equalsIgnoreCase("HDLocation")){
            movie.setReviews(elementValue);
        }else if(localName.equalsIgnoreCase("tblMovies")){
            data.setMovieList(movie);
        }

    }

    /**
     * This is called to get the tags value
     **/
    @Override
    public void characters(char[] ch, int start, int length)
   {
        if (elementOn) {
            elementValue = new String(ch, start, length);
            elementOn = false;
        }

    }

}