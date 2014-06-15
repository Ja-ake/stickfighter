package engine.multiplayer;

import engine.util.ReflectionEx;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jake
 */
public class Multiplayer {
    public Packet createPacket(Object e) {
        try {
            Class[] packetClasses = ReflectionEx.getClasses("engine.multiplayer.packets");
            for (Class packetClass : packetClasses) {
                Packet packet = ((Packet) packetClass.newInstance());
                if (packet.getPacketType().getName().equals(e.getClass().getName())) {
                    packet.pullData(e);
                    return packet;
                }
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
}
