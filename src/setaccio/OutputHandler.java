package setaccio;

import Packets.AiReactionPacket;
import Packets.AttackStartPacket;
import Packets.KillLogPacket;
import Packets.SpellGoPacket;
import Packets.SpellStartPacket;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
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

            if (filteredInfo != null) {
                for (Unit unit : filteredInfo) {
                    unit.sortAllPackets();
                    for (Packet p : unit.getPacketList()) {
                        switch(p.getOpcode()) {
                            case "SMSG_AURA_UPDATE":
                                //packet = new AuraUpdatePacket();
                                break;
                            case "SMSG_SPELL_START":
                                p = (SpellStartPacket)p;
                                break;
                            case "SMSG_SPELL_GO":
                                p = (SpellGoPacket)p;
                                break;
                            case "SMSG_ATTACK_START":
                                p = (AttackStartPacket)p;
                                break;
                            case "SMSG_AI_REACTION":
                                p = (AiReactionPacket)p;
                                break;
                            case "SMSG_PARTY_KILL_LOG":
                                p = (KillLogPacket)p;
                                break;
                            default:
                                break;
                        }
                        
                        System.out.println("-------Result: " + p.toString());
                        writer.println(p.toString());
                    }
                }
            } else {
                for (Map.Entry<String, Unit> entry : hmap.entrySet()) {
                    Unit unit = entry.getValue();
                    if (unit != null) {
                        unit.sortAllPackets();
                        for (Packet p : unit.getPacketList()) {
                            switch(p.getOpcode()) {
                                case "SMSG_AURA_UPDATE":
                                    //packet = new AuraUpdatePacket();
                                    break;
                                case "SMSG_SPELL_START":
                                    p = (SpellStartPacket)p;
                                    break;
                                case "SMSG_SPELL_GO":
                                    p = (SpellGoPacket)p;
                                    break;
                                case "SMSG_ATTACK_START":
                                    p = (AttackStartPacket)p;
                                    break;
                                case "SMSG_AI_REACTION":
                                    p = (AiReactionPacket)p;
                                    break;
                                case "SMSG_PARTY_KILL_LOG":
                                    p = (KillLogPacket)p;
                                    break;
                                default:
                                    break;
                            }

                            //System.out.println(p.toString());
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
