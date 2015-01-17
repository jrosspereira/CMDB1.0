package com.cjcore.cmdb.xml;

import com.cjcore.cmdb.bean.Movie;
import com.cjcore.cmdb.utils.FileUtils;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by jpereira on 12/28/2014.
 */
public class XMLParser {

    public List<Movie> parseXml(String fileFullPath){

        InputStream is = null;

        try {
            /**
             * Create a new instance of the SAX parser
             **/
            SAXParserFactory saxPF = SAXParserFactory.newInstance();
            SAXParser saxP = saxPF.newSAXParser();
            XMLReader xmlR = saxP.getXMLReader();

            is = new FileInputStream(fileFullPath);

            //String xmlStr = FileUtils.getStringFromInputStream(is);
            //xmlStr.replace("&quot;", ",");

            InputSource inputSource = new InputSource(is);

            /**
            * Create the Handler to handle each of the XML tags.
            **/
            XMLHandler myXMLHandler = new XMLHandler();
            xmlR.setContentHandler(myXMLHandler);
            xmlR.parse(inputSource);

            is.close();

            return myXMLHandler.getXMLData().getMovieList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
