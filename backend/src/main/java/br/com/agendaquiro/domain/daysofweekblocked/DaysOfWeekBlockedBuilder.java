package br.com.agendaquiro.domain.daysofweekblocked;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DaysOfWeekBlockedBuilder {

    private class BlockedTime {
        public BlockedTime(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
        LocalTime startTime;
        LocalTime endTime;
    }

    private DaysOfWeekBlocked daysOfWeekBlocked = new DaysOfWeekBlocked();
    private List<BlockedTime> blockedTimes = new ArrayList<>();

    public DaysOfWeekBlockedBuilder blockSunday() {
        return block(DayOfWeek.SUNDAY);
    }

    public DaysOfWeekBlockedBuilder blockSaturday() {
        return block(DayOfWeek.SATURDAY);
    }

    public DaysOfWeekBlockedBuilder block(DayOfWeek dayOfWeek) {
        daysOfWeekBlocked.block(dayOfWeek);
        return this;
    }

    public DaysOfWeekBlocked build() {
        processAllBlockedTimes();
        return daysOfWeekBlocked;
    }

    public DaysOfWeekBlockedBuilder block(DayOfWeek monday, LocalTime startTime, LocalTime endTime) {
        daysOfWeekBlocked.block(monday, startTime, endTime);
        return this;
    }

    public DaysOfWeekBlockedBuilder blockAllDays(LocalTime startTime, LocalTime endTime) {
        blockedTimes.add(new BlockedTime(startTime, endTime));
        return this;
    }

    public void processAllBlockedTimes() {
        ArrayList<DayOfWeek> daysOfWeek = createDaysOfWeekList();
        List<DayOfWeek> blockedDays = daysOfWeekBlocked.getWholeDaysOfWeekBlocked();
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
}
