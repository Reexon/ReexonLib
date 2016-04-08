package it.reexon.reexon.lib.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


public class SecurityApplication extends Application
{

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        final BorderPane pane = new BorderPane();

        final HBox hbox = new HBox();
        final TextField field = new TextField();
        HBox.setHgrow(field, Priority.ALWAYS);
        hbox.getChildren().addAll(new Label("Search:"), field, new Button("Go"));

        pane.setTop(hbox);
        stage.setTitle("Scurity");
        stage.setScene(new Scene(pane));
        stage.setX(0);
        stage.setY(0);

        stage.show();
    }
}
