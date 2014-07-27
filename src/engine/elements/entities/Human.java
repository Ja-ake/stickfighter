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
        SimpleCharacterControl scc = new SimpleCharacterControl(appState.getApp(), 2, new CapsuleCollisionShape(.8f, 2), getMass());

        scc.setDamping(0.5f, 0.5f);
        scc.setSleepingThresholds(0.7f, 0.7f);
        scc.setFrictionWalk(.05f);
        scc.setAngularFactor(0);
        scc.setMoveSpeed(40);
        scc.setMoveSlopeSpeed(0.3f);
        scc.setJumpSpeed(40);
        scc.setGravity(new Vector3f(0, -100, 0));

        return scc;
    }

    @Override
    protected Spatial initialSpatial() {

        //Material mat = new Material(appState.getApp().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        //mat.setColor("Color", ColorRGBA.Black);
        Material mat = new Material(appState.getApp().getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors", true);
        mat.setColor("Diffuse", ColorRGBA.White);

        Node stick = (Node) appState.getApp().getAssetManager().loadModel("Models/Stick/StickMesh.mesh.xml");
        Spatial body = stick.getChild(0);
        body.setMaterial(mat);
        body.setLocalTranslation(0, -3, 0);

        Spatial head = ((Node) appState.getApp().getAssetManager().loadModel("Models/Stick/HeadMesh.mesh.xml")).getChild(0);
        head.setMaterial(mat);
        head.setLocalTranslation(0, -3, 0);
        stick.attachChild(head);

        stick.scale(.5f);
        return stick;
    }
}
