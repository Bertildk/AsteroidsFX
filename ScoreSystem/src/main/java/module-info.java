import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.scoresystem.APIHandler.ScoreHandler;
import dk.sdu.mmmi.cbse.scoresystem.APIHandler.ScorePlugin;

module ScoreSystem {
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires Common;
    requires java.net.http;

    uses dk.sdu.mmmi.cbse.common.services.IScoreService;

    provides dk.sdu.mmmi.cbse.common.services.IScoreService with dk.sdu.mmmi.cbse.scoresystem.APIHandler.ScoreHandler;
    provides IGamePluginService with ScorePlugin;

    opens dk.sdu.mmmi.cbse.scoresystem.APIHandler to spring.core, spring.beans, spring.context;
    exports dk.sdu.mmmi.cbse.scoresystem.APIHandler;

}