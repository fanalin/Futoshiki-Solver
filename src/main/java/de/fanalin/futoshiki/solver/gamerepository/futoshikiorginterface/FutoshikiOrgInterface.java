package de.fanalin.futoshiki.solver.gamerepository.futoshikiorginterface;

import com.github.vbauer.herald.annotation.Log;
import de.fanalin.futoshiki.solver.game.FutoshikiGame;
import de.fanalin.futoshiki.solver.game.FutoshikiGameProperties;
import de.fanalin.futoshiki.solver.gamerepository.FutoshikiRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Repository for Futoshiki games, which uses futoshiki.org to fetch games from
 */
@Service(value = "futoshikiOrgRepository")
public class FutoshikiOrgInterface implements FutoshikiRepository {

    private HttpClient httpClient;

    private XmlToGameTransformer gameTransformer;

    @Autowired
    public FutoshikiOrgInterface(HttpClient httpClient, XmlToGameTransformer gameTransformer) {
        this.httpClient = httpClient;
        this.gameTransformer = gameTransformer;
    }

    @Log
    private Logger logger;

    public FutoshikiGame get(FutoshikiGameProperties properties) {
        logger.info("getting game from from futoshiki.org");

        HttpGet request = new HttpGet(getUrl(properties));

        ResponseHandler<FutoshikiGame> responseHandler = new ResponseHandler<FutoshikiGame>() {
            @Override
            public FutoshikiGame handleResponse(HttpResponse httpResponse) throws IOException {
                try {
                    return gameTransformer.getGame(httpResponse.getEntity().getContent(), properties);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        try {
            return httpClient.execute(request, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getUrl(FutoshikiGameProperties props) {
        int randomInt = new Double(Math.random()*1000).intValue();
        StringBuilder sb = new StringBuilder("http://www.futoshiki.org/get.cgi?")
            .append("size=").append(props.getSize())
            .append("&difficulty=").append(props.getDifficulty())
            .append("&id=").append(randomInt);
        return sb.toString();
    }
}
