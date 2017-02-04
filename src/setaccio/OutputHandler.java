package setaccio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import static setaccio.Filter.filteredInfo;
import static setaccio.InputHandler.hmap;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class OutputHandler {

    public static void WriteOutputFile() {
        try{
            PrintWriter writer = new PrintWriter("CastsFiltrados.txt", "UTF-8");
            TimerAnalyzer t = new TimerAnalyzer("");
            if (filteredInfo != null && !filteredInfo.isEmpty()) {
                for (Unit unit : filteredInfo) {
                    unit.sortAllPackets();
                    for (Packet p : unit.getPacketList()) {
                        //System.out.println("-- ResultF: " + p.toString());
                        writer.println(p.toString());
                    }
                }
            } else {
                for (Map.Entry<String, Unit> entry : hmap.entrySet()) {
                    Unit unit = entry.getValue();
                    if (unit != null) {
                        unit.sortAllPackets();
                        for (Packet p : unit.getPacketList()) {
                            //System.out.println("-- ResultH: " + p.toString());
                            writer.println(p.toString());
                        }
                    }
                }
            }
            System.out.println("----Finished Writing----");
            writer.close();
        } catch (IOException ex) {
            System.out.println("Falha ao escrever Arquivo: " + ex.toString());
        }
    }
}
