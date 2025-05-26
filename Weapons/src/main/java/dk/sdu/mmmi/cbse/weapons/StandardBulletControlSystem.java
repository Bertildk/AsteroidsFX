package dk.sdu.mmmi.cbse.weapons;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class StandardBulletControlSystem implements BulletSPI {
    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setEntityType(EntityType.BULLET);
        bullet.setSPI("SPI", this);
        bullet.setSpeed(3);
        bullet.setPolygonCoordinates(2, -2, 2, 2, -2, 2, -2, -2);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * 10);
        bullet.setY(shooter.getY() + changeY * 10);
        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(1);
        return bullet;
    }



    @Override
    public void explodeBullet(Entity e, World w) {

    }
}
