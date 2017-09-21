package nz.denys.reactjs.spring.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Theme("valo")
@SpringUI
@SpringViewDisplay
public class VaadinUi extends UI implements ViewDisplay{

    public static final String DELIMITER = "_";
    private static final String DATE_FORMAT = "ddMMMyyyy";

    private Panel springViewDisplay;
    private String airlineCode;
    private String flightNumber;
    private String suffix;
    private LocalDate date;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout root = new VerticalLayout();
        FileResource resource = new FileResource(new File(getClass().getClassLoader().getResource("airnz-logo.png").getFile()));
        root.setIcon(resource);
        root.setB
        root.setSizeFull();
        Label label = new Label();
        label.setHeight("10%");
        label.setIcon(resource);
        root.addComponent(new Label("Flight search"));
        setContent(root);
        TextField airlineCodeField = new TextField("Airline Code");
        TextField flightNumberField = new TextField("Flight Number");
        TextField suffixField = new TextField("Suffix");
        DateField dateField = new DateField();
        airlineCodeField.addValueChangeListener(valueChangeEvent -> airlineCode = valueChangeEvent.getValue());
        flightNumberField.addValueChangeListener(valueChangeEvent -> flightNumber = valueChangeEvent.getValue());
        suffixField.addValueChangeListener(valueChangeEvent -> suffix = valueChangeEvent.getValue());
        dateField.setDateFormat("ddMMMyyyy");
        dateField.addValueChangeListener(valueChangeEvent -> date = valueChangeEvent.getValue());

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(airlineCodeField);
        navigationBar.addComponent(flightNumberField);
        navigationBar.addComponent(suffixField);
        navigationBar.addComponent(dateField);
        navigationBar.addComponent(createFlightSearchButton("Search Flight"));
        root.addComponent(navigationBar);

        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        root.addComponent(springViewDisplay);
        root.setExpandRatio(springViewDisplay, 1.0f);
    }

    @Override
    public void showView(View view) {
        springViewDisplay.setContent((Component) view);
    }

    private Button createFlightSearchButton(String caption) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        // If you didn't choose Java 8 when creating the project, convert this
        // to an anonymous listener class
        button.addClickListener(event -> getUI().getNavigator().navigateTo(FlightDetailsView.VIEW_NAME + "/" + buildCompositeKey()));
        return button;
    }

    private String buildCompositeKey() {
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(completeString(airlineCode, 3)).append(completeString(flightNumber, 4))
                .append(completeString(suffix, 1)).append(formattedDate(date));
        return keyBuilder.toString();
    }

    public static String completeString(String value, int expectedLength) {
        value = StringUtils.defaultIfEmpty(value, "");
        if (value.length() == expectedLength) {
            return value;
        } else {
            while (value.length() < expectedLength) {
                value = value + DELIMITER;
            }
            return value;
        }
    }

    public static String formattedDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

}
