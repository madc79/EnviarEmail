package dad.enviaremail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.apache.commons.mail.*;

import java.io.IOException;


public class RootController {

    private Alert enviadoAlert;
    private Alert errorAlert;

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
        boolean conexion = conexionText.isSelected();

        try {
            Email email = new SimpleEmail();
            email.setHostName(nombre);
            email.setSmtpPort(Integer.parseInt(puerto));
            email.setAuthenticator(new DefaultAuthenticator(remitente, contrasena));
            email.setSSLOnConnect(conexion);
            email.setFrom(remitente);
            email.setSubject(asunto);
            email.setMsg(mensaje);
            email.addTo(destinatario);
            email.send();

            enviadoAlert = new Alert(Alert.AlertType.INFORMATION);
            enviadoAlert.setTitle("Mensaje enviado");
            enviadoAlert.setHeaderText("Mensaje enviado con éxito a " + destinatarioText.getText());
            enviadoAlert.showAndWait();

        } catch (Exception e) {
            errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("No se pudo enviar el email");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }


    }

    @FXML
    void OnVaciarButton(ActionEvent event) {
        asuntoText.setText("");
        nombreText.setText("");
        puertoText.setText("");
        remitenteText.setText("");
        contraseñaText.setText("");
        destinatarioText.setText("");
        mensajeText.setText("");
        conexionText.setSelected(false);

    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }
}
