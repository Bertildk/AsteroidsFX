import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
    uses IAsteroidSplitter;
    exports dk.sdu.mmmi.cbse.asteroid;
    requires Common;
    requires CommonAsteroids;
    uses dk.sdu.mmmi.cbse.common.services.IScoreService;
    provides dk.sdu.mmmi.cbse.common.services.ICollisionHandler with dk.sdu.mmmi.cbse.asteroid.AsteroidCollisionHandler;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroid.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroid.AsteroidProcessor;
    provides IAsteroidSplitter with dk.sdu.mmmi.cbse.asteroid.AsteroidSplitterImpl;
}