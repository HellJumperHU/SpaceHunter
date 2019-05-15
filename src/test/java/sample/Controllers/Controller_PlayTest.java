package sample.Controllers;

import javafx.scene.image.Image;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.text.html.ImageView;

import static org.junit.Assert.*;

public class Controller_PlayTest {

    private Controller_Play play;
    @Before
    public void setUp() throws Exception {
        play = new Controller_Play();
    }

    @After
    public void tearDown() throws Exception {
        play=null;
    }

    @Test
    public void get_play_is_star_out_of_window() {
        double starY_position=200.0;
        assertEquals(false,play.get_play_is_star_out_of_window(starY_position));
        starY_position=599;
        assertEquals(false,play.get_play_is_star_out_of_window(starY_position));
        starY_position=600;
        assertEquals(true,play.get_play_is_star_out_of_window(starY_position));
        starY_position=601;
        assertEquals(true,play.get_play_is_star_out_of_window(starY_position));
        starY_position=1;
        assertEquals(false,play.get_play_is_star_out_of_window(starY_position));
        starY_position=0;
        assertEquals(true,play.get_play_is_star_out_of_window(starY_position));
        starY_position=-1;
        assertEquals(true,play.get_play_is_star_out_of_window(starY_position));



    }

    @Test
    public void decrease_item() {
        int item_numbers=2;
        item_numbers = play.decrease_item(item_numbers);
        assertEquals(1,item_numbers);
        item_numbers = play.decrease_item(item_numbers);
        assertEquals(0,item_numbers);
        item_numbers = play.decrease_item(item_numbers);
        assertEquals(0,item_numbers);
    }

    @Test
    public void missle_out_of_field() {
        double missleY_position = 200;
        assertEquals(false, play.missle_out_of_field(missleY_position));
        missleY_position = 0.1;
        assertEquals(false, play.missle_out_of_field(missleY_position));
        missleY_position = 0.0;
        assertEquals(false, play.missle_out_of_field(missleY_position));
        missleY_position = -0.1;
        assertEquals(true, play.missle_out_of_field(missleY_position));
    }

    @Test
    public void collision_detected() {
        double object_1_x=100;
        double object_1_y=100;
        double object_2_x=300;
        double object_2_y=300;
        double object_2_width=50;
        double object_2_height=60;
        assertEquals(false,play.collision_detected(object_1_x,object_1_y,object_2_x,object_2_y,object_2_width,object_2_height));
        object_1_x=400;
        assertEquals(false,play.collision_detected(object_1_x,object_1_y,object_2_x,object_2_y,object_2_width,object_2_height));
        object_1_x=300;
        object_1_y=0;
        assertEquals(false,play.collision_detected(object_1_x,object_1_y,object_2_x,object_2_y,object_2_width,object_2_height));
        object_1_x=300;
        object_1_y=400;
        assertEquals(false,play.collision_detected(object_1_x,object_1_y,object_2_x,object_2_y,object_2_width,object_2_height));
        object_1_y=359;
        assertEquals(true,play.collision_detected(object_1_x,object_1_y,object_2_x,object_2_y,object_2_width,object_2_height));
        object_1_y=300;
        assertEquals(true,play.collision_detected(object_1_x,object_1_y,object_2_x,object_2_y,object_2_width,object_2_height));
        object_1_y=301;
        assertEquals(true,play.collision_detected(object_1_x,object_1_y,object_2_x,object_2_y,object_2_width,object_2_height));
        object_1_y=330;
        object_1_x=280;
        assertEquals(true,play.collision_detected(object_1_x,object_1_y,object_2_x,object_2_y,object_2_width,object_2_height));
        object_1_x=320;
        assertEquals(true,play.collision_detected(object_1_x,object_1_y,object_2_x,object_2_y,object_2_width,object_2_height));

    }
}