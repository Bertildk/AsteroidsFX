package dk.sdu.mmmi.cbse.main;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;


public class Main extends Application {
    MicroServiceLauncher microServiceLauncher;
    public static void main(String[] args) {

        launch(Main.class);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            System.out.println("Shutting down...");
            MicroServiceLauncher microServiceLauncher = MicroServiceLauncher.getInstance();
            microServiceLauncher.stopMicroService();
            // Add any cleanup code here if needed
        }));
    }

    @Override
    public void start(Stage stage) throws Exception {
        microServiceLauncher = MicroServiceLauncher.getInstance();
        microServiceLauncher.startMicroService();
        Thread.sleep(3000);
        //giving time for the microservice to start

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);

        for (String BeanName : context.getBeanDefinitionNames()) {
            System.out.println(BeanName);
        }

        Game game = context.getBean(Game.class);
        game.start(stage);

    }

}
