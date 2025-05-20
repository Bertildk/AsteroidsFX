package dk.sdu.mmmi.cbse.common.services;

public interface IScoreService {
    int getScore();
    int getHighScore();
    void incrementScore();
    void setHighScore();
    void setScore(int score);
}
