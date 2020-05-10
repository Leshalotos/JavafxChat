package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class ServerController {

    private String servername;
    private int PORT;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button StartServer;

    @FXML
    private TextField serverName;

    @FXML
    private Label lbl1;

    @FXML
    private TextField PORTSERVER;

    @FXML
    void initialize(ActionEvent event) {

    }

    @FXML
    void initialize() {
        StartServer.setOnAction(event -> {
            lbl1.setText("ChatServer started!");
            servername = serverName.getText();
            PORT = Integer.parseInt(PORTSERVER.getText());
            System.out.println("Server started! " + servername + " " + PORT);
            Server server = new Server(servername, PORT);
        });
    }
}

