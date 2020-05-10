package net.benfro.tanks;

import lombok.Data;

@Data
public class Movement {

   /**
    * Keeps angle alpha to 0 <= alpha < 2π radians
    */
   static double normalizeAngle(double movementAngle) {
      double MAX_ANGLE = 2 * Math.PI;
      return movementAngle + Math.ceil(-movementAngle / MAX_ANGLE) * MAX_ANGLE;
   }

   private double movementAngleRadians;
   private double velX;
   private double velY;
   private double velXMax;
   private double velYMax;
   private double velXMin;
   private double velYMin;

   public void setMovementAngleRadians(double movementAngleRadians) {
      this.movementAngleRadians = normalizeAngle(movementAngleRadians);
   }

   public double getMovementAngleDegrees() {
      return Math.toDegrees(this.movementAngleRadians);
   }

   public void setMovementAngleDegrees(double angleInDegrees) {
      setMovementAngleRadians(Math.toRadians(angleInDegrees));
   }

}
