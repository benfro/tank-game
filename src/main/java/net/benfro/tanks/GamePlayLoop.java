package net.benfro.tanks;

import javafx.animation.AnimationTimer;

import java.util.List;
import java.util.stream.Collectors;

class GamePlayLoop extends AnimationTimer {

    private static int loopCnt;
    private final TankGame game;

    public GamePlayLoop(TankGame game) {
        this.game = game;
    }

    @Override
    public void handle(long now) {
        CastingDirector castingDirector = game.getCastingDirector();
        castingDirector.getCurrentCast().stream().forEach(Actor::update);
        if (loopCnt++ % 60 == 0) {
            List<Actor> deadActors = castingDirector.
                    getCurrentCast().
                    stream().
                    filter(x -> !x.isAlive()).
                    collect(Collectors.toList());
            castingDirector.addToRemovedActors(deadActors.toArray(new Actor[deadActors.size()]));
            castingDirector.purgeRemovedActors();
            deadActors.stream().forEach((x) -> game.removeActor(x));
            loopCnt = 0;
        }
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
