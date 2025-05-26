import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    exports dk.sdu.mmmi.cbse.collisionsystem;
    requires Common;
    uses dk.sdu.mmmi.cbse.common.services.ICollisionHandler;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
}