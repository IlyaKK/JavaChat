package client3;

import client3.controllers.ViewController;
import client3.models.Network;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.chat.User;

import java.util.List;

public class EchoClient extends Application {
    private static final List<User> clients = List.of(
            new User("martin", "1111", "Мартин_Некотов"),
            new User("boris", "2222", "Борис_Николаевич"),
            new User("gena", "3333", "Гендальф_Серый")
    );

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EchoClient.class.getResource("views/chat-view.fxml"));
        Parent root = loader.load();
        stage.setTitle("Chat");
        stage.setScene(new Scene(root));
        stage.show();

        Network network = new Network();
        network.connect();

        ViewController viewController = loader.getController();
        viewController.setNetwork(network);

        network.waitMessage(viewController);
    }

    public static void main(String[] args) {
        launch(args);
    }
}