/*
 * Renderer for the room.
 */

package jake.engine.renderers;

import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.scene.Node;
import jake.engine.Renderer;
import jake.engine.State;
import jake.engine.elements.Entity;

/**
 *
 * @author Jake
 */
public class Room extends Renderer {
    private State appState;
    private Node levelModel;

    public Room(State appState) {
        this.appState = appState;
    }

    public void addEntity(Entity e) {
        elements.add(e);
    }

    public void loadLevel(String levelName) {
        appState.getApp().getAssetManager().registerLocator("town.zip", ZipLocator.class);
        levelModel = (Node) appState.getApp().getAssetManager().loadModel(levelName);
        appState.getApp().getRootNode().attachChild(levelModel);
        CollisionShape levelShape = CollisionShapeFactory.createMeshShape(levelModel);
        RigidBodyControl phys = new RigidBodyControl(levelShape, 0);
        levelModel.addControl(phys);
        appState.getPhysicsSpace().add(phys);
    }

    public void removeEntity(Entity e) {
        elements.remove(e);
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
    }
}
