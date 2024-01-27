package cz.ddmjm.jumper;

import cz.ddmjm.jumper.controller.Controller;
import cz.ddmjm.jumper.model.Platform;
import cz.ddmjm.jumper.model.Settings;
import cz.ddmjm.jumper.view.Keyboard;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //DDM.randomInteger(0, 10);
        Group root = new Group();
        Scene scene = new Scene(root, 780, 600);
        Keyboard.getInstance().setupHandlers(scene);

        ImageView backGround = new ImageView();
        backGround.setImage(new Image("cz/ddmjm/jumper/images/background.png"));
        root.getChildren().add(backGround);

        ImageView secondBackGround = new ImageView();
        secondBackGround.setImage(new Image("cz/ddmjm/jumper/images/background.png"));
        secondBackGround.setTranslateX(Settings.FULL);
        root.getChildren().add(secondBackGround);

        final ImageView playerView = new ImageView();
        playerView.setImage(new Image("cz/ddmjm/jumper/images/Rambo1.png"));
        root.getChildren().add(playerView);

        final ImageView playerView2 = new ImageView();
        playerView2.setImage(new Image("cz/ddmjm/jumper/images/Rambos1.png"));
        root.getChildren().add(playerView2);

        final Group platforms = new Group();
        for (Platform platform : Controller.getPlatforms()) {
            Rectangle platformView = new Rectangle();
            platformView.setWidth(platform.getLength());
            platformView.setHeight(Settings.PLATFORM_HEIGHT);
            platformView.setTranslateX(platform.getX());
            platformView.setTranslateY(780 - platform.getY());
            platformView.setFill(Color.BLACK);
            platforms.getChildren().add(platformView);
        }
        root.getChildren().add(platforms);

        primaryStage.setTitle("Jumping game");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        startTimer(30, new Runnable() {
            int ramboChange = 1;
            int ramboWalkSpead = 1;

            @Override
            public void run() {
                Controller.timerChanged();
                playerView2.setTranslateX(Controller.getPlayer2().getX());
                playerView2.setTranslateY(Controller.getPlayer2().getY());
                if (Controller.getPlayer().getVelocityX() > 0) {
                    Settings.STOP_WALKING = false;
                }

                Settings.OWER_JUMP_TIMER++;
                if (Settings.OWER_JUMP_TIMER == 30) {

                    Settings.OWER_JUMP_TIMER = 0;
                    Settings.OWER_JUMP_CORRECT = true;

                }

                backGround.setTranslateX(backGround.getTranslateX() - Controller.getPlayer().getVelocityX());
                secondBackGround.setTranslateX(secondBackGround.getTranslateX() - Controller.getPlayer().getVelocityX());
                platforms.setTranslateX(100 - Controller.getPlayer().getX());
                playerView.setTranslateX(100);
                playerView.setTranslateY(780 - Controller.getPlayer().getY());
                playerView2.setTranslateX(50);
                playerView2.setTranslateY(780 - Controller.getPlayer().getY());
                //neustale chozeni
                Controller.rightPressed();

                if (backGround.getTranslateX() < -Settings.FULL) {
                    backGround.setTranslateX(backGround.getTranslateX() + 2 * Settings.FULL);
                }
                if (secondBackGround.getTranslateX() < -Settings.FULL) {
                    secondBackGround.setTranslateX(backGround.getTranslateX() + 2 * Settings.FULL);
                }
                /* if (Controller.getPlayer().getX() >= Settings.FULL) {
                 backGround.setTranslateX(1670);
                 Settings.FULL += Settings.FULL; 

                 }*/

                if (ramboWalkSpead < 4) {

                    if (Controller.getPlayer().getVelocityX() > 0 && ramboChange < 4 && ramboWalkSpead == 3 && Settings.STOP_WALKING == false) {
                        playerView.setImage(new Image("cz/ddmjm/jumper/images/Rambo" + ramboChange + ".png"));
                        ramboChange++;
                        //owerjump konec
                        if (Settings.OWER_JUMP && ramboWalkSpead == 3) {

                            Settings.OWER_JUMP = false;
                        }
                    }

                    if (ramboChange == 4) {
                        ramboChange = 1;
                    }
                    if (ramboWalkSpead == 3) {
                        ramboWalkSpead = 1;
                    }
                    ramboWalkSpead++;
                }

            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void startTimer(final int interval, final Runnable action) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException ex) {
                    }
                    javafx.application.Platform.runLater(action);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

}
