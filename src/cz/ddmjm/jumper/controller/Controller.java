package cz.ddmjm.jumper.controller;

import cz.ddmjm.jumper.model.Platform;
import cz.ddmjm.jumper.model.Player;
import cz.ddmjm.jumper.model.Settings;

import java.util.Random;

public class Controller {

    private static Player player = new Player(50, 200);
    public static Player player2 = new Player(50, 250);

    public static void setPlayer2(Player player2) {
        Controller.player2 = player2;
    }
    private static Platform[] platforms = spawnPlatforms();
    
    
    
    public static void upPressed() {
        player.jump();
        player.setOnGround(false);
    }
    public static void upPressed2() {
        player2.jump();
        player2.setOnGround(false);
    }

    public static void leftPressed() {
        player.moveLeft();
    }
    public static void leftPressed2() {
        player2.moveLeft();
    }

    public static void rightPressed() {
        player.moveRight();
    }
    public static void rightPressed2() {
        player2.moveRight();
    }

    public static void leftReleased() {
        player.stopLeft();
    }
    public static void leftReleased2() {
        player2.stopLeft();
    }

    public static void rightReleased() {
        player.stopRight();
    }
    public static void rightReleased2() {
        player2.stopRight();
    }

    public static void timerChanged() {
        player.timerChanged();
        if (player.getY() <= Settings.PLAYER_HEIGHT) {
            player.setY(Settings.PLAYER_HEIGHT);
            player.setOnGround(true);
            player.setVelocityY(0);
        }
        
        boolean platformContact = false;
        if (!Settings.OWER_JUMP) {
        for (Platform platform : platforms) {
            if (player.getX() + Settings.PLAYER_WIDTH > platform.getX()
                    && player.getX() < platform.getX() + platform.getLength()
                    && player.getY() - Settings.PLAYER_HEIGHT <= platform.getY()
                    && player.getY() > platform.getY() - Settings.PLATFORM_HEIGHT) {
                platformContact = true;
                break;
            }
        }
        if (!platformContact && player.getY() != Settings.PLAYER_HEIGHT) {
            player.setOnGround(false);
            return;
        }

        for (Platform platform : platforms) {
            if (player.getX() + Settings.PLAYER_WIDTH > platform.getX()
                    && player.getX() < platform.getX() + platform.getLength()
                    && player.getY() - Settings.PLAYER_HEIGHT <= platform.getY()
                    && player.getY() > platform.getY() - Settings.PLATFORM_HEIGHT) {
                player.stop();

                if (player.getPrevY() > player.getY()
                        && player.getPrevY() - Settings.PLAYER_HEIGHT > platform.getY()
                        && player.getPrevX() + Settings.PLAYER_WIDTH > platform.getX()
                        && player.getPrevX() < platform.getX() + platform.getLength()) {

                    player.setY(platform.getY() + Settings.PLAYER_HEIGHT);
                    player.setOnGround(true);
                    player.setVelocityY(0);
                    continue;
                }

                if (player.getPrevY() < player.getY()
                        && player.getPrevY() < platform.getY() - Settings.PLATFORM_HEIGHT
                        && player.getPrevX() + Settings.PLAYER_WIDTH > platform.getX()
                        && player.getPrevX() < platform.getX() + platform.getLength()) {
                    player.setY(platform.getY() - Settings.PLATFORM_HEIGHT);
                    player.setVelocityY(0);
                    continue;
                }

                if (player.getPrevX() < player.getX()
                        && player.getPrevY() - Settings.PLAYER_HEIGHT <= platform.getY()
                        && player.getPrevY() >= platform.getY() - Settings.PLATFORM_HEIGHT
                        && player.getY() - Settings.PLAYER_HEIGHT != platform.getY()) {
                    player.setX(platform.getX() - Settings.PLAYER_WIDTH);
                    continue;
                }

                if (player.getPrevX() > player.getX()
                        && player.getPrevY() - Settings.PLAYER_HEIGHT <= platform.getY()
                        && player.getPrevY() >= platform.getY() - Settings.PLATFORM_HEIGHT
                        && player.getY() - Settings.PLAYER_HEIGHT != platform.getY()) {
                    player.setX(platform.getX() + platform.getLength());
                    continue;
                }
            }
        }
        }


    }
    public static void timerChanged2() {
        player2.timerChanged();
        if (player2.getY() <= Settings.PLAYER_HEIGHT) {
            player2.setY(Settings.PLAYER_HEIGHT);
            player2.setOnGround(true);
            player2.setVelocityY(0);
        }
        
        boolean platformContact = false;
        if (!Settings.OWER_JUMP) {
        for (Platform platform : platforms) {
            if (player2.getX() + Settings.PLAYER_WIDTH > platform.getX()
                    && player2.getX() < platform.getX() + platform.getLength()
                    && player2.getY() - Settings.PLAYER_HEIGHT <= platform.getY()
                    && player2.getY() > platform.getY() - Settings.PLATFORM_HEIGHT) {
                platformContact = true;
                break;
            }
        }
        if (!platformContact && player2.getY() != Settings.PLAYER_HEIGHT) {
            player2.setOnGround(false);
            return;
        }

        for (Platform platform : platforms) {
            if (player2.getX() + Settings.PLAYER_WIDTH > platform.getX()
                    && player2.getX() < platform.getX() + platform.getLength()
                    && player2.getY() - Settings.PLAYER_HEIGHT <= platform.getY()
                    && player.getY() > platform.getY() - Settings.PLATFORM_HEIGHT) {
                player2.stop();

                if (player2.getPrevY() > player2.getY()
                        && player2.getPrevY() - Settings.PLAYER_HEIGHT > platform.getY()
                        && player2.getPrevX() + Settings.PLAYER_WIDTH > platform.getX()
                        && player2.getPrevX() < platform.getX() + platform.getLength()) {

                    player2.setY(platform.getY() + Settings.PLAYER_HEIGHT);
                    player2.setOnGround(true);
                    player2.setVelocityY(0);
                    continue;
                }

                if (player2.getPrevY() < player2.getY()
                        && player2.getPrevY() < platform.getY() - Settings.PLATFORM_HEIGHT
                        && player2.getPrevX() + Settings.PLAYER_WIDTH > platform.getX()
                        && player2.getPrevX() < platform.getX() + platform.getLength()) {
                    player2.setY(platform.getY() - Settings.PLATFORM_HEIGHT);
                    player2.setVelocityY(0);
                    continue;
                }

                if (player2.getPrevX() < player2.getX()
                        && player2.getPrevY() - Settings.PLAYER_HEIGHT <= platform.getY()
                        && player2.getPrevY() >= platform.getY() - Settings.PLATFORM_HEIGHT
                        && player2.getY() - Settings.PLAYER_HEIGHT != platform.getY()) {
                    player2.setX(platform.getX() - Settings.PLAYER_WIDTH);
                    continue;
                }

                if (player2.getPrevX() > player2.getX()
                        && player2.getPrevY() - Settings.PLAYER_HEIGHT <= platform.getY()
                        && player2.getPrevY() >= platform.getY() - Settings.PLATFORM_HEIGHT
                        && player2.getY() - Settings.PLAYER_HEIGHT != platform.getY()) {
                    player2.setX(platform.getX() + platform.getLength());
                    continue;
                }
            }
        }
        }


    }

    public static Player getPlayer() {
        return player;
    }
    public static Player getPlayer2() {
        return player2;
    }

    public static Platform[] getPlatforms() {
        return platforms;
    }
    public static int count = 1;

    private static Platform[] spawnPlatforms() {
        Random random = new Random();

        Platform[] platforms = new Platform[Settings.PLATFORM_COUNTS];
        for (int i = 0; i < platforms.length; i++) {
            //platforms[i] = new Platform(DDM.randomInteger(50, 1000) * count, DDM.randomInteger(50, 500), DDM.randomInteger(100, 200));

            platforms[i] = new Platform((random.nextInt(1000 - 50 + 1) + 50) * count, (random.nextInt(500 - 50 + 1) + 50), random.nextInt(200 - 100 + 1) + 100);
            count++;
        }
        return platforms;
    }

}
