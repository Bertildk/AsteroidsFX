package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.ICollisionHandler;
import dk.sdu.mmmi.cbse.common.services.IScoreService;

import java.util.ServiceLoader;

public class AsteroidCollisionHandler implements ICollisionHandler {
    ServiceLoader<IAsteroidSplitter> asteroidSplitterServiceLoader = ServiceLoader.load(IAsteroidSplitter.class);
    ServiceLoader<IScoreService> scoreHandlerServiceLoader = ServiceLoader.load(IScoreService.class);

    @Override
    public void handleCollision(GameData gameData, World world, Entity entity, Entity otherEntity) {
        if (otherEntity.getEntityType() == EntityType.WEAPON){
            return;
        }
        if (otherEntity.getEntityType() == EntityType.ASTEROID) {
            return;
        }
        if(otherEntity.getEntityType() == EntityType.ENEMY) {
            world.removeEntity(entity);
            return;
        }
        // Increases only the score when an asteroid is hit by a bullet or player ship
        for (IScoreService scoreService : scoreHandlerServiceLoader) {
            scoreService.incrementScore();
        }
        for (IAsteroidSplitter asteroidSplitter : asteroidSplitterServiceLoader) {
            asteroidSplitter.createSplitAsteroid(entity, world);
        }
        world.removeEntity(entity);

    }

    @Override
    public EntityType getEntityType() {
         return EntityType.ASTEROID;
    }
}
