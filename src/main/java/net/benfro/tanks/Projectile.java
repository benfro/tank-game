package net.benfro.tanks;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class Projectile extends Actor implements Explodable {

    private final Tank tank;

    public Projectile(Tank tank, String SVGdata, Image... spriteCels) {
       super(tank.getGame(), SVGdata, tank.getIX(), tank.getIY(), spriteCels);
       this.tank = tank;
       setMovementAngleRadians(tank.getMovementAngleRadians());
       setVelX(4);
       setVelY(4);
       getSpriteFrame().setRotate(getMovementAngleDegrees() - 90);
       System.out.println("QQQ " + tank.getIX() + ", " + tank.getIY());
       System.out.println(this);
    }

    @Override
    public void update() {
        if (isOutsideOfGameBounds()) {
            setAlive(false);
            explode();
        }
        double deltaY = getIY() - calculateYVector();
        double deltaX = getIX() - calculateXVector();
        translateXYPosition(deltaX, deltaY);
        updateXYPosition(deltaX, deltaY);
    }


    @Override
    public void explode() {
        Explosion explosion =
                new Explosion(getGame(), Duration.seconds(0.5), getIX(), getIY(),
                        getGame().getImageRegistry().getImages("smokeGrey1",
                                "smokeGrey2",
                                "smokeGrey3",
                                "smokeGrey4"));
        explosion.setCycleCount(1);
        getGame().addActor(explosion);
    }
}
