package setaccio;

import Packets.AiReactionPacket;
import Packets.AttackStartPacket;
import Packets.KillLogPacket;
import Packets.SpellGoPacket;
import Packets.SpellStartPacket;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class InputHandler {
    public static HashMap<String, Unit> hmap = new HashMap<>();
    public static List<String> list = new ArrayList<>();

    public static void DoResetDataStores() {
        hmap.clear();
        list.clear();
    }

    public static String FindFirstPacketLine(String line, BufferedReader br) {
        try {
            line = br.readLine();
            while (line != null) {
                if (IsPacket(line))
                    return line;
                line = br.readLine();
            }
            return null;
        } catch (IOException ex) {
            System.out.println("Failed to find next packet.");
        }
        return null;
    }

    private static boolean IsPacket(String line) {
        if (line != null && (line.contains("ServerToClient") || line.contains("ClientToServer")))
            return true;
        return false;
    }

    public static void DoFilterFile(String nomeArq) {
        try {
            FileReader file = new FileReader(nomeArq);
            BufferedReader br = new BufferedReader(file);
            String linha = "";
            linha = FindFirstPacketLine(linha, br);

            while (linha != null && !linha.isEmpty())
            {
                if (linha.split(" ").length < 2)
                    System.out.println("Crash split Opcode: " + linha);
                String opcode = (linha.split(" "))[1];
                Packet packet = null;
                switch(opcode) {
                    case "SMSG_AURA_UPDATE":
                        //packet = new AuraUpdatePacket();
                        break;
                    case "SMSG_SPELL_START":
                        packet = new SpellStartPacket();
                        break;
                    case "SMSG_SPELL_GO":
                        packet = new SpellGoPacket();
                        break;
                    case "SMSG_ATTACK_START":
                        packet = new AttackStartPacket();
                        break;
                    case "SMSG_AI_REACTION":
                        packet = new AiReactionPacket();
                        break;
                    case "SMSG_PARTY_KILL_LOG":
                        packet = new KillLogPacket();
                        break;
                    default:
                        break;
                }

                if (packet != null) {
                    //Loop till the packet ends
                    while (linha != null && !linha.isEmpty()) {
                        //acumular
                        list.add(linha);
                        linha = br.readLine();
                    }

                    packet.parseInfo(list);
                    list.clear();

                    if (hmap.containsKey(packet.getOwner().getGuid())) {
                        hmap.get(packet.getOwner().getGuid()).addPacket(packet);
                    }
                    else {
                        packet.getOwner().addPacket(packet);
                        hmap.put(packet.getOwner().getGuid(), packet.getOwner());
                    }
                }
                linha = FindFirstPacketLine(linha, br);
            }
            br.close();

            for (Map.Entry<String, Unit> entry : hmap.entrySet())
            {
                //entry.getValue().printAllPackets();
            }
            System.out.println("----------------------FIM-----------------------------");
            //WriteOutputFile();
        } catch(IOException ex) {
            System.out.println("Falha na Leitura: " + ex.toString());
        } /*catch(NullPointerException ex) {
            System.out.println("Falha na Leitura: " + ex.toString());
        }*/
    }
}
