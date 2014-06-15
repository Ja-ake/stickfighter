package engine.multiplayer.packets;

/**
 *
 * @author Jake
 */
public class ExamplePacket extends Packet {
    public ExamplePacket() {
        add("health", 42.2);
        add("speed", 3);
        add("name", "Johnny");
    }
}
