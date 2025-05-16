package dk.sdu.mmmi.cbse.weapons;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

public class WeaponManager
{
    private BulletSPI currentWeapon;

    public void setCurrentWeapon(String currentWeapon) {
        if("bullet".equals(currentWeapon)) {
            this.currentWeapon = new StandardBulletControlSystem();
        } else if("bazooka".equals(currentWeapon)) {
            this.currentWeapon = new BazookaControlSystem();
        }
    }
    public BulletSPI getCurrentWeapon() {
        return currentWeapon;
    }

}
