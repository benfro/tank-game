package net.benfro.tanks;

import com.google.common.eventbus.Subscribe;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class Tank extends Actor {

   private static int internalLogCounter;
   private static final Logger log = LoggerFactory.getLogger(Tank.class);
   private boolean
           goingForward,
           goingBackward,
           turningLeft,
           turningRight,
           firing,
           turningBarrelLeft,
           turningBarrelRight;
   protected TankTurrent turrentSprite;
   private double barrelAngle;

   enum Color {
      BEIGE,
      RED,
      BLACK,
      BLUE,
      GREEN;
   }


   public Tank(TankGame game, String SVGdata, double xLocation, double yLocation, Image... spriteCells) {
      super(game, SVGdata, xLocation, yLocation, spriteCells);
      setMovementAngleRadians(Math.PI / 2);
      turrentSprite = new TankTurrent(this, this.getIX(), this.getIY(), spriteCells[1]);
      setVelX(2);
      setVelY(2);

   }

   @Override
   public void update() {
      if (goingForward) {
         if (internalLogCounter % 60 == 0) {
            log.debug("Going forwards");
         }
         double deltaY = getIY() - calculateYVector();
         double deltaX = getIX() - calculateXVector();
         checkScreenBoundariesAndStopIfOutside(deltaY, deltaX);
      }
      if (goingBackward) {
         if (internalLogCounter % 60 == 0) {
            log.debug("Going backwards");
         }
         double deltaY = getIY() + calculateYVector();
         double deltaX = getIX() + calculateXVector();
         checkScreenBoundariesAndStopIfOutside(deltaY, deltaX);
      }
      if (turningLeft) {
         if (internalLogCounter % 60 == 0) {
            log.debug("Turning left");
         }
         double deltaAngle = getMovementAngleRadians() - Math.toRadians(1);
         setAndRotate(deltaAngle);
         turrentSprite.setAndRotat(deltaAngle);

      }
      if (turningRight) {
         if (internalLogCounter % 60 == 0) {
            log.debug("Turning right");
         }
         double deltaAngle = getMovementAngleRadians() + Math.toRadians(1);
         setAndRotate(deltaAngle);
         turrentSprite.setAndRotat(deltaAngle);
      }

      if (internalLogCounter++ % 60 == 0)
         log.debug("Angle: {}", getMovementAngleDegrees());

      if (firing) {
         Projectile projectile = new Projectile(this, "",
                 getGame().getImageRegistry().getImage("redBulletOutline"));
         getGame().addActor(projectile);
         setFiring(false);
      }
      if (turningBarrelLeft) {

      }
      if (turningBarrelRight) {

      }

   }

   private void checkScreenBoundariesAndStopIfOutside(double deltaY, double deltaX) {
      if (isOutsideOfGameBounds()) {
         setToCurrentScreenBoundary();
      } else {
         translateFrames(deltaX, deltaY);
         updateXYPosition(deltaX, deltaY);
      }
   }

   protected void setToCurrentScreenBoundary() {
      if (getIX() >= getRightBoundary()) {
         setIX(getRightBoundary());
      }
      if (getIX() <= getLeftBoundary()) {
         setIX(getLeftBoundary());
      }
      if (getIY() >= getBottomBoundary()) {
         setIY(getBottomBoundary());
      }
      if (getIY() <= getTopBoundary()) {
         setIY(getTopBoundary());
      }
   }

   private void setAndRotate(double deltaAngle) {
//        final Rotate rotationTransform = new Rotate(getMovementAngleDegrees() - 90, turrentSprite.getOffsetX(), turrentSprite.getOffsetY());
      setMovementAngleRadians(deltaAngle);
      //this.getSpriteFrame().setRotate(getMovementAngleDegrees() - 90);
      this.getSpriteFrame().getTransforms().add(new Rotate(getMovementAngleDegrees() - 90));
      //turrentSprite.setAndRotat(deltaAngle);
      //turrentSprite.getSpriteFrame().setRotate(getMovementAngleDegrees() - 90);

//        turrentSpite.getSpriteFrame().getTransforms().add(rotationTransform);

      // rotate a square using timeline attached to the rotation transform's angle property.
//        final Timeline rotationAnimation = new Timeline();
//        rotationAnimation.getKeyFrames().add(
//                new KeyFrame(Duration.seconds(1), new KeyValue(
//                        rotationTransform.angleProperty(),
//                        getMovementAngleDegrees() - 90
//                )
//                )
//        );
//        rotationAnimation.setCycleCount(1);
//        rotationAnimation.play();
//        rotationAnimation.setOnFinished(event -> turrentSprite.getSpriteFrame().getTransforms().remove(rotationTransform));
   }

   private void translateFrames(double deltaX, double deltaY) {
      this.translateXYPosition(deltaX, deltaY);
      turrentSprite.translateXYPosition(deltaX, deltaY);
   }

   @Override
   public boolean collide(Actor object) {
      return false;
   }

   @Subscribe
   public void receiveAction(TankAction action) {
      switch (action) {
         case FORWARDS:
            goingForward = true;
            break;
         case BACKWARDS:
            goingBackward = true;
            break;
         case STOP:
            goingBackward = goingForward = false;
            break;
         case RIGHT:
            turningRight = true;
            break;
         case LEFT:
            turningLeft = true;
            break;
         case STOP_TURN:
            turningLeft = turningRight = false;
            break;
         case FIRE:
            firing = true;
            break;
         case STOP_FIRE:
            firing = false;
            break;
         case TURRENT_LEFT:
            turningBarrelLeft = true;
            break;
         case TURRENT_RIGHT:
            turningBarrelRight = true;
            break;
         case STOP_TURRENT_TURN:
            turningBarrelLeft = turningBarrelRight = false;
         default:
            break;

      }
   }

}
