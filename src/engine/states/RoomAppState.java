/*
 * State that controls the room.
 */
package engine.states;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.renderer.Caps;
import com.jme3.scene.Node;
import engine.GameControl;
import engine.InputPacket;
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
    private Node node;

    public RoomAppState() {
        node = new Node();
    }

    private void createFilters() {
        if (getApp().getRenderer().getCaps().contains(Caps.GLSL100)) {
            FilterPostProcessor fpp = new FilterPostProcessor(getApp().getAssetManager());
            //fpp.setNumSamples(4);
            int numSamples = getApp().getContext().getSettings().getSamples();
            if (numSamples > 0) {
                fpp.setNumSamples(numSamples);
            }
            CartoonEdgeFilter toon = new CartoonEdgeFilter();
            //toon.setEdgeColor(ColorRGBA.Black);
            fpp.addFilter(toon);
            getApp().getViewPort().addProcessor(fpp);
        }
    }

    private void createLighting() {
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(4f));
        node.addLight(al);

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1, -1, 1));
        sun.setColor(ColorRGBA.White.mult(.4f));
        node.addLight(sun);
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        getApp().getRootNode().attachChild(node);

        room = new Room(this);
        camera = new Camera3D(app.getCamera());
        room.loadLevel("main.scene");

        new Player(this, new Vector3f(0, 20, 0));

        createLighting();
        createFilters();

        //Draw physics mesh
//        getPhysicsSpace().enableDebug(getApp().getAssetManager());
    }

    @Override
    public GameControl getApp() {
        return (GameControl) app;
    }

    public Camera3D getCamera() {
        return camera;
    }

    public InputPacket getInputPacket() {
        return getApp().getInputPacket();
    }

    public Node getNode() {
        return node;
    }

    @Override
    public ArrayList<Renderer> getRenderers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
