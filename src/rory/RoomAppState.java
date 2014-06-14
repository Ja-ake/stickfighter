package rory;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class RoomAppState extends BulletAppState {

    private Room room;
    private Camera camera;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        room = new Room(this);
        camera = new Camera(app.getCamera());
        room.loadLevel("main.scene");
        new Player(this, new Vector3f(0, 10, 0));
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(2f));
        getApp().getRootNode().addLight(al);
    }

    public SimpleApplication getApp() {
        return (SimpleApplication) app;
    }

    public Camera getCamera() {
        return camera;
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
