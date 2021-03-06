package engine.multiplayer.packets;

import engine.elements.entities.Player;
import engine.multiplayer.Packet;

/**
 *
 * @author Jake
 */
public class PlayerPacket extends Packet {

    @Override
    public Class getPacketType() {
        return Player.class;
    }

    @Override
    public void pullData(Object o) {
        Player p = (Player) o;
        add("x", p.getPosition().x);
        add("y", p.getPosition().y);
        add("z", p.getPosition().z);
    }

    @Override
    public void pushData(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
