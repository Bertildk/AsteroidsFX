import WeaponEntity.WeaponCollisionHandler;

module Weapon {
    exports dk.sdu.mmmi.cbse.weapons;
    exports WeaponEntity;
    requires Common;
    requires CommonBullet;
    requires java.desktop;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides dk.sdu.mmmi.cbse.common.bullet.BulletSPI with dk.sdu.mmmi.cbse.weapons.BazookaControlSystem, dk.sdu.mmmi.cbse.weapons.StandardBulletControlSystem;
    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with WeaponEntity.WeaponPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with WeaponEntity.WeaponControlSystem;
    provides dk.sdu.mmmi.cbse.common.services.ICollisionHandler with dk.sdu.mmmi.cbse.weapons.BulletCollisionHandler, WeaponCollisionHandler;
}