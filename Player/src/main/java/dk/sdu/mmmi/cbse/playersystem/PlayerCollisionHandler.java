package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.ICollisionHandler;
import dk.sdu.mmmi.cbse.weapons.WeaponManager;

public class PlayerCollisionHandler implements ICollisionHandler {

    @Override
    public void handleCollision(GameData gameData, World world, Entity entity, Entity otherEntity) {
        //Weapon collision does not kill the player and the Bazooka bullet fragments also does not kill the player
        if (otherEntity.getEntityType() != EntityType.WEAPON && !(otherEntity.getName().equals("explodingBullet"))) {
            world.removeEntity(entity);
            return;

        }

        //if the other entity IS a weapon this happens
        if (entity.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) entity;
            WeaponManager weaponManager = player.getWeaponManager();
            weaponManager.setCurrentWeapon("bazooka");
            return;
        }
    }
    @Override
    public EntityType getEntityType() {
        return EntityType.PLAYER;
    }
}
