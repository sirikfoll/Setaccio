package setaccio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public abstract class Packet implements Comparable<Packet> {
    private String opcode;
    private LocalDateTime dateTime;
    private List packetList;
    private Unit owner;
    
    public abstract void parseInfo(List<String> l);

    private void FilterDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.SSS");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        this.dateTime = localDateTime;
    }
    
    @Override
    public int compareTo(Packet o) {
        if (getDateTime() == null || o.getDateTime() == null)
            return 0;
        return getDateTime().compareTo(o.getDateTime());
    }
    
    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List getPacketList() {
        return packetList;
    }

    public void setPacketList(List packetList) {
        this.packetList = packetList;
    }

    public Unit getOwner() {
        return owner;
    }

    public void setOwner(Unit owner) {
        this.owner = owner;
    }
}
