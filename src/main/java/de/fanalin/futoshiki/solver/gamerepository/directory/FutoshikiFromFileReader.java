package de.fanalin.futoshiki.solver.gamerepository.directory;

import de.fanalin.futoshiki.solver.FutoshikiGame;
import de.fanalin.futoshiki.solver.FutoshikiGameProperties;
import de.fanalin.futoshiki.solver.gamerepository.FutoshikiRepository;

import de.fanalin.futoshiki.solver.gamerepository.futoshikiorginterface.XmlToGameTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The files use the same format as the games from the futoshiki.org interface
 *
 * Created by matti on 19.09.2015.
 */
@Service(value = "futoshikiFromFileReader")
public class FutoshikiFromFileReader implements FutoshikiRepository {

    private XmlToGameTransformer gameTransformer;

    @Autowired
    public FutoshikiFromFileReader(XmlToGameTransformer gameTransformer) {
        this.gameTransformer = gameTransformer;
    }

    @Override
    public FutoshikiGame get(FutoshikiGameProperties properties) {
        final String baseName = "c:/Users/matti/Documents/projects/Futoshiki-Solver/games/futoshiki-";
        try {
            return gameTransformer.getGame(
                new FileInputStream(
                    new File(baseName + properties.getSize() + "-" + properties.getDifficulty() + ".xml")
                ),
                properties
            );
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
