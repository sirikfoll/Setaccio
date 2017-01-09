package setaccio;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */

public class Tools {
    /**
     * removes the prefix from the line and everything on its left.
     * @author chaouki
     * @param line
     * @param prefix
     * @return
     */
    public static String removePrefix(String line, String prefix){
        return line.replaceFirst(".*" + Pattern.quote(prefix) + ":?", "").trim();
    }

    /**
     * removes the prefix from the line and get first word.
     * @author chaouki
     * @param line
     * @param prefix
     * @return
     */
    public static String removePrefixAndGetFirstElement(String line, String prefix){
        String[] split = removePrefix(line, prefix).split("\\s+");
        return split[0];
    }

    /**
     * removes the prefix from the line and get the last word.
     * @author chaouki
     * @param line
     * @param prefix
     * @return
     */
    public static String removePrefixAndGetLastElement(String line, String prefix) {
        String[] split = removePrefix(line, prefix).split("\\s+");
        return split[split.length-1];
    }

    /**
     * Line Examples
     * 0x2C2F3C4280BD8B00004427000060D812 GameObject/0 R3023/S17447 Map: 532 (Karazhan) Entry: 194092 (Blackened Urn) Low: 6346770
     *          0                              1             2       3    4      5        6      7          8          9     10
     * 0x202F3C4280152800004427000060D812 Creature/0   R3023/S17447 Map: 532 (Karazhan) Entry: 21664  (Human Charger) Low: 6346770
     *          0                            1               2       3    4      5        6      7          8          9     10
     * 0x083220000000000000000000074A135A Player/0     R3208/S0     Map: 0 (Eastern Kingdoms) Low: 122295130
     *          0                            1             2         3   4          5          6       7
     * 0x203CB8428014AA000068DB00006033FC Creature/0   R3886/S26843 Map: 532 Entry: 21160 Low: 6304764
     *          0                            1             2         3   4     5     6    7      8
     * 0x2C3CB84280BD8B000068DB000060326A GameObject/0 R3886/S26843 Map: 532 Entry: 194092 Low: 6304362
     *          0                            1             2         3   4     5     6    7      8
     * @param line
     * @return Unit
     */
    public static Unit ExtractUnit(String line) {
        line = line.replaceFirst(".*" + Pattern.quote("Full:") + ":?", "").trim();

        if (line.equals("0x0"))
            return null;

        String[] words=line.split("\\s+");
        String type = words[1].replace("/0", "");
        String entry = null;
        String name = null;
        Unit unit = new Unit();

        if (!type.equals("Player")) {
            if (words.length <= 8)
                entry = words[6];
            else
                entry = words[7];
        }

        if (words.length > 10) {
            int start = line.indexOf("(", line.indexOf("Entry")) + 1;
            int end = line.indexOf("Low") - 2;
            name = line.substring(start, end);
        }
        unit.setGuid(words[0]);
        unit.setEntry(entry);
        unit.setType(type);
        unit.setName(name);
        return unit;
    }

    public static String[] parseSpellInfo(String line) {
        line = line.replaceFirst(".*" + Pattern.quote("SpellID") + ":?", "").trim();
        return line.split(" ");
    }

    public static int getLineIndexThatContainsPrefix(List<String> lines, String prefix) {
        for(int i=0 ; i < lines.size() ; i++)
            if (lines.get(i).contains(prefix))
                return i;

        return 0;
    }
}
