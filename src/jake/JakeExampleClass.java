package jake;

import rory.*;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 * Sample 7 - how to load an OgreXML model and play an animation, using
 * channels, a controller, and an AnimEventListener.
 */
public class JakeExampleClass extends SimpleApplication
        implements AnimEventListener {

    private AnimChannel channel;
    private AnimControl control;
    Node player;

    public static void main(String[] args) {
        ExampleClass app = new ExampleClass();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        viewPort.setBackgroundColor(ColorRGBA.LightGray);
        initKeys();
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
        rootNode.addLight(dl);
        player = (Node) assetManager.loadModel("Models/Stick/StickMesh.mesh.xml");
        player.setLocalScale(0.5f);
        rootNode.attachChild(player);
        control = player.getControl(AnimControl.class);
        control.addListener(this);
        channel = control.createChannel();
        channel.setAnim("Run");
        
        flyCam.setMoveSpeed(20);
    }

    @Override
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        if (animName.equals("Run")) {
            channel.setAnim("Stand", .50f);
            channel.setLoopMode(LoopMode.DontLoop);
            channel.setSpeed(1f);
        }
    }

    @Override
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
        // unused
    }

    /**
     * Custom Keybinding: Map named actions to inputs.
     */
    private void initKeys() {
        inputManager.addMapping("Run", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(actionListener, "Run");
    }
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Run") && !keyPressed) {
                if (!channel.getAnimationName().equals("Run")) {
                    channel.setAnim("Run", 0.50f);
                    channel.setLoopMode(LoopMode.Loop);
                }
            }
        }
    };
}