/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.boun.cmpe.mas.akin.cpgen;

import tr.edu.boun.cmpe.mas.akin.cpgen.util.XMLInputDataReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author akin
 */
public class XMLInputReaderTest {
    


    @Test
    public void testInput1() {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            XMLInputDataReader reader = new XMLInputDataReader();
            parser.parse(getClass().getResourceAsStream("/TestInput1.xml"), reader);
            System.out.println(reader.getDataSet());
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLInputReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
