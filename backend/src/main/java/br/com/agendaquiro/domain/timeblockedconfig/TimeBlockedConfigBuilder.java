package br.com.agendaquiro.domain.timeblockedconfig;

import br.com.agendaquiro.domain.professionalservice.ProfessionalService;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeBlockedConfigBuilder {

    private ProfessionalBlockTimeConfig professionalBlockTimeConfig;
    private List<BlockedTime> blockedTimes = new ArrayList<>();

    public TimeBlockedConfigBuilder(ProfessionalService professional) {
        this.professionalBlockTimeConfig = new ProfessionalBlockTimeConfig(professional);
    }

    public TimeBlockedConfigBuilder blockSunday() {
        return block(DayOfWeek.SUNDAY);
    }

    public TimeBlockedConfigBuilder blockSaturday() {
        return block(DayOfWeek.SATURDAY);
    }

    public TimeBlockedConfigBuilder block(DayOfWeek dayOfWeek) {
        professionalBlockTimeConfig.block(dayOfWeek);
        return this;
    }

    public ProfessionalBlockTimeConfig build() {
        processAllBlockedTimes();
        return professionalBlockTimeConfig;
    }

    public TimeBlockedConfigBuilder block(DayOfWeek monday, LocalTime startTime, LocalTime endTime) {
        professionalBlockTimeConfig.block(monday, startTime, endTime);
        return this;
    }

    public TimeBlockedConfigBuilder blockAllDays(LocalTime startTime, LocalTime endTime) {
        blockedTimes.add(new BlockedTime(startTime, endTime));
        return this;
    }

    private void processAllBlockedTimes() {
        ArrayList<DayOfWeek> daysOfWeek = createDaysOfWeekList();
        List<DayOfWeek> blockedDays = professionalBlockTimeConfig.getWholeDaysOfWeekBlocked();
        for (DayOfWeek blockedDay : blockedDays) {
            daysOfWeek.remove(blockedDay);
        }
        for (DayOfWeek dayOfWeek : daysOfWeek) {
            for (BlockedTime blockedTime : blockedTimes) {
                block(dayOfWeek, blockedTime.startTime, blockedTime.endTime);
            }
        }
    }

    public static ArrayList<DayOfWeek> createDaysOfWeekList() {
        ArrayList days = new ArrayList();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.TUESDAY);
        days.add(DayOfWeek.WEDNESDAY);
        days.add(DayOfWeek.THURSDAY);
        days.add(DayOfWeek.FRIDAY);
        days.add(DayOfWeek.SATURDAY);
        days.add(DayOfWeek.SUNDAY);
        return days;
    }

    private class BlockedTime {
        public BlockedTime(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
        LocalTime startTime;
        LocalTime endTime;
    }

}
