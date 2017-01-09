package setaccio;

import java.util.List;
import static setaccio.Tools.ExtractUnit;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class AttackStartPacket extends Packet {
    private Unit attacker;
    private Unit target;
    
    public AttackStartPacket() {
        super.setOpcode("SMSG_ATTACK_START");
    }
    
    @Override
    public void parseInfo(List<String> lines) {
        this.attacker = ExtractUnit(lines.get(1));
        this.target = ExtractUnit(lines.get(2));
    }

    public Unit getAttacker() {
        return attacker;
    }

    public void setAttacker(Unit attacker) {
        this.attacker = attacker;
    }

    public Unit getTarget() {
        return target;
    }

    public void setTarget(Unit target) {
        this.target = target;
    }
}
