package dk.sdu.mmmi.cbse.common.data;

public class Score {
    public int score;
    public int highScore;

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    public void getHighScore(int highScore) {
        this.highScore = highScore;
    }
    public void incrementScore(int increment) {
        this.score += increment;
    }
}
