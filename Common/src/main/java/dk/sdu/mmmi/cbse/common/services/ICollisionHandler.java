package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface ICollisionHandler {

    public void handleCollision(GameData gameData, World world, Entity entity, Entity otherEntity);

    public EntityType getEntityType();

}
