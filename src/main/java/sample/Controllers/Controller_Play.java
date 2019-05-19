package sample.Controllers;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import sample.Models.Model_Player_Stats;
import sample.Vector2D;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static sample.score_saver.json_saver.write_json;
import static sample.score_saver.json_saver.writeJsonSimpleDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller_Play class as the gameplay controller
 */
public class Controller_Play {

    /**
     * Created logger
     */
    private static Logger logger = LoggerFactory.getLogger(Controller_Play.class);

    //region FXML_ELEMENTS

    @FXML
    public Pane Pane_GrandParent;
    @FXML
    public AnchorPane base_anchor;
    @FXML
    public Pane Pane_Parent;
    @FXML
    public Label label_score;
    @FXML
    public Label label_get_mouse_location;
    @FXML
    public Label label_starship_location;
    @FXML
    public Label label_speed_boost;
    @FXML
    public Button start_button;
    @FXML
    public ImageView star_1;
    @FXML
    public ImageView star_2;
    @FXML
    public ImageView star_3;
    @FXML
    public ImageView star_4;
    @FXML
    public ImageView star_5;
    @FXML
    public ImageView star_6;
    @FXML
    public ImageView star_7;
    @FXML
    public ImageView star_8;
    @FXML
    public ImageView star_9;
    @FXML
    public ImageView star_10;
    @FXML
    public ImageView star_11;
    @FXML
    public ImageView star_12;
    @FXML
    public ImageView star_13;
    @FXML
    public ImageView space_ship;
    @FXML
    public Label label_speed_boost_combo;
    @FXML
    public Label label_rockets_number;
    @FXML
    public ImageView icon_missle_image;
    @FXML
    public Button final_exit_button;
    @FXML
    public Button submit;
    @FXML
    public Label score_fx_label;
    @FXML
    public Label label_success;
    @FXML
    public Label error;
    @FXML
    public TextField textField_name;


    //endregion


    //region VARIABLES

    private static final Model_Player_Stats model_player_stats = new Model_Player_Stats();

    List<ImageView> missle = new ArrayList<>();
    List<ImageView> meteors = new ArrayList<>();
    List<ImageView> boosters= new ArrayList<>();
    List<ImageView> Stars_list = new ArrayList<>();

    /*
    private Integer score=0;
    private int number_of_missles =5;
    private double boost_time=0.0;
    private int millisec,speed_boost_combo,missle_timer=0;
    private double boost =2.0;
    */
    private int millisec,missle_timer=0;
    private static boolean a=false,d=false;
    private static Random random = new Random();


    //endregion


    //region STAR_METHODS

    /**
     *
     * @param star out of window or nor
     * @return simple boolean value true or false
     */
    public boolean get_play_is_star_out_of_window(double star){
        if (star<=0 || star>=600)
            return true;
        //if (star.getLayoutX()<=0 || star.getLayoutX()>=800)
        //    return true;
        return false;
    }

    /**
     * decrease the given item ammount
     * @param number this number we will decrease if that bigger than 0
     * @return the decreased number
     */
    public int decrease_item(int number){
        if (number>0)
            return --number;
        else return 0;
    }

    /**
     * Put the stars into a List(Imageview)
     */
    public void Stars_load_up(){
        Stars_list.add(star_1);
        Stars_list.add(star_2);
        Stars_list.add(star_3);
        Stars_list.add(star_4);
        Stars_list.add(star_5);
        Stars_list.add(star_6);
        Stars_list.add(star_7);
        Stars_list.add(star_8);
        Stars_list.add(star_9);
        Stars_list.add(star_10);
        Stars_list.add(star_11);
        Stars_list.add(star_12);
        Stars_list.add(star_13);

    }

    //endregion


    //region MOUSE
    /*
    public void get_play_mouse_cord_move(@NotNull MouseEvent e){
        label_get_mouse_location.setText("X ="+e.getX()+" Y = "+(Pane_GrandParent.getHeight() - e.getY()));
    }

    public void get_play_mouse_cord_drag(MouseEvent e){
        get_play_mouse_cord_move(e);
    }
    */
    //endregion


    //region METEOR

    /**
     * Creating a new Imageview with meteor's image
     * and add it both to its list and the to the Pane that we play
     */
    private void spawn_meteor(){
        ImageView imgv = new ImageView(new Image("images/meteor_transparent.png"));
        imgv.relocate(get_random_x_koordinate(3), 0);
        Pane_GrandParent.getChildren().add(imgv);
        meteors.add(imgv);
        logger.info("Warning!! A meteor entered the field");
    }

