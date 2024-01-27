package cz.ddmjm.jumper.model;


public class Player {

    private double prevX;
    private double prevY;
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private boolean onGround;
    private boolean onGroundouble;


    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        velocityX = 0;
        velocityY = 0;
        onGround = false;
        prevX = x;
        prevY = y;

    }

    public void stop() {
        velocityX = 0;
        Settings.STOP_WALKING = true;
    }

    public void moveRight() {
        velocityX = Settings.MOVEMENT_SPEED;
    }

    public void moveLeft() {
        velocityX = -Settings.MOVEMENT_SPEED;
    }

    public void stopLeft() {
        if (velocityX < 0) {
            velocityX = 0;
        }
    }

    public void stopRight() {
        if (velocityX > 0) {
            velocityX = 0;
        }
    }

    public void jump() {
        if (onGround) {
            velocityY = Settings.JUMP_FORCE;
            return;
        }
        if (onGroundouble) {
            velocityY = Settings.JUMP_FORCE;
            onGroundouble = false;
        }
    }

    public void timerChanged() {
        prevX = x;
        prevY = y;
        x += velocityX;
        y += velocityY;
        if (!onGround) {
            velocityY--;
        }
        if (x < 0) {
            x = 0;
        }
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public double getPrevX() {
        return prevX;
    }

    public double getPrevY() {
        return prevY;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
        if (onGround) {
            onGroundouble = true;
        }
    }

}
