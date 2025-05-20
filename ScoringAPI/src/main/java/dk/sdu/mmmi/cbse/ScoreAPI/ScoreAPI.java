package dk.sdu.mmmi.cbse.ScoreAPI;

import dk.sdu.mmmi.cbse.common.services.IScoreService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoreAPI {
    private final Score score = Score.getInstance();

    public static void main(String[] args) {
        SpringApplication.run(ScoreAPI.class, args);
    }
    @PostMapping("/sethighscore")
    public void setHighScore() {
        if(score.getScore() > score.getHighScore()){
            score.setHighScore(score.getScore());
        }
    }
    @GetMapping("/score")
    public int getScore() {
        return score.getScore();
    }
    @GetMapping("/highscore")
    public int getHighScore() {
        return score.getHighScore();
    }
    @PostMapping("/increment")
    public void incrementScore() {
        score.setScore(score.getScore() + 1);
    }

    @PostMapping("/setscore")
    public void setScore(@RequestParam("score") int score) {
        this.score.setScore(score);
    }
}
