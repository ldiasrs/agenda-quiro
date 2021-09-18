package br.com.agendaquiro.domain.calendar;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class DaysOfTheWeeksBlockedBuilder {

    private class BlockedTime {
        public BlockedTime(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
        LocalTime startTime;
        LocalTime endTime;
    }

    private DaysOfTheWeeksBlocked daysOfTheWeeksBlocked = new DaysOfTheWeeksBlocked();
    private List<BlockedTime> blockedTimes = new ArrayList<>();

    public DaysOfTheWeeksBlockedBuilder blockSunday() {
        return block(DayOfWeek.SUNDAY);
    }

    public DaysOfTheWeeksBlockedBuilder blockSaturday() {
        return block(DayOfWeek.SATURDAY);
    }

    public DaysOfTheWeeksBlockedBuilder block(DayOfWeek dayOfWeek) {
        daysOfTheWeeksBlocked.block(dayOfWeek);
        return this;
    }

    public DaysOfTheWeeksBlocked build() {
        processAllBlockedTimes();
        return daysOfTheWeeksBlocked;
    }

    public DaysOfTheWeeksBlockedBuilder block(DayOfWeek monday, LocalTime startTime, LocalTime endTime) {
        daysOfTheWeeksBlocked.block(monday, startTime, endTime);
        return this;
    }

    public DaysOfTheWeeksBlockedBuilder blockAllDays(LocalTime startTime, LocalTime endTime) {
        blockedTimes.add(new BlockedTime(startTime, endTime));
        return this;
    }

    public void processAllBlockedTimes() {
        for (BlockedTime blockedTime : blockedTimes) {
            ArrayList<DayOfWeek> daysOfWeek = createDaysOfWeekList();
            Set<DayOfWeek> blockedDays = daysOfTheWeeksBlocked.getWholeDaysOfWeekBlocked();
            for (DayOfWeek blockedDay : blockedDays) {
                daysOfWeek.remove(blockedDay);
            }
            for (DayOfWeek dayOfWeek : daysOfWeek) {
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
