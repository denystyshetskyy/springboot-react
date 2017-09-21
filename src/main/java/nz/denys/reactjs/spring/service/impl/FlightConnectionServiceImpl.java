package nz.denys.reactjs.spring.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.netty.handler.codec.http.HttpResponseStatus;
import nz.co.airnz.flights.client.v90.model.FlightJson;
import nz.denys.reactjs.spring.service.FlightConnectionService;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.configuration2.Configuration;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Component
public class FlightConnectionServiceImpl implements FlightConnectionService {
    private static final Logger logger = LoggerFactory.getLogger(FlightConnectionServiceImpl.class);

    @Autowired
    private Configuration propertiesConfig;
    @Autowired
    private AsyncHttpClient client;

    @Override
    public FlightJson findFlightDetails(String compositeKey) {
        String url = propertiesConfig.getString("flight.api.url") + "flightsPrivate/" + compositeKey;
        RequestBuilder requestBuilder = new RequestBuilder().setUrl(url);
        requestBuilder.setMethod("GET");
        FlightJson svofFlight = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
        mapper.registerModule(new JavaTimeModule());
        ObjectReader reader = mapper.readerFor(FlightJson.class).withRootName("flight");
        try {
            Response response = client.prepareRequest(requestBuilder.build()).execute().get();
            if (response != null && response.getStatusCode() != HttpResponseStatus.NO_CONTENT.code()) {
                svofFlight = reader.readValue(response.getResponseBody());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return svofFlight;
    }
}
