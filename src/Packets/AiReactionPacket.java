package Packets;

import java.util.List;
import setaccio.Packet;
import static setaccio.Tools.ExtractUnit;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class AiReactionPacket extends Packet {
    private String status;

    public AiReactionPacket() {
        super.setOpcode("SMSG_AI_REACTION");
    }

    @Override
    public String toString() {
        return super.toString() + " " + status;
    }

    @Override
    public String toString(boolean simplified) {
        return this.toString();
    }
    /*
    ServerToClient: SMSG_AI_REACTION (0x26DA) Length: 19 ConnIdx: 1 Time: 12/26/2016 07:02:29.555 Number: 57003
    UnitGUID: Full: 0x202F3C428010DB40004427000060DCA5 Unit/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347941
    Reaction: Hostile (2)
    */
    @Override
    public void parseInfo(List<String> lines) {
        super.setOwner(ExtractUnit(lines.get(1)));
        super.parseDateTime(lines.get(0));
        this.setStatus((lines.get(2).split("\\s+"))[1]);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
