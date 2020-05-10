package net.benfro.tanks;

import javafx.scene.image.Image;
import lombok.Data;
import lombok.Getter;

/**
 * Deals with physics, state and statuses
 */
@Data
public abstract class Actor extends Sprite {

    @Getter
    private double rightBoundary;
    @Getter
    private double leftBoundary;
    @Getter
    private double bottomBoundary;
    @Getter
    private double topBoundary;

    private boolean isAlive = true;
    private boolean isFixed;
    private boolean isBonus;
    private boolean hasValue;
    private double damage;
    private double lifeSpan = 1000;

    private double boundScale;
    private double boundRot;
    private double friction;
    private double gravity;
    private double bounce;

    private final TankGame game;

    public Actor(TankGame game, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        this.game = game;
        rightBoundary = (game.getWIDTH() / 2 - getPixelsX() / 2) * 2;
        leftBoundary = 0;
        bottomBoundary = (game.getHEIGHT() / 2 - getPixelsY() / 2) * 2;
        topBoundary = 0;
    }

    public abstract void update();

    protected boolean collide(Actor object) {
        return false;
    }

    protected boolean isOutsideOfGameBounds() {
        return getIX() > rightBoundary ||
                getIX() < leftBoundary ||
                getIY() > bottomBoundary ||
                getIY() < topBoundary;
    }

    protected void setToCurrentScreenBoundary() {
        // Empty implementation
    }
}
