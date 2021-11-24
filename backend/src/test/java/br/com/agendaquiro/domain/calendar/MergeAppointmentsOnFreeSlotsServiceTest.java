package br.com.agendaquiro.domain.calendar;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MergeAppointmentsOnFreeSlotsServiceTest {

    private MergeAppointmentsOnFreeSlotsService service;

    @Test
    public void shouldMergeWithDifferentStartTime() {
        service = new MergeAppointmentsOnFreeSlotsService();
        //GIVEN A FREE SLOTS
        List<PeriodSlot> freeSlots = new ArrayList<>();
        PeriodSlot nonConflictedSlotA = PeriodSlot.builder()
                .date(LocalDate.of(2021, 10, 11))
                .startTime(LocalTime.of(13, 00)).endTime(LocalTime.of(14, 00))
                .build();
        freeSlots.add(nonConflictedSlotA);
        PeriodSlot conflictFreeSlot = PeriodSlot.builder()
                .date(LocalDate.of(2021, 10, 11))
                .startTime(LocalTime.of(14, 00)).endTime(LocalTime.of(15, 00))
                .build();
        freeSlots.add(conflictFreeSlot);
        PeriodSlot nonConflictedSlotB = PeriodSlot.builder()
                .date(LocalDate.of(2021, 10, 11))
                .startTime(LocalTime.of(15, 00)).endTime(LocalTime.of(16, 00))
                .build();
        freeSlots.add(nonConflictedSlotB);

        //GIVEN an appointment
        List<PeriodSlot> appointments = new ArrayList<>();
        PeriodSlot appointment = PeriodSlot.builder()
                .date(LocalDate.of(2021, 10, 11))
                .startTime(LocalTime.of(14, 10)).endTime(LocalTime.of(15, 00))
                .build();
        appointments.add(appointment);

        //WHEN asked to merge
        List<PeriodSlot> mergedSlots = service.merge(freeSlots, appointments);

        //THEN
        assertThat(mergedSlots).contains(appointment);
        assertThat(mergedSlots).contains(nonConflictedSlotA);
        assertThat(mergedSlots).contains(nonConflictedSlotB);
        assertThat(mergedSlots).doesNotContain(conflictFreeSlot);
    }

    @Test
    public void shouldMergeWithSameStartEndTime() {
        service = new MergeAppointmentsOnFreeSlotsService();
        //GIVEN A FREE SLOTS
        List<PeriodSlot> freeSlots = new ArrayList<>();
        PeriodSlot nonConflictedSlotA = PeriodSlot.builder()
                .date(LocalDate.of(2021, 10, 11))
                .startTime(LocalTime.of(13, 00)).endTime(LocalTime.of(14, 00))
                .build();
        freeSlots.add(nonConflictedSlotA);
        PeriodSlot conflictFreeSlot = PeriodSlot.builder()
                .date(LocalDate.of(2021, 10, 11))
                .startTime(LocalTime.of(14, 00)).endTime(LocalTime.of(15, 00))
                .build();
        freeSlots.add(conflictFreeSlot);
        PeriodSlot nonConflictedSlotB = PeriodSlot.builder()
                .date(LocalDate.of(2021, 10, 11))
                .startTime(LocalTime.of(15, 00)).endTime(LocalTime.of(16, 00))
                .build();
        freeSlots.add(nonConflictedSlotB);

        //GIVEN an appointment
        List<PeriodSlot> appointments = new ArrayList<>();
        PeriodSlot appointment = PeriodSlot.builder()
                .date(LocalDate.of(2021, 10, 11))
                .startTime(LocalTime.of(14, 00)).endTime(LocalTime.of(15, 00))
                .build();
        appointments.add(appointment);

        //WHEN asked to merge
        List<PeriodSlot> mergedSlots = service.merge(freeSlots, appointments);

        //THEN
        assertThat(mergedSlots).contains(appointment);
        assertThat(mergedSlots).contains(nonConflictedSlotA);
        assertThat(mergedSlots).contains(nonConflictedSlotB);
        assertThat(mergedSlots).doesNotContain(conflictFreeSlot);
    }
    @Test
    public void shouldMergeWithDifferentEndTime() {
        service = new MergeAppointmentsOnFreeSlotsService();
        //GIVEN A FREE SLOTS
        List<PeriodSlot> freeSlots = new ArrayList<>();
        PeriodSlot nonConflictedSlotA = PeriodSlot.builder()
                .date(LocalDate.of(2021, 10, 11))
                .startTime(LocalTime.of(13, 00)).endTime(LocalTime.of(14, 00))
                .build();
        freeSlots.add(nonConflictedSlotA);
        PeriodSlot conflictFreeSlot = PeriodSlot.builder()
                .date(LocalDate.of(2021, 10, 11))
                .startTime(LocalTime.of(14, 00)).endTime(LocalTime.of(14, 45))
                .build();
        freeSlots.add(conflictFreeSlot);
        PeriodSlot nonConflictedSlotB = PeriodSlot.builder()
                .date(LocalDate.of(2021, 10, 11))
                .startTime(LocalTime.of(15, 00)).endTime(LocalTime.of(16, 00))
                .build();
        freeSlots.add(nonConflictedSlotB);

        //GIVEN an appointment
        List<PeriodSlot> appointments = new ArrayList<>();
        PeriodSlot appointment = PeriodSlot.builder()
                .date(LocalDate.of(2021, 10, 11))
                .startTime(LocalTime.of(14, 00)).endTime(LocalTime.of(15, 00))
                .build();
        appointments.add(appointment);

        //WHEN asked to merge
        List<PeriodSlot> mergedSlots = service.merge(freeSlots, appointments);

        //THEN
        assertThat(mergedSlots).contains(appointment);
        assertThat(mergedSlots).contains(nonConflictedSlotA);
        assertThat(mergedSlots).contains(nonConflictedSlotB);
        assertThat(mergedSlots).doesNotContain(conflictFreeSlot);
    }

}
