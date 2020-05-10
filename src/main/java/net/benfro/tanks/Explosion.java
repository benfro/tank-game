package net.benfro.tanks;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.List;

public class Explosion extends Actor {


   private static class ExplosionAnimation extends Transition {

      private final Actor actor;

      public ExplosionAnimation(Actor actor, Duration duration) {
         this.actor = actor;
         setCycleDuration(duration);
      }

      @Override
      protected void interpolate(double frac) {
         List<Image> imageStates = actor.getImageStates();
         double span = 1.0 / imageStates.size();
         int index = (int) Math.floor(frac / span);
         if (index >= imageStates.size()) index = 0;
         actor.getSpriteFrame().setImage(imageStates.get(index));
      }

   }


   private final ExplosionAnimation explosionAnimation;

   public Explosion(TankGame game, Duration duration, double xPosition, double yPosition, Image... images) {
      super(game, "", xPosition, yPosition, images);
      this.explosionAnimation = new ExplosionAnimation(this, duration);
      explosionAnimation.setInterpolator(Interpolator.LINEAR);
   }

   @Override
   public void update() {
//        getGame().addActor(this);
      explosionAnimation.play();
      explosionAnimation.setOnFinished(event -> setAlive(false));
   }

   public void setCycleCount(int indefinite) {
      explosionAnimation.setCycleCount(indefinite);
   }


}
