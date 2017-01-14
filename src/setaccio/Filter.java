package setaccio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static setaccio.InputHandler.hmap;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class Filter {

    public List FilterByEntry(String npcEntry) {
        List<Unit> auxList = new ArrayList<>();
        for (Map.Entry<String,Unit> entry : hmap.entrySet())
        {
            if (entry.getValue().getEntry().equals(npcEntry))
                auxList.add(entry.getValue());
        }
        return auxList;
    }
}
