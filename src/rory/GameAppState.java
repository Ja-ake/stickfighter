package rory;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;

public class GameAppState extends BulletAppState {

    private Room room;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        room = new Room(this);
    }

    public SimpleApplication getApp() {
        return (SimpleApplication) app;
    }

    public Room getRoom() {
        return room;
    }

    public AppStateManager getStateManager() {
        return stateManager;
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        room.update(tpf);
    }
}