    /**
     *  Rotating and decrease the meteors horizontal value
     *  And check are there any collision that affect them
     *  If there are we remove both the meteor and the other object
     *  If it hit the ship, the game is over
     */
    private void move_meteor(){
        for (int i =0;i<meteors.size();i++){
            ImageView img = meteors.get(i);
            img.setRotate(img.getRotate()+10);
            img.setLayoutY(img.getLayoutY()+3);
            if (collision_detected(img.getLayoutX(),img.getLayoutY(),space_ship.getLayoutX(),space_ship.getLayoutY(),space_ship.getFitWidth()-20,space_ship.getFitHeight()-20)){
                remove_imageview(img,meteors);
                logger.info("Ship hit by a meteor");
                score_scren();
                break;
            }

            if (img.getLayoutY()>600) {
                remove_imageview(img, meteors);
                model_player_stats.score_increase(50);
                //score+=50;
                logger.info("Meteor left the field");
                break;
            }

            if (!missle.isEmpty()){
                for (int j =0;j<missle.size();j++){
                    ImageView img_missle = missle.get(j);
                    //System.out.println(img.getFitWidth());
                    if (collision_detected(img.getLayoutX(),img.getLayoutY(),img_missle.getLayoutX(),img_missle.getLayoutY(),67,67)){
                        remove_imageview(img,meteors);
                        remove_imageview(img_missle,missle);
                        model_player_stats.score_increase(150);
                        //score+=150;
                        logger.info("Meteor destroyed with missle");
                        //System.out.println("collision");
                    }

                }
            }
        }
    }



    //endregion


    //region MISSLE

    /**
     * if we have missles and missle launch is not on cooldown (missle_timer)
     * we call the launch_missle() method
     */
    public void missle_launch(){
        if (model_player_stats.getNumber_of_rockets()>0 && missle_timer<1)
            launch_missle();
    }

    /**
     * This method do the Missle Launch
     * and add 50 "missle_timer' so we can press space without launch
     * all of our missles in 1 sec
     */
    private void launch_missle(){
        ImageView imgv = new ImageView(new Image("images/boost_rocket_transparent.png"));
        Pane_GrandParent.getChildren().add(imgv);
        imgv.setLayoutY(465);
        imgv.setLayoutX(space_ship.getLayoutX()+36);
        missle.add(imgv);
        model_player_stats.rocked_successfully_launched();
        //number_of_missles=decrease_item(number_of_missles);
        missle_timer=50;
        //System.out.println("Generated Missle");
        logger.info("Missle launched");
    }

    /**
     * We go through the LAUNCHED missles list
     * and decrease their horizontal value
     * After we check are they out of window or not.
     * If any of them is, we call the remove_imageview()
     */
    private void move_missle(){
        for (int i=0;i<missle.size();i++){
            ImageView imgv=missle.get(i);
            imgv.setLayoutY(imgv.getLayoutY()-8);
            if (missle_out_of_field(imgv.getLayoutY())){
                remove_imageview(imgv,missle);
            }

        }
    }

    /**
     * We check is the missle out of window by below 0 horizontal value
     * IMPORTANT we check the launched missles only.
     * The booster type missles we check in the boosters checker method
     * 'cos we store those missles in the boosters list
     * @param imgvY the parameter name
     * @return if the missle 'above' our screen (horizontal value below 0) the mwthos return true
     */
    public boolean missle_out_of_field(double imgvY){
        if (imgvY<0)
            return true;
        else return false;
    }

    //endregion


    //region BOOSTER

    /**     *
     * @param object_1x the LayoutX() value of the object_1
     * @param object_1y the LayoutY() value of the object_1
     * @param object_2x the LayoutX() value of the object_2
     * @param object_2y the LayoutY() value of the object_2
     * @param object_2width the width uf object_2
     * @param object_2height the height of object_2
     * @return A boolean TRUE or FALSE depends on are the two object collisioned
     */
    public boolean collision_detected(double object_1x,double object_1y,double object_2x,double object_2y, double object_2width,double object_2height){
        return (Math.abs(object_1y - object_2y) < object_2height && Math.abs(object_1x - object_2x) < object_2width);
    }

