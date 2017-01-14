package setaccio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class InputHandler {
    public static HashMap<String, Unit> hmap = new HashMap<>();
    public static List<String> list = new ArrayList<>();

    public static String FindFirstPacketLine(BufferedReader br) {
        String lines;
        try {
            lines = br.readLine();
            while (lines != null && !lines.isEmpty()) {
                lines = br.readLine();
            }
            lines = br.readLine();
            return lines;
        } catch (IOException ex) {
            System.out.println("Failed to find next packet.");
        }
        return null;
    }
    
    public static String FindLastPacketLine(BufferedReader br) {
        String line;
        try {
            line = br.readLine();
            while (line != null && !line.isEmpty()) {
                line = br.readLine();
            }
            return line;
        } catch (IOException ex) {
            System.out.println("Failed to find last packet line.");
        }
        return null;
    }

    public static void DoFilterFile(String nomeArq, String paramPacket, String paramNpcEntry) {
        try {
            FileReader file = new FileReader(nomeArq);
            BufferedReader br = new BufferedReader(file);
            String linha = FindFirstPacketLine(br);
            while (linha != null && !linha.isEmpty())
            {
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
                        System.out.println("Contains: " + packet.getOwner().getGuid() + " Opcode: " + packet.getOpcode()
                        + "" + packet.getOwner().getPacketsByType(opcode));
                    }
                    else {
                        packet.getOwner().addPacket(packet);
                        hmap.put(packet.getOwner().getGuid(), packet.getOwner());
                    }
                    
                    List<Packet> l = packet.getOwner().getPacketList();
                    for (Packet p : l) {
                        System.out.println("Packet: " + p.getOpcode() + " Owner: " + p.getOwner().getGuid() + " Entry: " + p.getOwner().getEntry() + " Nome: " + p.getOwner().getName());
                    }
                }
                linha = FindFirstPacketLine(br);
            }
            br.close();

            /*
            Iterator it = hmap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Unit u = (Unit)pair.getValue();
                if (u != null) {
                System.out.println("Founded.");
                List<Packet> l = u.getPacketList();
                for (Packet p : l) {
                    System.out.println("Packet: " + p.getOpcode() + " Owner: " + p.getOwner().getGuid() + " Entry: " + p.getOwner().getEntry() + " Nome: " + p.getOwner().getName());
                }
            }
                //System.out.println("Guid: " + pair.getKey() + " Type: " + u.getType() + " Name: " + u.getName() + " Entry: " + u.getEntry());
                it.remove();
            }
            Unit u = hmap.get("0x202F3C428010D240004427000060D812");
            if (u != null) {
                System.out.println("Founded.");
                List<Packet> l = u.getPacketList();
                for (Packet p : l) {
                    System.out.println("Packet: " + p.getOpcode() + " Owner: " + p.getOwner().getGuid() + " Entry: " + p.getOwner().getEntry() + " Nome: " + p.getOwner().getName());
                }
            }
            */
            System.out.println("----------------------FIM-----------------------------");
            //WriteOutputFile();
        } catch(IOException ex) {
            System.out.println("Falha na Leitura: " + ex.toString());
        } /*catch(NullPointerException ex) {
            System.out.println("Falha na Leitura: " + ex.toString());
        }*/
    }
}