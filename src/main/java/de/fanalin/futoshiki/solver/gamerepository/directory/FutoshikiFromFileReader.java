package de.fanalin.futoshiki.solver.gamerepository.directory;

import de.fanalin.futoshiki.solver.game.FutoshikiGame;
import de.fanalin.futoshiki.solver.game.FutoshikiGameProperties;
import de.fanalin.futoshiki.solver.gamerepository.FutoshikiRepository;

import de.fanalin.futoshiki.solver.gamerepository.futoshikiorginterface.XmlToGameTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Read futoshiki games from a directory games/ from classpath. Every file is one game, the game properties are specified
 * in the filename.
 *
 * The files use the same format as the games from the futoshiki.org interface
 *
 * Currently not in use, stays here for testing purposes
 */
public class FutoshikiFromFileReader implements FutoshikiRepository {

    private XmlToGameTransformer gameTransformer;

    final static String FILE_PREFIX = "games/futoshiki-";
    final static String FILE_SUFFIX = ".xml";

    public FutoshikiFromFileReader(XmlToGameTransformer gameTransformer) {
        this.gameTransformer = gameTransformer;
    }

    @Override
    public FutoshikiGame get(FutoshikiGameProperties properties) {

        String fileName = FILE_PREFIX + properties.getSize() + "-" + properties.getDifficulty() + FILE_SUFFIX;
        InputStream gameStream = getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            return gameTransformer.getGame(gameStream, properties);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }
}
