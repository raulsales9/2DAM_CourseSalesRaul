module com.raul.conversorobj.interfaceobj {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.raul.conversorobj.interfaceobj to javafx.fxml;
    exports com.raul.conversorobj.interfaceobj;
    requires org.hibernate.orm.core;
}
