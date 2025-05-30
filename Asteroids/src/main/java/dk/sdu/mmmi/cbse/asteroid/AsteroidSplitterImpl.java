package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Random;

/**
 *
 * @author corfixen
 */
public class AsteroidSplitterImpl implements IAsteroidSplitter {

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        if(e.getSize() < 4) {
            System.out.println("Asteroid too small to split size of Asteroid" + e.getSize());
        }else {
            for (int i = 0; i < 2; i++) {
                System.out.println("Creating split asteroid with size: " + e.getSize() / 2);
                Entity asteroid = new Asteroid();
                asteroid.setEntityType(e.getEntityType());
                Random rnd = new Random();
                float size = e.getSize() / 2;
                asteroid.setSize(size);
                asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
                asteroid.setX(e.getX() + rnd.nextInt(10));
                asteroid.setY(e.getY() + rnd.nextInt(10));
                asteroid.setRadius(size);
                asteroid.setRotation(rnd.nextInt(90));
                world.addEntity(asteroid);
            }
        }


    }

}
