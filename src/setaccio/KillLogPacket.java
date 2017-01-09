package setaccio;

import java.util.List;
import static setaccio.Tools.ExtractUnit;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class KillLogPacket extends Packet {

    public KillLogPacket() {
        super.setOpcode("SMSG_PARTY_KILL_LOG");
    }
    
    /*
    ServerToClient: SMSG_PARTY_KILL_LOG (0x2798) Length: 24 ConnIdx: 1 Time: 12/26/2016 07:02:29.102 Number: 56912
    PlayerGUID: Full: 0x083220000000000000000000074A135A Player/0 R3208/S0 Map: 0 (Eastern Kingdoms) Low: 122295130
    VictimGUID: Full: 0x202F3C428010DB40004427000060DBBC Unit/0 R3023/S17447 Map: 532 (Karazhan) Entry: 17261 (Restless Skeleton) Low: 6347708
    */
    
    @Override
    public void parseInfo(List<String> lines) {
        setOwner(ExtractUnit(lines.get(1)));
    }
}
