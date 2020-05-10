package net.benfro.tanks;

import com.google.common.collect.Lists;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Translate;
import lombok.Data;
import net.benfro.tanks.util.SinCosLookupTable;

import java.util.Arrays;
import java.util.List;

/**
 * Deals with images and sprites and their position
 */
@Data
public class Sprite {

    static void movePivot(Node node, double x, double y) {
        node.getTransforms().add(new Translate(-x, -y));
        node.setTranslateX(x);
        node.setTranslateY(y);
    }

    private double iX;
    private double iY;
    private double pX = 0;
    private double pY = 0;
    private double pixelsX;
    private double pixelsY;
    private final List<Image> imageStates;
    private ImageView spriteFrame;
    private SVGPath spriteBound;
    private boolean isFlipV;
    private boolean isFlipH;
    private final Movement movement;

    public Sprite() {
        movement = new Movement();
        spriteBound = new SVGPath();
        imageStates = Lists.newArrayList();
    }

    public Sprite(String SVGdata, double xLocation, double yLocation, Image... spriteCells) {
        this();
        spriteBound.setContent(SVGdata);
        spriteFrame = new ImageView(spriteCells[0]);
        imageStates.addAll(Arrays.asList(spriteCells));
        pixelsX = spriteCells[0].getRequestedWidth();
        pixelsY = spriteCells[0].getRequestedHeight();
        translateXYPosition(xLocation + getOffsetX(), yLocation + getOffsetY());
        updateXYPosition(xLocation + getOffsetX(), yLocation + getOffsetY());
    }

    protected double calculateXVector() {
        return SinCosLookupTable.cos(getMovementAngleDegrees()) * getVelX();
    }

    protected double calculateYVector() {
        return SinCosLookupTable.sin(getMovementAngleDegrees()) * getVelY();
    }

    public double getMovementAngleRadians() {
        return movement.getMovementAngleRadians();
    }

    public double getMovementAngleDegrees() {
        return movement.getMovementAngleDegrees();
    }

    public void setMovementAngleRadians(double movementAngle) {
        movement.setMovementAngleRadians(movementAngle);
    }

    public double getVelX() {
        return movement.getVelX();
    }

    public double getVelY() {
        return movement.getVelY();
    }

    public void setVelX(double vX) {
        movement.setVelX(vX);
    }

    public void setVelY(double vY) {
        movement.setVelY(vY);
    }

    protected void updateXYPosition(double deltaX, double deltaY) {
        setIX(deltaX);
        setIY(deltaY);
    }

    protected void translateXYPosition(double deltaX, double deltaY) {
        spriteFrame.setTranslateX(deltaX);
        spriteFrame.setTranslateY(deltaY);
    }

    protected double getOffsetX() {
        return -pixelsX / 2;
    }

    protected double getOffsetY() {
        return -pixelsY / 2;
    }

//    public void setAndRotateDegrees(double deltaAngle) {
//        setMovementAngleRadians(deltaAngle);
//        this.getSpriteFrame().setRotate(getMovementAngleDegrees());
//    }
}
