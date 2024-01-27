package cz.ddmjm.jumper.view;

import cz.ddmjm.jumper.controller.Controller;
import cz.ddmjm.jumper.model.Settings;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {

    private static final Keyboard instance = new Keyboard();

    public static Keyboard getInstance() {
        return instance;
    }

    private Keyboard() {
    }

    public void setupHandlers(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                updateState(true, event.getCode());
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                updateState(false, event.getCode());
            }
        });
    }

    private void updateState(boolean pressed, KeyCode keyCode) {
        if (keyCode == KeyCode.W && pressed) {
            Controller.upPressed();
        }
        if (keyCode == KeyCode.SPACE && pressed) {
            Controller.upPressed();
        }


        if (keyCode == KeyCode.A && !pressed) {
            Controller.leftReleased();
        }
        if (keyCode == KeyCode.D && !pressed) {
            Controller.rightReleased();
        }
        if (keyCode == KeyCode.SHIFT && pressed && Settings.OWER_JUMP_CORRECT) {
            Settings.OWER_JUMP = true;            
        }
        if (keyCode == KeyCode.NUMPAD8 && pressed) {
            Controller.upPressed2();
        }
        if (keyCode == KeyCode.NUMPAD7 && pressed && Settings.OWER_JUMP_CORRECT) {
            Settings.OWER_JUMP = true;            
        }
        if (keyCode == KeyCode.NUMPAD9 && pressed && Settings.OWER_JUMP_CORRECT) {
            Settings.OWER_JUMP = true;            
        }

        

    }

}
