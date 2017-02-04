package setaccio;

import Packets.AiReactionPacket;
import Packets.AttackStartPacket;
import Packets.KillLogPacket;
import Packets.SpellGoPacket;
import Packets.SpellStartPacket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public abstract class Packet implements Comparable<Packet> {
    private String opcode;
    private LocalDateTime dateTime;
    private Unit owner;

    public Packet() {

    }

    public abstract void parseInfo(List<String> l);
    
    public SpellGoPacket toSpellGoPacket() {
        if (this instanceof SpellGoPacket)
            return (SpellGoPacket)this;
        return null;
    }

    public SpellStartPacket toSpellStartPacket() {
        if (this instanceof SpellStartPacket)
            return (SpellStartPacket)this;
        return null;
    }

    public AttackStartPacket toAttackStartPacket() {
        if (this instanceof AttackStartPacket)
            return (AttackStartPacket)this;
        return null;
    }

    public KillLogPacket toKillLogPacket() {
        if (this instanceof KillLogPacket)
            return (KillLogPacket)this;
        return null;
    }
    
    public AiReactionPacket toAiReactionPacket() {
        if (this instanceof AiReactionPacket)
            return (AiReactionPacket)this;
        return null;
    }
    
    
    @Override
    public String toString() {
        String str = "";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.SSS");
        str += dateTimeFormatter.format(dateTime);
        str += " " + owner.getGuid();
        str += " " + opcode;
        if (owner.getType().equals("Player")) {
            str += " " + owner.getType();
        } else {
            str += " " + owner.getEntry();
            if (owner.getName() != null)
                str += " " + owner.getName();
        }
        return str;
    }

    public void parseDateTime(String line) {
        int start = line.indexOf("Time") + 6;
        int end = line.indexOf("Number") - 1;
        String date = line.substring(start, end);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.SSS");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        this.dateTime = localDateTime;
        //System.out.println("Time: " + dateTime + "HH: " + dateTime.getHour() + "mm: " + dateTime.getMinute() + "ss: " + dateTime.getSecond() + "SSS: " + dateTime.getNano());
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

    public Unit getOwner() {
        return owner;
    }

    public void setOwner(Unit owner) {
        this.owner = owner;
    }
}
