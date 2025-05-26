package WeaponEntity;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.ICollisionHandler;

public class WeaponCollisionHandler implements ICollisionHandler {

    @Override
    public void handleCollision(GameData gameData, World world, Entity entity, Entity otherEntity) {
        if (otherEntity.getEntityType() == EntityType.PLAYER) {
            world.removeEntity(entity);
        }
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.WEAPON;
    }
}
