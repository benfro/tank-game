package net.benfro.tanks;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class DebugCross {

   private Group pivotMarker;

   public DebugCross(double x, double y) {
      //this.pivotMarker = pivotMarker;
      final Line verticalLine = new Line(0, -10, 0, 10);
      verticalLine.setStroke(Color.FIREBRICK);
      verticalLine.setStrokeWidth(3);
      verticalLine.setStrokeLineCap(StrokeLineCap.ROUND);
      final Line horizontalLine = new Line(-10, 0, 10, 0);
      horizontalLine.setStroke(Color.FIREBRICK);
      horizontalLine.setStrokeWidth(3);
      verticalLine.setStrokeLineCap(StrokeLineCap.ROUND);
      pivotMarker = new Group(verticalLine, horizontalLine);
      pivotMarker.setTranslateX(x);
      pivotMarker.setTranslateY(y);
   }

   public void translate(double x, double y) {
      pivotMarker.setTranslateX(x);
      pivotMarker.setTranslateY(y);
   }
}



