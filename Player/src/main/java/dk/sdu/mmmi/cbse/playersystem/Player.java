package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.weapons.WeaponManager;

/**
 *
 * @author Emil
 */
public class Player extends Entity {
    private long lastShotTime;
    private WeaponManager weaponManager;

    public Player(WeaponManager weaponManager) {
        this.weaponManager = weaponManager;
    }
    public WeaponManager getWeaponManager() {
        return weaponManager;
    }

    public long getLastShotTime() {
    return lastShotTime;
    }

    public long getFireRate() {
        return 100L;
    }

    public void setLastShotTime(long currentTime) {
    this.lastShotTime = currentTime;
    }

}
