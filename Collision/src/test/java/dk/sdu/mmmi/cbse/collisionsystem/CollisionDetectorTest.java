package dk.sdu.mmmi.cbse.collisionsystem;
import dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class CollisionDetectorTest {
    @Test
    void TestCollisionOne(){
        Entity entity1 = mock(Entity.class);
        Entity entity2 = mock(Entity.class);

        //Creating two identical Mock Entities, with same location & Radius to test the collision
        // Stubbing methods to return the values so that the entities are identical
        when(entity1.getX()).thenReturn(Double.valueOf(400));
        when(entity1.getY()).thenReturn(Double.valueOf(300));
        when(entity1.getRadius()).thenReturn(Float.valueOf(10));

        when(entity2.getX()).thenReturn(Double.valueOf(400));
        when(entity2.getY()).thenReturn(Double.valueOf(300));
        when(entity2.getRadius()).thenReturn(Float.valueOf(10));

        CollisionDetector collisionDetector = new CollisionDetector();

        Assertions.assertTrue(collisionDetector.collides(entity1, entity2));
    }
    @Test
    void TestCollisionTwo(){
        Entity entity1 = mock(Entity.class);
        Entity entity2 = mock(Entity.class);

        when(entity1.getX()).thenReturn(Double.valueOf(400));
        when(entity1.getY()).thenReturn(Double.valueOf(300));
        when(entity1.getRadius()).thenReturn(Float.valueOf(10));

        when(entity2.getX()).thenReturn(Double.valueOf(400));
        when(entity2.getY()).thenReturn(Double.valueOf(300));
        // Entity2 is smaller than entity1, same location
        when(entity2.getRadius()).thenReturn(Float.valueOf(5));
        when(entity2.getRadius()).thenReturn(Float.valueOf(1));

        CollisionDetector collisionDetector = new CollisionDetector();

        Assertions.assertTrue(collisionDetector.collides(entity1, entity2));
    }
    @Test
    void TestCollisionThree(){
        Entity entity1 = mock(Entity.class);
        Entity entity2 = mock(Entity.class);

        when(entity1.getX()).thenReturn(Double.valueOf(100));
        when(entity1.getY()).thenReturn(Double.valueOf(100));
        when(entity1.getRadius()).thenReturn(Float.valueOf(10));

        when(entity2.getX()).thenReturn(Double.valueOf(400));
        when(entity2.getY()).thenReturn(Double.valueOf(300));
        when(entity2.getRadius()).thenReturn(Float.valueOf(10));

        CollisionDetector collisionDetector = new CollisionDetector();

        Assertions.assertFalse(collisionDetector.collides(entity1, entity2));
    }
}