    /**
     * If we pick up a booster here we get the type of booster
     * after pick up we remove the booster
     * both from screen and the boosters list
     * @param imageView imageview of the picked up booster
     */
    void pick_up_booster(@NotNull ImageView imageView){
        if (imageView.getId()=="missle") {
            model_player_stats.rocket_picked_up();
            //number_of_missles += 2;
            model_player_stats.score_increase(10);
            //score+=10;
            logger.info("Missle type booster picked up");
        }

        else {
            model_player_stats.speed_boost_combo_up();
            //speed_boost_combo++;
            label_speed_boost_combo.setText(model_player_stats.getSpeed_boost_combo() + "x");
            model_player_stats.speed_boost_picked_up(2.0,600);
            //boost += 2;
            //boost_time += 600;
            model_player_stats.score_increase(5);
            //score+=5;
            logger.info("Speed type booster picked up");
        }
        remove_imageview(imageView,boosters);
        //System.out.println("picked up");
    }

    /**
     * Some kind of destructor
     * We remove the actual imageview
     * both from screen and from his own list
     * @param imageviev the parameter is Imageview format
     * @param img we store the Imageview in this variable
     */
    private void remove_imageview (ImageView imageviev, @NotNull List<ImageView> img){
        Pane_GrandParent.getChildren().remove(imageviev);
        img.remove(imageviev);
    }

    /**
     * we increase the horizontal state value of the imageview
     * with a static number * the 'speed boost' value
     * this way we get the feel we are moving up
     * @param imageview  the parameter is Imageview format
     *                   ans store it the imageView variable
     */
    private void move_boosters(@NotNull ImageView imageview){
        imageview.setLayoutY(imageview.getLayoutY() + 2 * model_player_stats.getBoost_value());
    }

    /**
     * We go throught the boosters list.
     * for each element we call the
     * move_boosters()
     * collision_detected() -> pick_up_booster()
     * if the imageview is "out of window" we call the remove_imageview()
     */
    private void boosters() {

        for (int i = 0; i < boosters.size(); i++) {
            ImageView imageview = boosters.get(i);
            move_boosters(imageview);

            if (collision_detected(imageview.getLayoutX(),imageview.getLayoutY(),space_ship.getLayoutX(),space_ship.getLayoutY(),space_ship.getFitWidth(),space_ship.getFitHeight())) {
                pick_up_booster(imageview);
            }
            if (imageview.getLayoutY() > 600) {
                remove_imageview(imageview,boosters);
                //System.out.println("removed boost");
            }

        }

    }

    //endregion


    //region TIMERS'S_AND_TIME_VARIABLES'S_FUNCTIONS

    /**
     * Its one of the most important method.
     * Here we call
     * the events generator
     * and handle the
     * missle cooldown after launch
     * booster time
     */
    public void do_booster_timers(){
        millisec++;
        if (millisec>100){
            millisec=0;
            model_player_stats.score_increase(model_player_stats.getSpeed_boost_combo()+1);
            //score+=1*(speed_boost_combo+1);
            random_event();
        }
        if (model_player_stats.getSpeed_boost_time()>0)
            model_player_stats.decrease_speed_boost_time(1.5);
            //boost_time-=1.5;
        else {
            model_player_stats.setBoost_value(2.0);
        }
        if (missle_timer>0)
            missle_timer--;

    }

    //endregion


    //region EVENT_AND_RANDOMNUMBER_GENERATOR

    /**
     * every time we call this method
     * we generate an event
     *(speed booster or missle supply or meteor)
     * depends on the generated number     *
     */
    private void random_event(){
        int x = random.nextInt(100);
        if (x<80){
            if (x<15) {
                ImageView imageView = new ImageView(new Image("images/speed_boost_transparent.png"));
                imageView.relocate(get_random_x_koordinate(3), 0);
                imageView.setId("booster");
                Pane_GrandParent.getChildren().add(imageView);
                boosters.add(imageView);
                logger.info("Generated Speed type booster");
            }
            else if (x<30){
                ImageView imageView = new ImageView(new Image("images/boost_rocket_transparent.png"));
                imageView.relocate(get_random_x_koordinate(3), 0);
                imageView.setId("missle");
                Pane_GrandParent.getChildren().add(imageView);
                boosters.add(imageView);
                logger.info("Generated Missle type booster");
            }
            else {
                spawn_meteor();
                logger.info("Generated a Meteor");
            }
            //System.out.println("generated boost");
        }
    }

    /**
     * generate random horizontal coordinate depends on out move
     * @param b minimum value is 0
     * @return a random Int number
     */
    public int get_random_y_koordinate(int b){
        switch (b){
            case 0: return random.nextInt(600);  // up and (left or right)
            case 1: return random.nextInt(600)+200; // down and (left or right)
            default:return random.nextInt(800); // left or right
        }
    }

