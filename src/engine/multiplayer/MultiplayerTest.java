package engine.multiplayer;

import com.jme3.math.Vector3f;
import engine.elements.entities.Human;

/**
 *
 * @author Jake
 */
public class MultiplayerTest {
    public static void main(String[] args) {
        Multiplayer multiplayer = new Multiplayer();
        Packet p = multiplayer.createPacket(new Human(new Vector3f(0, 0, 0)));
        System.out.println(p.get("x"));
    }
}
