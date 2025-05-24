package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IScoreService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.stream.Collectors.toList;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends GameData {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private int score;

    private Text text;

    List<IGamePluginService> iGamePluginServices;
    List<IEntityProcessingService> iEntityProcessingServices;
    List<IPostEntityProcessingService> iPostEntityProcessingServices;
    IScoreService scoreService;
    public Game(List<IGamePluginService> iGamePluginServices, List<IEntityProcessingService> iEntityProcessingServices,
                List<IPostEntityProcessingService> iPostEntityProcessingServices, IScoreService iScoreService) {
        this.iGamePluginServices = iGamePluginServices;
        this.iEntityProcessingServices = iEntityProcessingServices;
        this.iPostEntityProcessingServices = iPostEntityProcessingServices;
        this.scoreService = iScoreService;
    }


    public void start(Stage window) throws Exception {
        int score = scoreService.getScore();
        text = new Text(10, 20, "Destroyed asteroids: " + score + " Highscore: " + scoreService.getHighScore());
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(text);

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }

        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            System.out.println("Adding entity polygon for: " + entity.getClass().getSimpleName());
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }
        Image backgroundImage = new Image(getClass().getResourceAsStream("/background.png"));
        BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, null);
        gameWindow.setBackground(new javafx.scene.layout.Background(background));
        render();
        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();

    }

    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
                updateScore();
            }

        }.start();
    }


    private void updateScore(){
        Random rnd = new Random();
        if(rnd.nextInt(100) < 95){
            text.setText("Destroyed asteroids: " + scoreService.getScore() + " Highscore: " + scoreService.getHighScore());
        }
        // Method implememted so that the API isn't called every frame
    }
    private void update() {
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity polygonEntity : polygons.keySet()) {
            if(!world.getEntities().contains(polygonEntity)){
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
            if (entity.getClass().getSimpleName().equals("Asteroid")) {
                polygon.setFill(Color.GRAY);
            } else if (entity.getClass().getSimpleName().equals("Player")) {
                polygon.setFill(Color.GREENYELLOW);
            } else if (entity.getClass().getSimpleName().equals("Enemy")) {
                polygon.setFill(Color.RED);
            } else if (entity.getClass().getSimpleName().equals("Bullet")) {
                polygon.setFill(Color.BEIGE);
            } else if(entity.getClass().getSimpleName().equals("Weapon")){
                polygon.setFill(Color.YELLOW);
            }
            else {
                polygon.setFill(Color.GREEN);
            }

        }

    }

    /*
    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
    */

     //Updated to use SPRING DI
    private List<IGamePluginService> getPluginServices() {
        return iGamePluginServices;
    }
    private List<IEntityProcessingService> getEntityProcessingServices() {
        return iEntityProcessingServices;
    }
    private List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return iPostEntityProcessingServices;
    }
}
