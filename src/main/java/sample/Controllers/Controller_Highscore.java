package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static sample.score_saver.json_loader.load_json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Controller_Highscore {

    @FXML
    Label label_1st_name;
    @FXML
    Label label_2nd_name;
    @FXML
    Label label_3rd_name;
    @FXML
    Label label_1st_score;
    @FXML
    Label label_2nd_score;
    @FXML
    Label label_3rd_score;

    public void refresh_table() {
        List<String> name_list = new ArrayList<>();
        List<Integer> score_list = new ArrayList<>();
        load_json(name_list, score_list);
        List<Integer> score_base = score_list;

        if (!name_list.isEmpty()) {
            score_list.sort(Comparator.naturalOrder());
            if (name_list.size()<2){
                label_1st_score.setText(""+score_list.get(0));
                label_1st_name.setText(""+name_list.get(0));
                label_2nd_name.setText("-");
                label_2nd_score.setText("-");
                label_3rd_name.setText("-");
                label_3rd_score.setText("-");
            }

        }

    }



    public void get_highscore_main_same_window(ActionEvent event) throws IOException{
        Button btn = (Button)event.getSource();
        Stage stage = (Stage)btn.getScene().getWindow();
        Parent root =FXMLLoader.load(getClass().getResource("/sample/main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/css_main.css");
        stage.setTitle("Main menu");
        stage.setScene(scene);
    }

}