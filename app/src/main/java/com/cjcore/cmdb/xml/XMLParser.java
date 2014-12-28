package com.cjcore.cmdb.xml;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by jpereira on 12/28/2014.
 */
public class XMLParser {

    private Boolean returnBool = false;

    public boolean parseXml(String fileFullPath){

        InputStream is = null;

        try {
            /**
             * Create a new instance of the SAX parser
             **/
            SAXParserFactory saxPF = SAXParserFactory.newInstance();
            SAXParser saxP = saxPF.newSAXParser();
            XMLReader xmlR = saxP.getXMLReader();

            is = new FileInputStream(fileFullPath);

            InputSource inputSource = new InputSource(is);
            /**
            * Create the Handler to handle each of the XML tags.
            **/
            XMLHandler myXMLHandler = new XMLHandler();
            xmlR.setContentHandler(myXMLHandler);
            xmlR.parse(inputSource);

            returnBool = true;
            is.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return returnBool;
    }

}
