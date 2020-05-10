package net.benfro.tanks;

import com.google.common.eventbus.EventBus;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TankGame extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }
    private static final Logger log = LoggerFactory.getLogger(TankGame.class);

    @Getter
    private final double WIDTH = 800;
    @Getter
    private final double HEIGHT = 600;
    @Getter
    private final CastingDirector castingDirector;
    @Getter
    private final ImageRegistry imageRegistry = new ImageRegistry();
    private final EventBus eventBus = new EventBus();

    private Pane rootNode;
    private Scene scene;
    private GamePlayLoop gamePlayLoop;

    public TankGame() {
        this.castingDirector = new CastingDirector();
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting Tank Game!");

        rootNode = new Pane();
        scene = new Scene(rootNode, WIDTH, HEIGHT, Color.WHITESMOKE);

        createKeyEventHandling();
        addGameActorNodes();
        createStartGameLoop();

        stage.setTitle(":: == TANKS! == ::");
        stage.setScene(scene);
        stage.show();
    }

    private void createStartGameLoop() {
        gamePlayLoop = new GamePlayLoop(this);
        gamePlayLoop.start();
    }


    private void addGameActorNodes() {
        Tank myTank = new Tank(this, "M150 0 L75 500 L225 200 Z", getWIDTH() / 2, getHEIGHT() / 2,
                imageRegistry.getImages("redTank", "redBarrel"));
        eventBus.register(myTank);
        castingDirector.addCurrentCast(myTank);
        rootNode.getChildren().addAll(myTank.getSpriteFrame(), myTank.turrentSprite.getSpriteFrame());
    }

    public void addActor(Actor actor) {
        Platform.runLater(() -> {
            rootNode.getChildren().add(actor.getSpriteFrame());
            castingDirector.addCurrentCast(actor);
        });
    }

    public void removeActor(Actor actor) {
        Platform.runLater(() -> {
            rootNode.getChildren().remove(actor.getSpriteFrame());
            castingDirector.addToRemovedActors(actor);
        });
    }


    private void createKeyEventHandling() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    eventBus.post(TankAction.FORWARDS);
                    break;
                case DOWN:
                    eventBus.post(TankAction.BACKWARDS);
                    break;
                case LEFT:
                    eventBus.post(TankAction.LEFT);
                    break;
                case RIGHT:
                    eventBus.post(TankAction.RIGHT);
                    break;
                case S:
                    eventBus.post(TankAction.TURRENT_LEFT);
                    break;
                case D:
                    eventBus.post(TankAction.TURRENT_RIGHT);
                    break;
                case SPACE:
                    eventBus.post(TankAction.FIRE);
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case DOWN:
                case UP:
                    eventBus.post(TankAction.STOP);
                    break;
                case LEFT:
                case RIGHT:
                    eventBus.post(TankAction.STOP_TURN);
                    break;
                case S:
                case D:
                    eventBus.post(TankAction.STOP_TURRENT_TURN);
                    break;
                case SPACE:
                    eventBus.post(TankAction.STOP_FIRE);
                    break;
            }
        });
    }

}