    /**
     * generate a random vertical coordinate depends on
     * our movement.
     * I mean simple right or simple left
     * or up and right or down and left etc...
     * @param b minimum value is 0
     * @return a random Int number
     */
    public int get_random_x_koordinate(int b){
        switch (b){
            case 0: return random.nextInt(500);  //left and (up or down)
            case 1: return random.nextInt(500)+100; // right  and (up or down)
            default:return random.nextInt(600); // up or down
        }
    }

    //endregion


    //region BUTTONS_AND_KEY_EVENTS

    /**
     * Check if the player pressed any of W,A,S,D,ESC button
     */
    //public void get_play_press_W(){w = !w;s = false;}
    //public void get_play_press_S(){s = !s;w = false;}

    /**
     * If 'A' pressed
     */
    public void get_play_press_A(){a = !a;d = false;}

    /**
     * If 'D' pressed
     */
    public void get_play_press_D(){d = !d;a = false;}

    /**
     * If 'ESC' pressed
     */
    public  void get_play_press_ESC(){Pane_Parent.setVisible(true);Play_Scene_Anim.stop();}

    /**
     * If in the game menu we choose the return
     */
    public void get_play_press_return(){Pane_Parent.setVisible(false);Play_Scene_Anim.start();}


    /**
     * After we pressed ESC during game and choosed the
     * EXIT button, this method will be called.
     * Lead us back to Main Menu
     *
     * We also call this method in the endgame phase where we can save our score
     * @throws IOException IOException
     */
    public void get_play_press_exit() throws IOException{
        Stage stage = (Stage)base_anchor.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/main.fxml"));
        stage.setTitle("Main menu");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/css_main.css");
        stage.setScene(scene);
        stage.show();
        logger.info("Went back to main menu from play screen");
    }


    /**
     * setup the score screen
     */
    private void score_scren(){
        Play_Scene_Anim.stop();
        base_anchor.getChildren().remove(Pane_GrandParent);
        get_rid_of_lists_elememts(missle);
        get_rid_of_lists_elememts(boosters);
        get_rid_of_lists_elememts(meteors);
        score_fx_label.setLayoutX(280);
        score_fx_label.setLayoutY(216);
        score_fx_label.setText("YOUR SCORE : "+model_player_stats.getScore());
        score_fx_label.setPrefWidth(200);
        score_fx_label.setPrefHeight(32);


        textField_name.setPromptText("Pilot name");
        textField_name.setLayoutX(302);
        textField_name.setLayoutY(288);


        label_success.setLayoutY(150);
        label_success.setLayoutX(300);
        label_success.setPrefHeight(25);
        label_success.setPrefWidth(170);

        error.setLayoutX(302);
        error.setLayoutY(310);


      //  label_success.setVisible(true);
        submit.setVisible(true);
        final_exit_button.setVisible(true);
        textField_name.setVisible(true);
        score_fx_label.setVisible(true);


    }

    /**
     * Submit Score button action method
     */
    public void press_submit(){
        if (textField_name.getText().length()<1 || textField_name.getText().length()>13){
            error.setText("Between 4 and 13 characters");
            error.setVisible(true);
            logger.error("Invalid Pilot Name on end game screen");
        }
       else{
           try {
               submit.setDisable(true);
               //writeJsonSimpleDemo("score.json");
               write_json(textField_name.getText(),model_player_stats.getScore());
               logger.info(textField_name.getText()+"'s score has been saved");
           }catch (Exception e){logger.error(e.getMessage());}
            label_success.setVisible(true);
       }



    }

    /**
     * Empty the actual list
     * @param img the list name here
     */
    private void get_rid_of_lists_elememts(@NotNull List<ImageView> img){
        if (!img.isEmpty())
            while (!img.isEmpty())
                img.remove(0);
    }

    //endregion


    //region METHODS_CALLED_IN_ANIMATION

    /**
     * Score updater
     */
    private void update_score(){
        label_score.setText(""+model_player_stats.getScore());
    }

    /**
     * Handle the ship movement depend on
     * did we pressed A or D
     */
    public void set_play_Move_ship(){
        int velocity_x=0;
        if (a) velocity_x=-4;
        if (d) velocity_x=4;
        //if (w) velocity_y=1;
        //if (s) velocity_y=-1;
        if (a && space_ship.getLayoutX()>0)
            space_ship.setLayoutX(space_ship.getLayoutX()+velocity_x);

        if (d && space_ship.getLayoutX()<712)
            space_ship.setLayoutX(space_ship.getLayoutX()+velocity_x);
    }

