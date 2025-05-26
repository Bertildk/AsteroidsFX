package dk.sdu.mmmi.cbse.weapons;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.ICollisionHandler;

public class BulletCollisionHandler implements ICollisionHandler {
    @Override
    public void handleCollision(GameData gameData, World world, Entity entity, Entity otherEntity) {
        if (otherEntity.getEntityType() == EntityType.WEAPON) {
            return;
        }
        BulletSPI spi = (BulletSPI) entity.getSPI("SPI");
        if (spi != null) {
            spi.explodeBullet(entity, world);
        }


        if (otherEntity.getEntityType() == EntityType.ASTEROID || otherEntity.getEntityType() == EntityType.ENEMY) {
            world.removeEntity(entity);
            world.removeEntity(otherEntity);
            return;
        }
        // If it collides with anything else, just remove the bullet
        world.removeEntity(entity);

    }

    @Override
    public EntityType getEntityType() {
        return EntityType.BULLET;
    }
}
