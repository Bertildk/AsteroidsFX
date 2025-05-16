package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.weapons.WeaponManager;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);
        world.addEntity(enemy);

    }
    
    public Entity createEnemy(GameData gameData) {
        WeaponManager weaponManager = new WeaponManager();
        Entity enemyShip = new Enemy(weaponManager);
        Enemy enemy = (Enemy) enemyShip;
        enemy.getWeaponManager().setCurrentWeapon("bullet");

        Random rnd = new Random();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setX(rnd.nextInt(600 - 200) + 200);
        enemyShip.setY(rnd.nextInt(600 - 200) + 200);
        enemyShip.setRadius(8);
        enemyShip.setRotation(rnd.nextInt(90));
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
