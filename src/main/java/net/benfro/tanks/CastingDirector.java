package net.benfro.tanks;

import java.util.*;

class CastingDirector {

    private final List<Actor> CURRENT_CAST;
    private final List<Actor> COLLIDE_CHECKLIST;
    private final Set<Actor> REMOVED_ACTORS;

    public CastingDirector() {
        CURRENT_CAST = new ArrayList<>();
        COLLIDE_CHECKLIST = new ArrayList<>();
        REMOVED_ACTORS = new HashSet<>();
    }

    public List<Actor> getCurrentCast() {
        return CURRENT_CAST;
    }

    public void addCurrentCast(Actor... actors) {
        CURRENT_CAST.addAll(Arrays.asList(actors));
    }

    public void removeCurrentCast(Actor... actors) {
        CURRENT_CAST.removeAll(Arrays.asList(actors));
    }

    public void resetCurrentCast() {
        CURRENT_CAST.clear();
    }

    public List getCollideCheckList() {
        return COLLIDE_CHECKLIST;
    }

    public void resetCollideCheckList() {
        COLLIDE_CHECKLIST.clear();
        COLLIDE_CHECKLIST.addAll(CURRENT_CAST);
    }

    public Set getRemovedActors() {
        return REMOVED_ACTORS;
    }

    public void addToRemovedActors(Actor... actors) {
        if (actors.length == 0) {
            return;
        } else if (actors.length > 1) {
            REMOVED_ACTORS.addAll(Arrays.asList(actors));
        } else {
            REMOVED_ACTORS.add(actors[0]);
        }

    }

    public void resetRemovedActors() {
        CURRENT_CAST.removeAll(REMOVED_ACTORS);
        REMOVED_ACTORS.clear();
    }

    public void purgeRemovedActors() {
        CURRENT_CAST.removeAll(REMOVED_ACTORS);
    }
}
