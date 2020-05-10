package net.benfro.tanks.util;

/**
 * http://stackoverflow.com/questions/16930581/fast-sine-and-cosine-function-in-java
 * in this implementation, units are in degrees
 */
public class SinCosLookupTable {

   private static final int ticksPerDegree = 1;
   private static final int modulus = 360 * ticksPerDegree;
   private static final double[] lookupTable = new double[modulus];

   static {
      for (int i = 0; i < lookupTable.length; i++) {
         lookupTable[i] = Math.sin((i * Math.PI) / (ticksPerDegree * 180));
      }
   }

   private static double lookupInternal(int angle) {
      return angle >= 0 ? lookupTable[angle % (modulus)] : -lookupTable[-angle % (modulus)];
   }

   public static double sin(double angleInDegrees) {
      return lookupInternal((int) (angleInDegrees * ticksPerDegree + 0.5));
   }

   public static double cos(double angleInDegrees) {
      return lookupInternal((int) ((angleInDegrees + 90) * ticksPerDegree + 0.5));
   }

}
