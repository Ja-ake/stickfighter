package engine.elements.entities;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import engine.elements.Entity;
import engine.states.RoomAppState;

public class Human extends Entity {

    public Human(RoomAppState appState, Vector3f position) {
        super(appState, position);
    }

    protected SimpleCharacterControl getControl() {
        return (SimpleCharacterControl) physicsControl;
    }

    public float getMass() {
        return 1;
    }

    @Override
    protected RigidBodyControl initialCollisionShape() {
        SimpleCharacterControl scc = new SimpleCharacterControl(appState.getApp(), 2, new CapsuleCollisionShape(1, 2), getMass());

        scc.setDamping(0.5f, 0.5f);
        scc.setSleepingThresholds(0.7f, 0.7f);
        scc.setFriction(1);
        scc.setAngularFactor(0f);
        scc.setMoveSpeed(40);
        scc.setMoveSlopeSpeed(0.3f);
        scc.setJumpSpeed(40);
        scc.setGravity(new Vector3f(0, -100, 0));

        return scc;
    }

    @Override
    protected Spatial initialSpatial() {
        Node s = (Node) appState.getApp().getAssetManager().loadModel("Models/S/StickMesh.mesh.xml");
        Material mat = new Material(appState.getApp().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Black);
        s.getChild("StickMat").setMaterial(mat);
        s.scale(.5f);
        s.getChild(0).setLocalTranslation(0, -3, 0);
        return s;
    }
}
