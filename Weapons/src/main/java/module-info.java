import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.weapons.BazookaControlSystem;

module Bazooka {
    exports dk.sdu.mmmi.cbse.weapons;
    exports WeaponEntity;
    requires Common;
    requires CommonBullet;
    requires java.desktop;
    provides dk.sdu.mmmi.cbse.common.bullet.BulletSPI with dk.sdu.mmmi.cbse.weapons.BazookaControlSystem, dk.sdu.mmmi.cbse.weapons.StandardBulletControlSystem;
    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with WeaponEntity.WeaponPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with WeaponEntity.WeaponControlSystem;
}