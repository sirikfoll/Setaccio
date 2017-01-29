package setaccio;

import Packets.SpellGoPacket;
import Packets.SpellStartPacket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
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

    public void sortAllPackets() {
        Collections.sort(packetList);
    }

    public void printAllPackets() {
        for (Packet p : packetList)
            System.out.println(p.toString());
    }

    boolean hasPacketType(String type) {
        for (Packet p : packetList)
            if (p.getOpcode().equals(type))
                return true;
        return false;
    }

    List getPacketsByType(String type) {
        List<Packet> tmpPackets = new ArrayList<>();
        for (Packet p : packetList) {
            if (p.getOpcode().equals(type))
                tmpPackets.add(p);
        }
        return tmpPackets;
    }
    
    void getPacketsByType(String pType, List list) {
        for (Packet p : packetList) {
            if (p.getOpcode().equals(pType))
                list.add(p);
        }
    }
    
    void removePacketsExceptType(String type) {
        for (Iterator<Packet> it = packetList.iterator(); it.hasNext();) {
            Packet p = it.next();
            if (!p.getOpcode().equals(type))
                it.remove();
        }
    }

    HashSet getUniqueCasts() {
        HashSet<String> spells = new HashSet<>();
        for (Packet p : packetList) {
            if (p.getOpcode().equals("SMSG_SPELL_GO")) {
                spells.add(((SpellGoPacket)p).getSpellId() + " " + ((SpellGoPacket)p).getSpellName());
                System.out.println("Spell: " + ((SpellGoPacket)p).getSpellId());
            }
            else if (p.getOpcode().equals("SMSG_SPELL_START"))
                spells.add(((SpellStartPacket)p).getSpellId() + " " + ((SpellStartPacket)p).getSpellName());
        }
        return spells;
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
