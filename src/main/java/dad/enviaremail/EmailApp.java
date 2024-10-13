package dad.enviaremail;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmailApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        RootController rootController = new RootController();

        Stage calculadoraStage = new Stage();
        calculadoraStage.setTitle("Enviar Email");
        calculadoraStage.setScene(new Scene(rootController.getRoot()));
        calculadoraStage.show();

    }
}

