package engine.multiplayer;

import com.jme3.network.AbstractMessage;
import com.jme3.network.Client;
import com.jme3.network.HostedConnection;
import com.jme3.network.MessageListener;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializable;
import java.util.Map;

/**
 *
 * @author Jake
 */
@Serializable
public abstract class Packet extends AbstractMessage {
    protected Map<String, Short> shorts;
    protected Map<String, Integer> integers;
    protected Map<String, Long> longs;
    protected Map<String, Float> floats;
    protected Map<String, Double> doubles;
    protected Map<String, Boolean> booleans;
    protected Map<String, String> strings;
    protected Map<String, Object> objects;
    
    public abstract Class getPacketType();
    public abstract void pullData(Object e);
    public abstract void pushData(Object e);
    
    public void add(String name, short d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {
            shorts.put(name, d);
        }
    }

    public void add(String name, int d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {
            integers.put(name, d);
        }
    }

    public void add(String name, long d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {

            longs.put(name, d);
        }
    }

    public void add(String name, float d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {

            floats.put(name, d);
        }
    }

    public void add(String name, double d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {

            doubles.put(name, d);
        }
    }

    public void add(String name, boolean d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {

            booleans.put(name, d);
        }
    }

    public void add(String name, String d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {

            strings.put(name, d);
        }
    }

    public void add(String name, Object d) {
        if (!(shorts.containsKey(name)
                || integers.containsKey(name)
                || longs.containsKey(name)
                || floats.containsKey(name)
                || doubles.containsKey(name)
                || booleans.containsKey(name)
                || strings.containsKey(name)
                || objects.containsKey(name))) {

            objects.put(name, d);
        }
    }

    public void remove(String name) {
        shorts.remove(name);
        integers.remove(name);
        longs.remove(name);
        floats.remove(name);
        doubles.remove(name);
        booleans.remove(name);
        strings.remove(name);
        objects.remove(name);
    }

    public short getShort(String name) {
        return shorts.get(name);
    }

    public int getInteger(String name) {
        return integers.get(name);
    }

    public long getLong(String name) {
        return longs.get(name);
    }

    public float getFloat(String name) {
        return floats.get(name);
    }

    public double getDouble(String name) {
        return doubles.get(name);
    }

    public boolean getBoolean(String name) {
        return booleans.get(name);
    }

    public String getString(String name) {
        return strings.get(name);
    }

    public Object get(String name) {
        Object ret = shorts.get(name);
        if (ret == null) {
            ret = integers.get(name);
        }
        if (ret == null) {
            ret = longs.get(name);
        }
        if (ret == null) {
            ret = floats.get(name);
        }
        if (ret == null) {
            ret = doubles.get(name);
        }
        if (ret == null) {
            ret = booleans.get(name);
        }
        if (ret == null) {
            ret = strings.get(name);
        }
        if (ret == null) {
            ret = objects.get(name);
        }

        return ret;
    }
    
    public void register(Client client, MessageListener<Client> listener) {
        client.addMessageListener(listener, this.getClass());
    }
    
    public void register(Server server, MessageListener<HostedConnection> listener) {
        server.addMessageListener(listener, this.getClass());
    }
}
