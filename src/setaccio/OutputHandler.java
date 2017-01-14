package setaccio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import static setaccio.InputHandler.hmap;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class OutputHandler {

    public static void WriteOutputFile() {
        try{
            PrintWriter writer = new PrintWriter("CastsFiltrados.txt", "UTF-8");

            Iterator<String> iter = hmap.keySet().iterator();
            while(iter.hasNext()){
                String key = iter.next();
                Unit info = hmap.get(key);
                if (info != null) {
                    //for (SpellCast cast : info.getCasts()) {
                    //    System.out.println("Time: " + cast.getTime() + " Id: " + cast.getSpellId() + " (" + cast.getNome() + ") Target: " + cast.getTarget());
                    //    writer.println(cast.getTime() + " " + cast.getSpellId() + " " + cast.getNome() + " " + cast.getTarget());
                    //}
                }
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Falha ao escrever Arquivo: " + ex.toString());
        }
    }
}
