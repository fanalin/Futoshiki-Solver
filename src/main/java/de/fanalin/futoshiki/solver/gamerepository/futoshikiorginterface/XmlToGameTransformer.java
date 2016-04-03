package de.fanalin.futoshiki.solver.gamerepository.futoshikiorginterface;

import com.github.vbauer.herald.annotation.Log;
import de.fanalin.futoshiki.solver.game.FutoshikiGame;
import de.fanalin.futoshiki.solver.game.FutoshikiGameProperties;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Extracts the game string from the "XML" as returned by the futoshiki.org services
 */
@Service
public class XmlToGameTransformer {
    @Log
    private Logger logger;

    public FutoshikiGame getGame(InputStream stream, final FutoshikiGameProperties props) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Element ele = db.parse(stream).getDocumentElement();

        final String gameContent = ele.getTextContent();
        logger.info("text=" + gameContent);

        return new StringFutoshikiGame(gameContent, props);
    }
}
