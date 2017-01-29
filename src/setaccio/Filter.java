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

    public List FilterByEntry(String npcEntry) {
        for (Map.Entry<String,Unit> entry : hmap.entrySet()) {
            if (entry.getValue().getEntry().equals(npcEntry))
                filteredInfo.add(entry.getValue());
        }
        //} else {
        //    for (Iterator<Unit> it = filteredInfo.iterator(); it.hasNext();) {
        //        Unit unit = it.next();
        //        if (!unit.getEntry().equals(npcEntry))
        //            it.remove();
        //    }

        return filteredInfo;
    }
    
    public List FilterByPacket(String type) {
        for (Map.Entry<String,Unit> entry : hmap.entrySet()) {
            if (entry.getValue().hasPacketType(type)) {
                entry.getValue().removePacketsExceptType(type);
                filteredInfo.add(entry.getValue());
            }
        }
        //} else {
        //    for (Unit unit : filteredInfo)
        //    {
        //        unit.removePacketsExceptType(type);
        //    }
        return filteredInfo;
    }

    public List getfilteredInfo() {
        return filteredInfo;
    }
    
}
