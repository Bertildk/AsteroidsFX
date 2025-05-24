package WeaponEntity;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class WeaponPlugin implements IGamePluginService {
    private Entity Weapon;
    @Override
    public void start(GameData gameData, World world) {
        Weapon = createWeapon(gameData);
        world.addEntity(Weapon);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Weapon.class)) {
            world.removeEntity(entity);
        }
    }
    public Entity createWeapon(GameData gamedata) {
        Entity weapon = new Weapon();
        Random rnd = new Random();
        weapon.setEntityType(EntityType.WEAPON);
        weapon.setPolygonCoordinates(
                0, 6,
                2, 2,
                6, 0,
                2, -2,
                0, -6,
                -2, -2,
                -6, 0,
                -2, 2
        );        weapon.setX(rnd.nextInt(gamedata.getDisplayWidth()));
        weapon.setY(rnd.nextInt(gamedata.getDisplayHeight()));
        weapon.setRadius(8);
        weapon.setRotation(0);
        return weapon;
    }
}
