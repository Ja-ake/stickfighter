package engine.multiplayer.packets;

import engine.multiplayer.Packet;
import engine.elements.entities.Player;

/**
 *
 * @author Jake
 */
public class ExamplePacket extends Packet {
    
    @Override
    public Class getPacketType() {
        return Player.class;
    }
    
    @Override
    public void pullData(Object e) {
        add("health", 42.2);
        add("speed", 3);
        add("name", "Johnny");
    }

    @Override
    public void pushData(Object e) {
        
    }
}
