package com.cjcore.cmdb.xml;

import com.cjcore.cmdb.bean.Movie;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by jpereira on 12/26/2014.
 */
public class XMLHandler extends DefaultHandler{

    String elementValue = null;
    Boolean elementOn = false;
    public static XMLGettersSetters data = new XMLGettersSetters();
    public Movie movie;

    private StringBuilder reviewChars = new StringBuilder();
    private String nodeName = "";

    public static XMLGettersSetters getXMLData() {
        return data;
    }

    /**
     * This will be called when the tags of the XML starts.
     **/
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        elementOn = true;

        nodeName = localName;
        if (localName.equals("tblMovies")){
            movie = new Movie();
        }
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

        if (localName.equalsIgnoreCase("MovieID")) {
            String movieIdStr = elementValue;
            Long  movieId= null;
            if (movieIdStr != null && !movieIdStr.isEmpty()) {
                movieId = Long.parseLong(movieIdStr);
            }
            movie.setMovieId(movieId);

        }else if (localName.equalsIgnoreCase("MovieName"))
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

        else if (localName.equalsIgnoreCase("Categories"))
            movie.setCategories(elementValue);

        else if(localName.equalsIgnoreCase("Director"))
            movie.setDirector(elementValue);

        else if(localName.equalsIgnoreCase("Rated"))
            movie.setRated(elementValue);

        else if(localName.equalsIgnoreCase("RunTime"))
            movie.setRuntime(elementValue);

        else if(localName.equalsIgnoreCase("IMDBID"))
            movie.setImdbId(elementValue);

        else if(localName.equalsIgnoreCase("FileName"))
            movie.setFileName(elementValue);

        else if(localName.equalsIgnoreCase("FileSize"))
            movie.setFileSize(elementValue);

        else if(localName.equalsIgnoreCase("Remarks"))
            movie.setRemarks(elementValue);

        else if(localName.equalsIgnoreCase("HDLocation"))
            movie.setHdLocation(elementValue);

        else if(localName.equalsIgnoreCase("tblMovies"))
            data.setMovieList(movie);

        else if(localName.equalsIgnoreCase("Reviews")){
            movie.setReviews(reviewChars.toString());

            reviewChars = new StringBuilder();
        }


    }

    /**
     * This is called to get the tags value
     **/
    @Override
    public void characters(char[] ch, int start, int length)
   {
       String value = new String(ch, start, length);
        if (elementOn) {
            elementValue = value;
            elementOn = false;

        }

       if(nodeName.equalsIgnoreCase("Reviews")){
           reviewChars.append(value);
       }
    }

}
