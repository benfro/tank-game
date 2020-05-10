package net.benfro.tanks;

import javafx.scene.image.Image;

public class Prop extends Actor {

    public Prop(TankGame game, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(game, SVGdata, xLocation, yLocation, spriteCels);
    }

    @Override
    public void update() {

    }
}
