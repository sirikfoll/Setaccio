package setaccio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import static setaccio.InputHandler.hmap;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class Filter {
    public static List<Unit> filteredInfo = new ArrayList<>();

    public void FilterByEntry(String npcEntry) {
            for (Map.Entry<String,Unit> entry : hmap.entrySet()) {
                if (entry.getValue().getEntry().equals(npcEntry)) {
                    Unit tmpUnit = new Unit(entry.getValue());
                    filteredInfo.add(tmpUnit);
                } else
                    filteredInfo.remove(entry.getValue());
            }
    }
    
    public void FilterByPacket(String type) {
        for (Map.Entry<String,Unit> entry : hmap.entrySet()) {
            if (entry.getValue().hasPacketType(type)) {
                Unit tmpUnit = new Unit(entry.getValue());
                tmpUnit.removePacketsExceptType(type);
                filteredInfo.add(tmpUnit);
            }
        }
    }
    
    public void FilterBySpell(String spellID) {
        for (Iterator<Unit> it = filteredInfo.iterator(); it.hasNext();) {
                Unit unit = it.next();
                unit.removePacketsExceptSpellID(spellID);
        }
    }

    public List getfilteredInfo() {
        return filteredInfo;
    }
}
