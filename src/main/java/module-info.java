module de.bbq.wochenbericht {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires com.sun.xml.bind;
    requires java.base;
    
    opens de.bbq.wochenbericht to javafx.fxml, java.xml.bind;
    exports de.bbq.wochenbericht;
}
