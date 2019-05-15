package sample.score_class;

public class score_data {

    private String name;
    private String score;



    public score_data(String name, String score){
        this.name=name;
        this.score=score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }
}
