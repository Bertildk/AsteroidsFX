package dk.sdu.mmmi.cbse.weapons;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Random;


public class BazookaControlSystem implements BulletSPI {

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bazooka = new Bullet();
        bazooka.setSPI("SPI", this);
        bazooka.setEntityType(EntityType.BULLET);
        bazooka.setSpeed(2);
        bazooka.setSize(4);
        bazooka.setPolygonCoordinates(4, -4, 4, 4, -4, 4, -4, -4);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bazooka.setX(shooter.getX() + changeX * 10);
        bazooka.setY(shooter.getY() + changeY * 10);
        bazooka.setRotation(shooter.getRotation());
        bazooka.setRadius(2);
        return bazooka;
    }

    @Override
    public void explodeBullet(Entity e, World world) {
        Random rnd = new Random();
        if (e.getSize() < 3){
            System.out.println("Bullet too small to explode" + e.getSize());
        }else{
            for (int i = 0; i < 8; i++) {
                Entity bullet = new Bullet();
                bullet.setEntityType(EntityType.BULLET);
                bullet.setSPI("SPI", this);
                bullet.setSpeed(2);
                float size = e.getSize() / 3;
                bullet.setSize(size);
                bullet.setName("explodingBullet");
                bullet.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
                bullet.setX(e.getX() + rnd.nextInt(10) - 5);
                bullet.setY(e.getY() + rnd.nextInt(10) - 5);
                bullet.setRadius(size);
                bullet.setRotation((double) 360 /i);
                world.addEntity(bullet);

            }
        }
    }
}
