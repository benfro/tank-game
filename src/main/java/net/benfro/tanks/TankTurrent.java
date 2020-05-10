package net.benfro.tanks;

import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class TankTurrent extends Sprite {

   private final Tank owningTank;
   double deltaX;
   double deltaY;

   public TankTurrent(Tank owningTank, double xLocation, double yLocation, Image... spriteCells) {
      super("", xLocation, yLocation, spriteCells);
      this.owningTank = owningTank;
      deltaX = owningTank.getOffsetX();
      deltaY = getOffsetY();
      updateXYPosition(xLocation - deltaX, yLocation - deltaY);
      translateXYPosition(xLocation - deltaX, yLocation - deltaY);
   }

   @Override
   protected double getOffsetY() {
      return getPixelsY() * 0.4;
   }

   @Override
   protected double getOffsetX() {
      return deltaX;
   }


   public void setAndRotat(double angleRadian) {
      setMovementAngleRadians(angleRadian);
      Rotate rotate = new Rotate(getMovementAngleDegrees() - 90, getIX(), getIY());
      getSpriteFrame().getTransforms().add(rotate);
      //getSpriteFrame().tran
      getSpriteFrame().getTransforms().remove(rotate);
      //System.out.println("owningTankX = " + owningTank.getIX());
      //System.out.println("owningTankY = " + owningTank.getIY());

      //getSpriteFrame().getTransforms().clear();
   }
}
