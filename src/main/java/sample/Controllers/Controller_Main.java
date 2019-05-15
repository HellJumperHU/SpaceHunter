package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller_Main {

    /**
     * On button click event, appear a new window with the sat xy.fxml as Parent
     *
     public void get_main_highscore_new_window(ActionEvent event) throws IOException{
     Parent root = FXMLLoader.load(getClass().getResource("/sample/highscore.fxml"));
     Stage stage = new Stage();
     stage.setTitle("Highscores");
     stage.setScene(new Scene(root));
     stage.show();
     }
     */
    @FXML
    public void get_main_highscore_same_window(ActionEvent event) throws IOException{
        Button btn = (Button)event.getSource();
        Stage stage = (Stage)btn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/highscore.fxml"));
        stage.setTitle("Highscores");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/css_play.css");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void get_main_close(ActionEvent event) throws IOException{
        Button btn = (Button)event.getSource();
        Stage stage=(Stage)btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void get_main_play_same_window(ActionEvent event) throws IOException {
        Button btn = (Button)event.getSource();
        Stage stage = (Stage)btn.getScene().getWindow();
        FXMLLoader load_play_scene = new FXMLLoader(getClass().getResource("/sample/play.fxml"));
        Parent root =load_play_scene.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/css_play.css");
        load_play_scene.<Controller_Play>getController().Stars_load_up();
        load_play_scene.<Controller_Play>getController().Play_Scene_Anim.start();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    //case W: load_play_scene.<Controller_Play>getController().get_play_press_W();break;
                    //case S: load_play_scene.<Controller_Play>getController().get_play_press_S();break;
                    case A: load_play_scene.<Controller_Play>getController().get_play_press_A();break;
                    case D: load_play_scene.<Controller_Play>getController().get_play_press_D();break;
                    case ESCAPE: load_play_scene.<Controller_Play>getController().get_play_press_ESC();break;
                    case SPACE:load_play_scene.<Controller_Play>getController().missle_launch(); break;
                    default: break;
                }
            }
        });

        stage.setTitle("Play");
        stage.setScene(scene);
        stage.show();
    }

}
