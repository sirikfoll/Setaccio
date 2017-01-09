package setaccio;

import java.util.List;
import static setaccio.Tools.removePrefixAndGetLastElement;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class AuraUpdatePacket extends Packet {

    private Unit owner;
    private Integer spellId;

    public AuraUpdatePacket() {
        super.setOpcode("SMSG_AURA_UPDATE");
    }
    
    @Override
    public void parseInfo(List<String> lines) {
        String[] words=lines.get(1).split("\\s+");
        if(!words[0].equals("GUID:") || !words[1].equals("Full:") || !words[3].equals("Type:"))
            return;

        if(words[4].equals("Creature"))
            owner=new Unit();
        owner.setEntry(words[6]);
        owner.setGuid(words[2]);

        /**
         *  Spell ID
         */
        String spellIdString = removePrefixAndGetLastElement(lines.get(3), "Spell ID");
        spellIdString = spellIdString.substring(1, spellIdString.length() - 1);
        spellId=Integer.valueOf(spellIdString);
    }

    public Integer getSpellId() {
        return spellId;
    }
}
