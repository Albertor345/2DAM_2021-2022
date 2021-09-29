module WebStore.AlbertoGonzalez {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.logging;
    requires snakeyaml;
    requires jakarta.inject.api;
    requires jakarta.enterprise.cdi.api;

    opens gui.main to javafx.graphics;
    opens fx.controllers to javafx.fxml, model;
    opens model to lombok;
    opens dao to model;
    opens services to model;
    opens configuration to snakeyaml;

}