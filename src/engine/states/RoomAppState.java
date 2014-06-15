/*
 * State that controls the room.
 */
package engine.states;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import java.util.ArrayList;
import engine.Renderer;
import engine.State;
import engine.elements.Camera3D;
import engine.elements.entities.Player;
import engine.renderers.Room;

/**
 * TODO: change name
 *
 * @author Jake
 */
public class RoomAppState extends BulletAppState implements State {

    private Room room;
    private Camera3D camera;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        room = new Room(this);
        camera = new Camera3D(app.getCamera());
        room.loadLevel("main.scene");
        new Player(this, new Vector3f(0, 10, 0));
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(2f));
        getApp().getRootNode().addLight(al);
    }

    public SimpleApplication getApp() {
        return (SimpleApplication) app;
    }

    public Camera3D getCamera() {
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

    @Override
    public ArrayList<Renderer> getRenderers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
