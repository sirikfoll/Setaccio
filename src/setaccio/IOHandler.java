package setaccio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class IOHandler {
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
            SpellCast sc = new SpellCast();
            FilteredInfo f = new FilteredInfo();
            String linha = br.readLine();

            while (linha != null)
            {
                if (linha.contains(paramPacket)) {
                    int index = linha.indexOf("Time:");
                    sc.setTime((String)linha.subSequence(index + 16, index + 29));
                    linha = br.readLine();
                    if (linha.contains("Entry: " + paramNpcEntry)) {
                        f.setNpcId(paramNpcEntry);
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
                        linha = br.readLine();
                        if (linha.contains("Entry: ")) {
                            sc.setTarget(linha.substring(linha.indexOf("Entry: ") + 7, linha.indexOf("Low:") - 1));
                            System.out.println("Target: " + sc.getTarget());
                        }
                        if (hmap.containsKey(paramNpcEntry))
                            hmap.get(paramNpcEntry).addCast(sc);
                        else
                            f.addCast(sc);
                        hmap.put(f.getNpcId(), f);
                    }
                }
                linha = br.readLine();
            }
            br.close();
            
            /*
            Iterator<String> iter = hmap.keySet().iterator();
            while(iter.hasNext()){
                String key = iter.next();
                if (key == paramSpell) {
                    hmap.get(key);
                }
            }
            */
        } catch(IOException ex) {
            System.out.println("Falha na Leitura: " + ex.toString());
        } /*catch(NullPointerException ex) {
            System.out.println("Falha na Leitura: " + ex.toString());
        }*/
    }
}