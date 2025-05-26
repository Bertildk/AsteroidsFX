import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module EnemyShip {
    exports dk.sdu.mmmi.cbse;
    requires Common;
    requires CommonBullet;
    requires Bazooka;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    uses dk.sdu.mmmi.cbse.common.services.IScoreService;
    provides IGamePluginService with dk.sdu.mmmi.cbse.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.EnemyControlSystem;
    provides dk.sdu.mmmi.cbse.common.services.ICollisionHandler with dk.sdu.mmmi.cbse.EnemyCollisionHandler;
}