    /**
     * Moving the stars in the background
     */
    public void set_play_Mose_stars(){
        for (int i=0;i<Stars_list.size();i++){

            Stars_list.get(i).setLayoutY(Stars_list.get(i).getLayoutY()+1*model_player_stats.getBoost_value());
            if (get_play_is_star_out_of_window(Stars_list.get(i).getLayoutY())){
                Stars_list.get(i).setLayoutX(get_random_x_koordinate(3));
                Stars_list.get(i).setLayoutY(0);
            }
            //set_star_new_position(velocity_x,velocity_y,Stars_list.get(i));

        }
    }

    /**
     * Refresh the remaining time of the speed booster
     * If the boost id over we set the
     */
    private void booster_label(){
        if (model_player_stats.getSpeed_boost_time()<1) {


            label_speed_boost.setText("" + 0+"s");
            label_speed_boost_combo.setText("");
            model_player_stats.setSpeed_boost_combo(0);
            model_player_stats.setSpeed_boost_time(0);
        }
        else{

            label_speed_boost.setText(""+(int)model_player_stats.getSpeed_boost_time()/100+"s");
        }
    }

    /**
     * Missle Launching
     */
    private void missle_label(){
        label_rockets_number.setText(""+model_player_stats.getNumber_of_rockets());
    }


    //endregion

    /**
     * The Animation
     * Its the heart of the game
     * Its represent the time ellapse
     */
    AnimationTimer Play_Scene_Anim = new AnimationTimer() {
        @Override
        public void handle(long currentNanoTime) {

            model_player_stats.score_increase(2);
            //score++;
            set_play_Move_ship();
            set_play_Mose_stars();
            booster_label();
            missle_label();
            update_score();
            if (!meteors.isEmpty())move_meteor();
            if (!boosters.isEmpty())boosters();
            if (!missle.isEmpty())move_missle();
            do_booster_timers();
            //score_scren();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e){}
        }
    };



    // Experimental methods
    /*

     private double get_vector_lenght(Vector2D v){
        return Math.sqrt(Math.pow(v.x,2)+Math.pow(v.y,2));
    }

    private double get_two_vectors_scalar(Vector2D v1, Vector2D v2){
        return v1.x*v2.x+v1.y*v2.y;
    }

    private double get_two_vctors_angle(double scalar,double v1_lenght,double v2_lenght){
        //double sin = Math.sqrt(1-Math.pow(scalar/(v1_lenght*v2_lenght),2));
        return Math.acos(scalar/(v1_lenght*v2_lenght));
    }



    /*

    public void set_star_new_position(int velocity_x,int velocity_y,ImageView star ) {

        if (velocity_x!=0 && velocity_y!=0){
            if (a && w) {
                if (width_or_heigh()) {
                    star.setLayoutY(get_random_y_koordinate(0));
                    star.setLayoutX(0);
                }
                else {
                    star.setLayoutY(0);
                    star.setLayoutX(get_random_x_koordinate(0));
                }
            }
            if (a && s){
                if (width_or_heigh()) {
                    star.setLayoutY(get_random_y_koordinate(1));
                    star.setLayoutX(0);
                }
                else {
                    star.setLayoutY(600);
                    star.setLayoutX(get_random_x_koordinate(0));
                }

            }
            if (d && w){

                if (width_or_heigh()) {
                    star.setLayoutY(get_random_y_koordinate(0));
                    star.setLayoutX(800);
                }
                else {
                    star.setLayoutY(0);
                    star.setLayoutX(get_random_x_koordinate(1));
                }
            }
            if (d && s){

                if (width_or_heigh()) {
                    star.setLayoutY(get_random_y_koordinate(1));
                    star.setLayoutX(800);
                }
                else {
                    star.setLayoutY(600);
                    star.setLayoutX(get_random_x_koordinate(1));
                }
            }
        }

        if (velocity_x != 0 && velocity_y == 0){
            if (velocity_x == -1) {
                star.setLayoutX(800);
                star.setLayoutY(get_random_y_koordinate(3));
            }
            else{
                star.setLayoutY(get_random_y_koordinate(3));
                star.setLayoutX(0);
            }
        }

        if (velocity_y!=0 && velocity_x==0) {
            if (velocity_y == -1){
                star.setLayoutX(get_random_x_koordinate(3));
                star.setLayoutY(600);
            }
            else{
                star.setLayoutX(get_random_x_koordinate(3));
                star.setLayoutY(0);
            }
        }



    }

    public boolean width_or_heigh(){
        int width= random.nextInt(2);
        if (width==0) return true;
        else return false;
    }


    */

}
