package Packets;

import java.util.List;
import setaccio.Packet;
import setaccio.Unit;
import static setaccio.Tools.ExtractUnit;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class AttackStartPacket extends Packet {
    private Unit target;

    public AttackStartPacket() {
        super.setOpcode("SMSG_ATTACK_START");
    }

    @Override
    public String toString() {
        return super.toString() + " Target: " + target.getName() + " " + target.getEntry();
    }

    @Override
    public void parseInfo(List<String> lines) {
        super.setOwner(ExtractUnit(lines.get(1)));
        super.parseDateTime(lines.get(0));
        this.target = ExtractUnit(lines.get(2));
    }

    public Unit getTarget() {
        return target;
    }

    public void setTarget(Unit target) {
        this.target = target;
    }
}
