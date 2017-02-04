package setaccio;

import Packets.SpellGoPacket;
import Packets.SpellStartPacket;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import static setaccio.Filter.filteredInfo;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class TimerAnalyzer {
    public List<LocalDateTime> cooldowns;
    private LocalDateTime fromDateTimeSGO;
    private LocalDateTime toDateTimeSGO;
    private LocalDateTime fromDateTimeStart;
    private LocalDateTime toDateTimeStart;
    boolean _isFirstGO = true;
    boolean _isFirstStart = true;
    
    public TimerAnalyzer(String entry) {

        if (filteredInfo != null) {
            for (Unit unit : filteredInfo) {
                unit.sortAllPackets();
                for (Packet p : unit.getPacketList()) {
                    switch(p.getOpcode()) {
                        case "SMSG_ATTACK_START":
                            _isFirstGO = false;
                            _isFirstStart = false;
                            fromDateTimeSGO = p.getDateTime();
                            fromDateTimeStart = p.getDateTime();
                            break;
                        case "SMSG_SPELL_GO":
                        {
                            if (fromDateTimeSGO == null) {
                                _isFirstGO = false;
                                fromDateTimeSGO = p.getDateTime();
                            }
                            p = (SpellGoPacket)p;
                            toDateTimeSGO = p.getDateTime();
                            LocalDateTime tempDateTime = LocalDateTime.from( fromDateTimeSGO );
                            long years = tempDateTime.until( toDateTimeSGO, ChronoUnit.YEARS);
                            tempDateTime = tempDateTime.plusYears( years );
                            long months = tempDateTime.until( toDateTimeSGO, ChronoUnit.MONTHS);
                            tempDateTime = tempDateTime.plusMonths( months );
                            long days = tempDateTime.until( toDateTimeSGO, ChronoUnit.DAYS);
                            tempDateTime = tempDateTime.plusDays( days );
                            long hours = tempDateTime.until( toDateTimeSGO, ChronoUnit.HOURS);
                            tempDateTime = tempDateTime.plusHours( hours );
                            long minutes = tempDateTime.until( toDateTimeSGO, ChronoUnit.MINUTES);
                            tempDateTime = tempDateTime.plusMinutes( minutes );
                            long seconds = tempDateTime.until( toDateTimeSGO, ChronoUnit.SECONDS);
                            tempDateTime = tempDateTime.plusSeconds( seconds );
                            long milliseconds = tempDateTime.until( toDateTimeSGO, ChronoUnit.MILLIS);
                            String timer = "" + minutes + ":" + seconds + ":" + milliseconds;
                            ((SpellGoPacket)p).setTimeUntilNext(timer);
                            fromDateTimeSGO = toDateTimeSGO;
                            break;
                        }
                        case "SMSG_SPELL_START":
                        {
                            if (fromDateTimeStart == null) {
                                _isFirstStart = false;
                                fromDateTimeStart = p.getDateTime();
                            }
                            p = (SpellStartPacket)p;
                            toDateTimeStart = p.getDateTime();
                            LocalDateTime tempDateTime = LocalDateTime.from( fromDateTimeStart );
                            long years = tempDateTime.until( toDateTimeStart, ChronoUnit.YEARS);
                            tempDateTime = tempDateTime.plusYears( years );
                            long months = tempDateTime.until( toDateTimeStart, ChronoUnit.MONTHS);
                            tempDateTime = tempDateTime.plusMonths( months );
                            long days = tempDateTime.until( toDateTimeStart, ChronoUnit.DAYS);
                            tempDateTime = tempDateTime.plusDays( days );
                            long hours = tempDateTime.until( toDateTimeStart, ChronoUnit.HOURS);
                            tempDateTime = tempDateTime.plusHours( hours );
                            long minutes = tempDateTime.until( toDateTimeStart, ChronoUnit.MINUTES);
                            tempDateTime = tempDateTime.plusMinutes( minutes );
                            long seconds = tempDateTime.until( toDateTimeStart, ChronoUnit.SECONDS);
                            tempDateTime = tempDateTime.plusSeconds( seconds );
                            long milliseconds = tempDateTime.until( toDateTimeStart, ChronoUnit.MILLIS);
                            String timer = "" + minutes + ":" + seconds + ":" + milliseconds;
                            ((SpellStartPacket)p).setTimeUntilNext(timer);
                            fromDateTimeStart = toDateTimeStart;
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
        }
    }
}
