package client;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import commands.Command;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthController
{
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passField;
    @FXML
    public Label statusLabel;

    @FXML
    public void continueClick(final ActionEvent actionEvent) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        String result = Client.getInstance().sendMsg(String.format("%s;%s;%s"
                , Command.LOGIN
                , loginField.getText()
                , passField.getText()
        )).toString();
        if (result.equals(Command.OK)) {
            Scene scene = ((Node)actionEvent.getSource()).getScene();
            Parent root = FXMLLoader.load(this.getClass().getResource("/main.fxml"));
            scene.setRoot(root);
        } else {
            statusLabel.setText(result);
        }

    }
}
