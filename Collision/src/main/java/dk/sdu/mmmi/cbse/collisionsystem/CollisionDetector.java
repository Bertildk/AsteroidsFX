package dk.sdu.mmmi.cbse.collisionsystem;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.services.ICollisionHandler;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import java.util.ServiceLoader;


public class CollisionDetector implements IPostEntityProcessingService {
    ServiceLoader<ICollisionHandler> collisionHandlerServiceLoader = ServiceLoader.load(ICollisionHandler.class);
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }
                ICollisionHandler handler1 = getCollisionHandler(entity1.getEntityType());
                ICollisionHandler handler2 = getCollisionHandler(entity2.getEntityType());
                if (handler1 != null && handler2 != null && collides(entity1, entity2)) {
                    handler1.handleCollision(gameData, world, entity1, entity2);
                    handler2.handleCollision(gameData, world, entity2, entity1);
                }

            }
        }

}

private ICollisionHandler getCollisionHandler(EntityType entityType) {
        for (ICollisionHandler collisionHandler : collisionHandlerServiceLoader) {
            if(collisionHandler.getEntityType().equals(entityType)) {
                return collisionHandler;
            }
        }
        return null;
}

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

}