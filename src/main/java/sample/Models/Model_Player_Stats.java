package sample.Models;

public class Model_Player_Stats {
    private Integer score =0 ;
    private double speed_boost_time=0.0;
    private Integer speed_boost_combo=0;
    private Integer number_of_rockets=5;
    private double boost_value =2.0;

    public void score_increase(int points){score =score+points;}

    public void speed_boost_picked_up(double increase_boost_value_by,Integer increase_boost_time_by){ speed_boost_time = speed_boost_time+increase_boost_time_by ; boost_value=boost_value+increase_boost_value_by ;}
    public void speed_boost_combo_up(){speed_boost_combo++;}
    public void decrease_speed_boost_time(double value){speed_boost_time = speed_boost_time-value;}
    public void rocket_picked_up(){number_of_rockets=number_of_rockets+2;}
    public void rocked_successfully_launched(){number_of_rockets = number_of_rockets-1;}

    public Integer getScore() {
        return score;
    }

    public double getSpeed_boost_time() {
        return speed_boost_time;
    }

    public Integer getSpeed_boost_combo() {
        return speed_boost_combo;
    }

    public Integer getNumber_of_rockets() {
        return number_of_rockets;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSpeed_boost_time(int speed_boost_time) {
        this.speed_boost_time = speed_boost_time;
    }

    public void setSpeed_boost_combo(int speed_boost_combo) {
        this.speed_boost_combo = speed_boost_combo;
    }

    public void setNumber_of_rockets(int number_of_rockets) {
        this.number_of_rockets = number_of_rockets;
    }

    public double getBoost_value() {
        return boost_value;
    }

    public void setBoost_value(double boost_value) {
        this.boost_value = boost_value;
    }

}

