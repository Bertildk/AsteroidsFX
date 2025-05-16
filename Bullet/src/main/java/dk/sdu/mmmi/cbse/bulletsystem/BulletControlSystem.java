package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import javax.swing.*;
import java.util.ArrayList;

public class BulletControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        ArrayList<Entity> bulletsToRemove = new ArrayList<>();
        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * bullet.getSpeed());
            bullet.setY(bullet.getY() + changeY * bullet.getSpeed());

            if (bullet.getX() < 0) {
                bulletsToRemove.add(bullet);
            }

            if (bullet.getX() > gameData.getDisplayWidth()) {
                bulletsToRemove.add(bullet);
            }

            if (bullet.getY() < 0) {
                bulletsToRemove.add(bullet);
            }

            if (bullet.getY() > gameData.getDisplayHeight()) {
                bulletsToRemove.add(bullet);
            }
        }
        for (Entity bullet : bulletsToRemove) {
            world.removeEntity(bullet);
        }
    }


}
