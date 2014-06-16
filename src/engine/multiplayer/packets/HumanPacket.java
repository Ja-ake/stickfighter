package engine.multiplayer.packets;

import engine.elements.entities.Human;
import engine.multiplayer.Packet;

/**
 *
 * @author Jake
 */
public class HumanPacket extends Packet {

    @Override
    public Class getPacketType() {
        return Human.class;
    }

    @Override
    public void pullData(Object o) {
        Human p = (Human) o;
        add("x", p.position.x);
        add("y", p.position.y);
        add("z", p.position.z);
    }

    @Override
    public void pushData(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
