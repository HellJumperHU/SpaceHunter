package sample.Models;

/**
 * EXPERIMENTAL
 */
public class Model_Score {
    private Integer score =0;

    public void IncreaseScore(int pluspoint){score = score+pluspoint;}

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
