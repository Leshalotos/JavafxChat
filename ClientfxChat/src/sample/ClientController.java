package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.*;
import java.io.*;

public class ClientController implements ConnectionListener{

    private String IPConstructor;
    private int PORTConstructor;
    private String nameConstructor;
    private Connection connection;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField IP;

    @FXML
    private TextField name;

    @FXML
    private TextField PORTSERVER;

    @FXML
    private Button StartChat;

    @FXML
    private TextField textarea;

    @FXML
    private TextArea textwindow;

    @FXML
    void initialize() {
        StartChat.setOnAction(event -> {
            nameConstructor = textarea.getText();
            IPConstructor = IP.getText();
            PORTConstructor = Integer.parseInt(PORTSERVER.getText());
            try {
                connection = new Connection(this, IPConstructor, PORTConstructor);
            } catch (IOException e) {
                e.printStackTrace();
                printMessage("Connection exception: " + e);
            }
            printMessage("Client name: " + fieldName.getText() + " Server port: " + PORT + " Server ip: " + IP );
        });
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String post = textwindow.getText();
        if(post.equals("")) return;
        textwindow.setText(null);
        connection.sendString(nameConstructor.getText() + ": " + post);
    }

    @Override
    public synchronized void onConnectionReady(Connection connection) {
        printMessage("Connection with server exist...");
    }

    @Override
    public synchronized void onReceiveString(Connection connection, String string) {
        printMessage(string);
    }

    @Override
    public synchronized void onDisconneckt(Connection connection) {
        printMessage("User disconnected: " + connection);
    }

    @Override
    public synchronized void onException(Connection connection, Exception e) {
        printMessage("Connection exception: " + e);
    }

    private synchronized void printMessage(String message) {
        Platform.runLater(new Runnable()  {
            @Override
            public void run() {
                textarea.append(message + "\n");
                textarea.setCaretPosition(textarea.getDocument().getLength());
            }
        });
    }
}


