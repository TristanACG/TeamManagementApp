module com.example.npcbasketball {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.npcbasketball to javafx.fxml;
    exports com.example.npcbasketball;
    exports com.example.npcbasketball.Controllers;
    opens com.example.npcbasketball.Controllers to javafx.fxml;
}