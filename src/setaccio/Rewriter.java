package setaccio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static setaccio.InputHandler.FindFirstPacketLine;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class Rewriter {
    private List<String> list = new LinkedList<>();

    public void removeLineFromFile(String file, String lineToRemove) {
 
        try {
            File inFile = new File(file);
            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }
            File tempFile = new File(inFile.getAbsolutePath() + "_Parsed.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            
            String line = "";
            String tmpLine;
            line = FindFirstPacketLine(line, br);
            while (line != null && !line.isEmpty()) {
                while (line != null && !line.isEmpty()) {
                    list.add(line + "\n");
                    line = br.readLine();
                }
                if (list.size() > 1 && (tmpLine = list.get(1)) != null && !tmpLine.contains("Entry: " + lineToRemove)) {
                    pw.println(list);
                    pw.flush();
                }
                list.clear();
                line = FindFirstPacketLine(line, br);
            }
            pw.close();
            br.close();
            System.out.println("Fim - Apagar");
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //if (!tempFile.renameTo(inFile))
            //System.out.println("Could not rename file");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
