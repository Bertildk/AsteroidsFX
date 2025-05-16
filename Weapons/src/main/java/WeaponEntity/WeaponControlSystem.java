package WeaponEntity;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class WeaponControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        Random rndDecider = new Random();
        int rng = rndDecider.nextInt(1000);
        if (rng == 999){
            WeaponPlugin wpnPlugin = new WeaponPlugin();
            world.addEntity(wpnPlugin.createWeapon(gameData));
        }
    }
}
