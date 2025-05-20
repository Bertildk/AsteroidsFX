package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IScoreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.BeanProperty;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Configuration
public class GameConfig {



    @Bean
    public Game game() {
        return new Game(gamePlugins(), entityProcessingServices(), postEntityProcessingServices(), scoreServices());
    }
    @Bean
    public IScoreService scoreServices() {
        return ServiceLoader.load(IScoreService.class).findFirst().orElseThrow(() ->
                new RuntimeException("No IScoreService found"));

    }
    @Bean
    public List<IGamePluginService> gamePlugins() {
        return ServiceLoader.load(IGamePluginService.class).stream()
                .map(ServiceLoader.Provider::get)
                .collect(toList());
    }
    @Bean
    public List<IEntityProcessingService> entityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream()
                .map(ServiceLoader.Provider::get)
                .collect(toList());
    }
    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream()
                .map(ServiceLoader.Provider::get)
                .collect(toList());
    }

}
