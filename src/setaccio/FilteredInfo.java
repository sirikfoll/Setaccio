package setaccio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class FilteredInfo {
    private String npcId;
    private String spellId;
    private String combatStart;
    private ArrayList<SpellCast> casts = new ArrayList<>();

    public String getNpcId() {
        return npcId;
    }

    public void setNpcId(String npcId) {
        this.npcId = npcId;
    }

    public String getSpellId() {
        return spellId;
    }

    public void setSpellId(String spellId) {
        this.spellId = spellId;
    }

    public String getCombatStart() {
        return combatStart;
    }

    public void setCombatStart(String combatStart) {
        this.combatStart = combatStart;
    }

    public ArrayList<SpellCast> getCasts() {
        return casts;
    }
    
    public HashSet<String> getUniqueCasts() {
        HashSet<String> hset = new HashSet<>();
        for (SpellCast sc : casts)
            hset.add(sc.getSpellId());
        return hset;
    }
    
    public ArrayList<SpellCast> getCasts(String spellId) {
        ArrayList<SpellCast> tmpCasts = new ArrayList<>();
        casts.stream().filter((sc) -> (sc.getSpellId().equals(spellId))).forEachOrdered((sc) -> {
            tmpCasts.add(sc);
        });
        return tmpCasts;
    }

    public void setCasts(ArrayList<SpellCast> casts) {
        this.casts = casts;
    }
    
    public void addCast(SpellCast sc) {
        casts.add(sc);
    }
}
