package setaccio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class Unit {
    private String entry;
    private String guid;
    private String name;
    private String type;
    private List<Packet> packetList = new ArrayList<>();

    public Unit() {
        this.entry = "-1";
        this.guid = "-1";
    }

    List getPacketsByType(String type) {
        List<Packet> tmpPackets = new ArrayList<>();
        for (Packet p : packetList) {
            if (p.getOpcode().equals(type))
                tmpPackets.add(p);
        }
        return tmpPackets;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String npcId) {
        this.entry = npcId;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Packet> getPacketList() {
        return packetList;
    }

    public void addPacket(Packet packet) {
        this.packetList.add(packet);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
