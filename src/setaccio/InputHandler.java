package setaccio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import static setaccio.OutputHandler.WriteOutputFile;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class InputHandler {
    public static HashMap<String, FilteredInfo> hmap = new HashMap<>();

    private static void FiltrarSpellId(String input, SpellCast sc) {
        int inicio = input.indexOf("(", 16);
        int fim = input.indexOf(")", 16);
        String nome = input.substring(16, inicio - 1);
        String id = input.substring(inicio + 1, fim);
        sc.setSpellId(id);
        sc.setNome(nome);
    }

    public static void DoFilterFile(String nomeArq, String paramPacket, String paramNpcEntry) {
        try {
            FileReader file = new FileReader(nomeArq);
            BufferedReader br = new BufferedReader(file);
            String linha = br.readLine();

            while (linha != null)
            {
                if (linha.contains(paramPacket)) {
                    int index = linha.indexOf("Time:");
                    String time =(String)linha.subSequence(index + 16, index + 29);
                    linha = br.readLine();
                    if (linha.contains("Entry: " + paramNpcEntry)) {
                        SpellCast sc = new SpellCast();
                        FilteredInfo f = new FilteredInfo();
                        sc.setTime(time);
                        f.setNpcId(paramNpcEntry);
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        FiltrarSpellId(linha, sc);
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        linha = br.readLine();
                        if (linha.contains("Entry: ")) {
                            sc.setTarget(linha.substring(linha.indexOf("Entry: ") + 7, linha.indexOf("Low:") - 1));
                        } else if (linha.contains("Player")) {
                            sc.setTarget("Player");
                        }
                        if (hmap.containsKey(paramNpcEntry))
                            hmap.get(paramNpcEntry).addCast(sc);
                        else {
                            f.addCast(sc);
                            hmap.put(f.getNpcId(), f);
                        }
                    }
                }
                linha = br.readLine();
            }
            br.close();
            
            WriteOutputFile();
        } catch(IOException ex) {
            System.out.println("Falha na Leitura: " + ex.toString());
        } /*catch(NullPointerException ex) {
            System.out.println("Falha na Leitura: " + ex.toString());
        }*/
    }
}