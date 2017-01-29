package Packets;

import java.util.List;
import java.util.regex.Pattern;
import setaccio.Packet;
import setaccio.Unit;
import static setaccio.Tools.*;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class SpellStartPacket extends Packet {
    private String spellId;
    private String spellName;

    private Unit caster;
    private Unit target;
    private Integer targetFlags;

    public SpellStartPacket() {
        super.setOpcode("SMSG_SPELL_START");
    }

    public String toString() {
        return super.toString() + " Id: " + spellId + " Name: " + spellName + " Caster: " + caster.getName() + " Target: " + target.getName();
    }

    @Override
    public void parseInfo(List<String> lines) {
        caster = ExtractUnit(lines.get(1));
        super.setOwner(caster);
        super.parseDateTime(lines.get(0));

        String spellInfo = "";
        for (String s : lines)
            if (s.contains("SpellID")) {
                spellInfo = s.replaceFirst(".*" + Pattern.quote("SpellID") + ":?", "").trim();
                break;
            }

        int start = spellInfo.lastIndexOf("(") + 1;
        this.spellId = spellInfo.substring(start, spellInfo.length() - 1);
        this.spellName = spellInfo.replaceFirst(Pattern.quote("(") + ".*", "").trim();

        Integer index = getLineIndexThatContainsPrefix(lines, "(Target) Flags:");
        String[] split = lines.get(index).split("\\s+");
        String targetFlagsString = split[split.length - 1].replace('(', ' ').replace(')', ' ').trim();
        targetFlags=Integer.valueOf(targetFlagsString);
        if(targetFlags == 0){
            target = this.caster;
        }
        else if(targetFlags == 2){
            target = ExtractUnit(lines.get(getLineIndexThatContainsPrefix(lines, "(Cast) (Target) Unit: Full:")));
        }
        else
            target = this.caster;
        /*
        (Cast) (Target) Name:
(Cast) [0] HitTarget: Full: 0x202F3C428010DB40004427000060DBBA Creature/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347706
(Cast) [1] HitTarget: Full: 0x202F3C428010DB40004427000060DBBE Creature/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347710
(Cast) [2] HitTarget: Full: 0x202F3C428010DB40004427000060DBBF Creature/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347711
(Cast) [3] HitTarget: Full: 0x202F3C428010DB40004427000060DC2E Creature/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347822
(Cast) [4] HitTarget: Full: 0x202F3C428010DB40004427000060DC30 Creature/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347824
(Cast) [5] HitTarget: Full: 0x202F3C428010DB40004427000060DC32 Creature/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347826
(Cast) [6] HitTarget: Full: 0x202F3C428010DB40004427000060DC34 Creature/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347828
(Cast) [7] HitTarget: Full: 0x202F3C428010DB40004427000060DC9E Creature/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347934
(Cast) [8] HitTarget: Full: 0x202F3C428010DB40004427000060DCA0 Creature/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347936
(Cast) [9] HitTarget: Full: 0x202F3C428010DB40004427000060DCA2 Creature/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347938
(Cast) [10] HitTarget: Full: 0x202F3C428010DB40004427000060DCA4 Creature/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347940
(Cast) [11] HitTarget: Full: 0x083220000000000000000000074A135A Player/0 R3208/S0 Map: 0 (Eastern Kingdoms) Low: 122295130
        */
    }

    public String getSpellId() {
        return spellId;
    }

    public void setSpellId(String spellId) {
        this.spellId = spellId;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }
}
