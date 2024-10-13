package dad.enviaremail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.apache.commons.mail.*;

import java.io.IOException;


public class RootController {

    @FXML
    private TextField asuntoText;

    @FXML
    private VBox botonesBox;

    @FXML
    private Button cerrarButton;

    @FXML
    private CheckBox conexionText;

    @FXML
    private TextField contraseñaText;

    @FXML
    private TextField destinatarioText;

    @FXML
    private Button enviarButton;

    @FXML
    private GridPane interfazPane;

    @FXML
    private TextArea mensajeText;

    @FXML
    private TextField nombreText;

    @FXML
    private TextField puertoText;

    @FXML
    private TextField remitenteText;

    @FXML
    private BorderPane root;

    @FXML
    private Button vaciarButton;

    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void OnCerrarButton(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void OnEnviarButton(ActionEvent event) throws EmailException {

        String remitente = remitenteText.getText();
        String contrasena = contraseñaText.getText();
        String destinatario = destinatarioText.getText();
        String asunto = asuntoText.getText();
        String mensaje = mensajeText.getText();
        String nombre = nombreText.getText();
        String puerto = puertoText.getText();

        Email email = new SimpleEmail();
        email.setHostName(nombre);
        email.setSmtpPort(Integer.parseInt(puerto));
        email.setAuthenticator(new DefaultAuthenticator(remitente, contrasena));
        email.setSSLOnConnect(true);
        email.setFrom(remitente);
        email.setSubject(asunto);
        email.setMsg(mensaje);
        email.addTo(destinatario);
        email.send();

    }

    @FXML
    void OnVaciarButton(ActionEvent event) {
        asuntoText.setText("");

        conexionText.setSelected(false);


    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
}
