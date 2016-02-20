package de.fanalin.futoshiki.solver.gamerepository.futoshikiorginterface;

import de.fanalin.futoshiki.solver.FutoshikiGame;
import de.fanalin.futoshiki.solver.FutoshikiGameProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by matti on 19.09.2015.
 */
@Service
public class XmlToGameTransformer {
    Logger logger = LoggerFactory.getLogger(XmlToGameTransformer.class);

    public FutoshikiGame getGame(InputStream stream, final FutoshikiGameProperties props) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Element ele = db.parse(stream).getDocumentElement();

        final String gameContent = ele.getTextContent();
        logger.info("text=" + gameContent);

        return new StringFutoshikiGame(gameContent, props);
    }
}
