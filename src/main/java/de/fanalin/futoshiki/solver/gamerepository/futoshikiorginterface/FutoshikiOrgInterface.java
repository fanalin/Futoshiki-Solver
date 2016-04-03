package de.fanalin.futoshiki.solver.gamerepository.futoshikiorginterface;

import de.fanalin.futoshiki.solver.FutoshikiGame;
import de.fanalin.futoshiki.solver.FutoshikiGameProperties;
import de.fanalin.futoshiki.solver.gamerepository.FutoshikiRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(FutoshikiOrgInterface.class);

    public FutoshikiGame get(FutoshikiGameProperties properties) {
        logger.info("getting game from from futoshiki.org");

        HttpGet request = new HttpGet("http://www.futoshiki.org/get.cgi?size=4&difficulty=2&id=6584");

        ResponseHandler<FutoshikiGame> responseHandler = new ResponseHandler<FutoshikiGame>() {
            @Override
            public FutoshikiGame handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
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
}
