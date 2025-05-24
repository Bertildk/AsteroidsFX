package dk.sdu.mmmi.cbse.collisionsystem;

import WeaponEntity.Weapon;
import dk.sdu.mmmi.cbse.Enemy;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.services.IScoreService;
import dk.sdu.mmmi.cbse.playersystem.Player;
import dk.sdu.mmmi.cbse.weapons.WeaponManager;

import java.util.ServiceLoader;


public class CollisionDetector implements IPostEntityProcessingService {
    ServiceLoader<IAsteroidSplitter> asteroidSplitterServiceLoader = ServiceLoader.load(IAsteroidSplitter.class);
    ServiceLoader<IScoreService> scoreHandlerServiceLoader = ServiceLoader.load(IScoreService.class);
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }
                if (this.collides(entity1, entity2)) {
                    EntityType type1 = entity1.getEntityType();
                    EntityType type2 = entity2.getEntityType();

                    if ((type1 == EntityType.ASTEROID && type2 == EntityType.BULLET) ||
                            (type1 == EntityType.BULLET && type2 == EntityType.ASTEROID)) {

                        Entity asteroidEntity;
                        Entity bulletEntity;

                        if (type1 == EntityType.ASTEROID) {
                            asteroidEntity = entity1;
                            bulletEntity = entity2;
                        } else {
                            asteroidEntity = entity2;
                            bulletEntity = entity1;
                        }

                        createAsteroidSplit(asteroidEntity, world);
                        bulletCollision(bulletEntity, world);
                        incrementScore();
                        world.removeEntity(asteroidEntity);
                        world.removeEntity(bulletEntity);

                    } else if ((type1 == EntityType.PLAYER && type2 == EntityType.ENEMY) ||
                            (type1 == EntityType.ENEMY && type2 == EntityType.PLAYER)) {

                        world.removeEntity(entity1);
                        world.removeEntity(entity2);

                    } else if ((type1 == EntityType.ENEMY && type2 == EntityType.BULLET) ||
                            (type1 == EntityType.BULLET && type2 == EntityType.ENEMY)) {

                        Entity bulletEntity;
                        if (type1 == EntityType.BULLET) {
                            bulletEntity = entity1;
                        } else {
                            bulletEntity = entity2;
                        }

                        bulletCollision(bulletEntity, world);
                        world.removeEntity(entity1);
                        world.removeEntity(entity2);

                    } else if ((type1 == EntityType.ENEMY && type2 == EntityType.ASTEROID) ||
                            (type1 == EntityType.ASTEROID && type2 == EntityType.ENEMY)) {

                        world.removeEntity(entity1);
                        world.removeEntity(entity2);

                    } else if ((type1 == EntityType.PLAYER && type2 == EntityType.BULLET) ||
                            (type1 == EntityType.BULLET && type2 == EntityType.PLAYER)) {

                        Entity bulletEntity;
                        if (type1 == EntityType.BULLET) {
                            bulletEntity = entity1;
                        } else {
                            bulletEntity = entity2;
                        }

                        bulletCollision(bulletEntity, world);

                        if (!bulletEntity.getName().equals("explodingBullet")) {
                            world.removeEntity(entity1);
                            world.removeEntity(entity2);
                            UpdateHighscore();
                        }

                    } else if ((type1 == EntityType.PLAYER && type2 == EntityType.ASTEROID) ||
                            (type1 == EntityType.ASTEROID && type2 == EntityType.PLAYER)) {

                        UpdateHighscore();
                        world.removeEntity(entity1);
                        world.removeEntity(entity2);

                    } else if ((type1 == EntityType.PLAYER && type2 == EntityType.WEAPON) ||
                            (type1 == EntityType.WEAPON && type2 == EntityType.PLAYER)) {

                        if (type1 == EntityType.WEAPON) {
                            WeaponManager weaponManager = ((Player) entity2).getWeaponManager();
                            weaponManager.setCurrentWeapon("bazooka");
                            world.removeEntity(entity1);
                        } else {
                            WeaponManager weaponManager = ((Player) entity1).getWeaponManager();
                            weaponManager.setCurrentWeapon("bazooka");
                            world.removeEntity(entity2);
                        }
                    }
                    if(!(IsValidEntityType(type1) && IsValidEntityType(type2))) {
                        // Removing entities if they are not valid types
                        world.removeEntity(entity1);
                        world.removeEntity(entity2);
                    }

                }
            }
        }
    }
    private boolean IsValidEntityType(EntityType type) {
        return type == EntityType.ASTEROID || type == EntityType.BULLET || type == EntityType.ENEMY || type == EntityType.PLAYER || type == EntityType.WEAPON;
    }
    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

    public void UpdateHighscore(){
        for(IScoreService scoreHandler : scoreHandlerServiceLoader){
            scoreHandler.setHighScore();
        }
    }
    public void bulletCollision(Entity e, World world){
        BulletSPI spi = (BulletSPI) e.getSPI("SPI");
        if (spi != null) {
            spi.explodeBullet(e, world);
        }
    }
    public void createAsteroidSplit(Entity e, World world){
        for(IAsteroidSplitter asteroidSplitter : asteroidSplitterServiceLoader){
            // If the two entities are an asteroid and a bullet, remove both
            // and create a new asteroid
            asteroidSplitter.createSplitAsteroid(e, world);
        }
    }
    public void incrementScore(){
        for(IScoreService scoreHandler : scoreHandlerServiceLoader){
            scoreHandler.incrementScore();
        }
    }



}
