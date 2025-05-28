package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.lang.module.ModuleDescriptor;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PluginLoader {
    ModuleLayer pluginLayer;
    public void pluginLoader(){
        //Loads all JARS in the plugins directory
        ModuleFinder finder  =  ModuleFinder.of(Paths.get("plugins"));

        //creates a list of all module names found
        List<String> moduleNames = finder
                .findAll()
                .stream()
                .map(ModuleReference::descriptor)
                .map(ModuleDescriptor::name)
                .collect(Collectors.toList());

        // resolves the configuration for the modules found against the current JVM modules in the boot layer
        Configuration pluginConfig = ModuleLayer.boot()
                .configuration()
                .resolve(finder, ModuleFinder.of(), moduleNames);

        // creates a new module layer with the new plugins and loads them during runtime
        pluginLayer = ModuleLayer
                .boot()
                .defineModulesWithOneLoader(pluginConfig, ClassLoader.getSystemClassLoader());

        //prints the name of all modules found
        for (String moduleName : moduleNames) {
            // Loads the module and prints its name
            System.out.println("Loaded plugin: " + moduleName);
        }
    }

    public ModuleLayer getPluginLayer() {
        return pluginLayer;
    }

    /**
     This allows the main method to create an insctance of PluginLoader
     and load the plugins then get the new plugin layer
     and use it to load the modules from the plugins directory.

     Example use:

        PluginLoader pluginLoader = new PluginLoader();
        pluginLoader.pluginLoader();
        ModuleLayer pluginLayer = pluginLoader.getPluginLayer();

        ServiceLoader<IGamePluginService> gamePluginServices = ServiceLoader.load(IGamePluginService.class, pluginLayer);
        for (IGamePluginService service : gamePluginServices) {
            service.start();
        }

     So that new entities can be added to the game from plugins
     Just need a module-info and provide the interfaces that are loaded
     it is not used in the Project but this is a Proof of concept

     Was tested by moving a file to the plugins directory and seeing that the module name was printed
     */

    public static void main(String[] args) {
        PluginLoader pluginLoader = new PluginLoader();
        pluginLoader.pluginLoader();
        /*  Example output: Loaded plugin: ScoreSystem */
        ModuleLayer newLayer = pluginLoader.getPluginLayer();
        System.out.println(newLayer.modules());
    }
}
