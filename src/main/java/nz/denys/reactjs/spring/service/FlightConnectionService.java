package nz.denys.reactjs.spring.service;

import nz.co.airnz.flights.client.v90.model.FlightJson;
import org.springframework.stereotype.Service;

@Service
public interface FlightConnectionService {
    FlightJson findFlightDetails(String compositeKey);
}
