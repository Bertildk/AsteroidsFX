package dk.sdu.mmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.playersystem.Player;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.weapons.WeaponManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class PlayerControlSystemTest {

    @Test
    void TestMovement(){
        // creating Player object to test Movement
        WeaponManager weaponManager = new WeaponManager();
        Player player = new Player(weaponManager);

        //Creating World object to add player to the game
        World world = new World();
        world.addEntity(player);

        //Creating mock objects
        GameData mockGamedata = mock(GameData.class);
        GameKeys mockKeys = mock(GameKeys.class);

        // Creating Stubs to simulate the behaviour of the player to test Movement, not rotation
        when(mockGamedata.getKeys()).thenReturn(mockKeys);
        when(mockKeys.isDown(GameKeys.UP)).thenReturn(true);
        when(mockGamedata.getDisplayWidth()).thenReturn(800);
        when(mockGamedata.getDisplayHeight()).thenReturn(600);

        // Seting the Players postition to the middle
        player.setX(mockGamedata.getDisplayWidth()/2);
        player.setY(mockGamedata.getDisplayHeight()/2);
        // 0 degrees is right,
        // 90 degrees is up,
        // 180 degrees is left,
        // 270 degrees is down

        //creating the controlsystem object to call process method
        PlayerControlSystem playerControlSystem = new PlayerControlSystem();

        // Testing movement in all directions of the player
        player.setRotation(90); // Facing up
        playerControlSystem.process(mockGamedata, world);

        Assertions.assertEquals(400, player.getX());
        Assertions.assertEquals(301, player.getY());
        Assertions.assertEquals(90, player.getRotation());

        player.setRotation(0); // Facing right
        playerControlSystem.process(mockGamedata, world);

        Assertions.assertEquals(401, player.getX());
        Assertions.assertEquals(301, player.getY());
        Assertions.assertEquals(0, player.getRotation());

        player.setRotation(270); // Facing down
        playerControlSystem.process(mockGamedata, world);

        Assertions.assertEquals(401, player.getX());
        Assertions.assertEquals(300, player.getY());
        Assertions.assertEquals(270, player.getRotation());

        player.setRotation(180); // Facing left
        playerControlSystem.process(mockGamedata, world);

        Assertions.assertEquals(400, player.getX());
        Assertions.assertEquals(300, player.getY());
        Assertions.assertEquals(180, player.getRotation());

        //Improvement move the 4 player movement tests to individual test methods...
    }

}
