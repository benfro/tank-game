package net.benfro.tanks;

import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImageRegistry {

   private final Map<String, Image> imageMap = new HashMap<>();

   public ImageRegistry() {
      loadGameImages();
   }

   public Image getImage(String key) {
      return imageMap.get(key);
   }

   public Image[] getImages(String... keys) {
      List<Image> images = Arrays.stream(keys).map(x -> imageMap.get(x)).collect(Collectors.toList());
      return images.toArray(new Image[images.size()]);
   }

   private void loadGameImages() {
      String tankBasePath = "/images/tanks_png/Tanks/";

      imageMap.put("redTank", new Image(tankBasePath + "tankRed.png", 75, 70, true, false, true));
      imageMap.put("blackTank", new Image(tankBasePath + "tankBlack.png", 75, 700, true, false, true));
      imageMap.put("blueTank", new Image(tankBasePath + "tankBlue.png", 75, 70, true, false, true));
      imageMap.put("beigeTank", new Image(tankBasePath + "tankBeige.png", 75, 70, true, false, true));
      imageMap.put("greenTank", new Image(tankBasePath + "tankGreen.png", 75, 70, true, false, true));
      imageMap.put("redBarrel", new Image(tankBasePath + "barrelRed.png", 16, 50, true, false, true));
      imageMap.put("blackBarrel", new Image(tankBasePath + "barrelBlack.png", 16, 50, true, false, true));
      imageMap.put("blueBarrel", new Image(tankBasePath + "barrelBlue.png", 16, 50, true, false, true));
      imageMap.put("beigeBarrel", new Image(tankBasePath + "barrelBeige.png", 16, 50, true, false, true));
      imageMap.put("greenBarrel", new Image(tankBasePath + "barrelGreen.png", 16, 50, true, false, true));
      imageMap.put("redBulletOutline", new Image("/images/tanks_png/" + "Bullets/bulletRed_outline.png", 20, 34, true, false, true));
      imageMap.put("barrelGreenSide", new Image("/images/tanks_png/" + "Obstacles/barrelGreen_side.png", 44, 62, true, false, true));
      imageMap.put("barrelGreenSideDamaged", new Image("/images/tanks_png/" + "Obstacles/barrelGreen_side_damaged.png", 44, 62, true, false, true));
      imageMap.put("barrelGreenUp", new Image("/images/tanks_png/" + "Obstacles/barrelGreen_up.png", 48, 48, true, false, true));
      imageMap.put("smokeGrey0", new Image("/images/tanks_png/" + "Smoke/smokeGrey0.png", 87, 87, true, false, true));
      imageMap.put("smokeGrey1", new Image("/images/tanks_png/" + "Smoke/smokeGrey1.png", 92, 89, true, false, true));
      imageMap.put("smokeGrey2", new Image("/images/tanks_png/" + "Smoke/smokeGrey2.png", 90, 99, true, false, true));
      imageMap.put("smokeGrey3", new Image("/images/tanks_png/" + "Smoke/smokeGrey3.png", 79, 79, true, false, true));
      imageMap.put("smokeGrey4", new Image("/images/tanks_png/" + "Smoke/smokeGrey4.png", 100, 97, true, false, true));
   }
}
