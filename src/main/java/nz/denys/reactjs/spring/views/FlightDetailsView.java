package nz.denys.reactjs.spring.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import nz.co.airnz.flights.client.v90.model.FlightJson;
import nz.denys.reactjs.spring.service.FlightConnectionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = FlightDetailsView.VIEW_NAME)
public class FlightDetailsView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "flightDetailsView";

    @Autowired
    private FlightConnectionService flightConnectionService;
    private FlightJson flightDetails;
    private Grid<FlightJson> grid;

    @PostConstruct
    void init() {
        grid = new Grid<>();
//        grid.setSizeFull();
        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String compositeKey = event.getParameters();
        flightDetails = flightConnectionService.findFlightDetails(compositeKey);
        if (flightDetails != null) {
            grid.setItems(flightDetails);
            grid.addColumn(FlightJson::getSchdlFlightNumber).setCaption("Flight number");
            grid.addColumn(FlightJson::getSchdlDepDateTimeLcl).setCaption("Departure date");
            grid.addColumn(FlightJson::getSchdlDepAirportIata).setCaption("Departure airport");
            grid.addColumn(FlightJson::getSchdlArrivalAirportIata).setCaption("Arrival airport");
            grid.addColumn(FlightJson::getSchdlArrivalDateTimeLcl).setCaption("Arrival date");
            grid.setSizeFull();
//            addComponent(grid);
        }
    }
}
