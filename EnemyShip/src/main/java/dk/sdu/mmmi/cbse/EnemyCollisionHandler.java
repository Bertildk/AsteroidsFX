package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.ICollisionHandler;
import dk.sdu.mmmi.cbse.common.services.IScoreService;

import java.util.ServiceLoader;

public class EnemyCollisionHandler implements ICollisionHandler {
    ServiceLoader<IScoreService> scoreHandlerServiceLoader = ServiceLoader.load(IScoreService.class);

    @Override
    public void handleCollision(GameData gameData, World world, Entity entity, Entity otherEntity) {
        if (otherEntity.getEntityType() == EntityType.WEAPON) {
            return;
        } else if (otherEntity.getEntityType() == EntityType.ENEMY) {
           return;
        } else if (otherEntity.getEntityType() == EntityType.ASTEROID) {
            world.removeEntity(entity);
            return;
        }

        // Increases the score when an enemy ship collides with a player ship or bullet
        for (IScoreService scoreService : scoreHandlerServiceLoader) {
            scoreService.incrementScore();
        }
        world.removeEntity(entity);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.ENEMY;
    }
}
