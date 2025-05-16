package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.weapons.WeaponManager;

public class Enemy extends Entity {
    private WeaponManager weaponManager;
    public Enemy(WeaponManager weaponManager) {
        this.weaponManager = weaponManager;
    }
    public WeaponManager getWeaponManager() {
        return weaponManager;
    }
}
