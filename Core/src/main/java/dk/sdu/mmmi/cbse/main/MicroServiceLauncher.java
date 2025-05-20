package dk.sdu.mmmi.cbse.main;

import java.io.IOException;

public final class MicroServiceLauncher {
    private Process process;
    private static MicroServiceLauncher INSTANCE;



    private MicroServiceLauncher() {

    }
    public static MicroServiceLauncher getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MicroServiceLauncher();
        }
        return INSTANCE;
    }
    public void startMicroService() {
        if (process != null && process.isAlive()) {
            System.out.println("Microservice is already running.");
            return;
        }
        try {
            this.process = new ProcessBuilder("java", "-jar", "ScoringAPI/target/scoringsystem-0.0.1-SNAPSHOT.jar").inheritIO().start();
            System.out.println("Attempting to start Microservice.");
            if(process.isAlive()) {
                System.out.println("Microservice started successfully.");
            } else {
                System.out.println("Microservice failed to start.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopMicroService() {
        if (process != null) {
            process.destroy();
            System.out.println("Attempting to stop Microservice API.");
        }
        if (process == null) {
            System.out.println("Microservice is not running.");
        }
    }
}
