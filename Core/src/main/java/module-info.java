module Core {
    requires spring.context;
    requires spring.core;
    requires spring.beans;

    requires Common;
    requires javafx.graphics;
    requires Collision;
    requires CommonAsteroids;
    requires Asteroid;
    requires java.desktop;
    requires ScoreSystem;
    opens dk.sdu.mmmi.cbse.main to javafx.graphics,spring.core;
    exports dk.sdu.mmmi.cbse.main;
    uses dk.sdu.mmmi.cbse.common.services.IScoreService;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
}


