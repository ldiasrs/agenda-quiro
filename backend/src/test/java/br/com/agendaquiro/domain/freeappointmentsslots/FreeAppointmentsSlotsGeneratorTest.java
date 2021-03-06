package br.com.agendaquiro.domain.freeappointmentsslots;

import br.com.agendaquiro.domain.calendar.PeriodSlot;
import br.com.agendaquiro.domain.professsional.Professional;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;
import br.com.agendaquiro.domain.timeblockedconfig.TimeBlockedConfigBuilder;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FreeAppointmentsSlotsGeneratorTest {

    @Test
    public void shouldBuildAgenda() {
        FreeAppointmentsSlotsGenerator freeAppointmentsSlotsGenerator = new FreeAppointmentsSlotsGenerator();
        //GIVEN A BLOCKED DAYS AND TIMES CONFIGURATION
        ProfessionalBlockTimeConfig professionalBlockTimeConfig = new TimeBlockedConfigBuilder(new Professional())
                .blockAllDays(LocalTime.of(00,00), LocalTime.of(10,0))
                .blockAllDays(LocalTime.of(18,0), LocalTime.of(23,59))
                .blockAllDays(LocalTime.of(12,0), LocalTime.of(13,00))
                .blockSunday()
                .build();

        //GIVEN A TIME PERIOD OF AGENDA (sunday-blocked, monday-free (10h-18))
        LocalDateTime sunday = LocalDateTime.of(2021, 9, 19, 00, 00);
        LocalDateTime tuesday = LocalDateTime.of(2021, 9, 21, 00, 00);
        //GIVEN A PERIOD OF TIME OF 60 min
        int durationInMinutes = 60;

        //WHEN ASKED TO CREATE AGENDA
        FreeAppointmentsSlots agendaOfPeriod =
                freeAppointmentsSlotsGenerator.generate(
                        durationInMinutes,
                        sunday, tuesday,
                        professionalBlockTimeConfig.getPeriodsOfTimesBlocked(), professionalBlockTimeConfig.getWholeDaysOfWeekBlocked()
                );

        //THEN the agenda should be fine
        List<PeriodSlot> periodSlots = agendaOfPeriod.getPeriodSlots();

        //THEN SHOULD HAVE CREATE  slots
        assertThat(periodSlots.size()).isEqualTo(7);
        LocalDateTime currentDateTimeVerification =  LocalDateTime.of(2021, 9, 20, 10, 00);;
        for (PeriodSlot periodSlot : periodSlots) {
            LocalDate date = periodSlot.getDate();
            LocalTime startTime = periodSlot.getStartTime();
            LocalTime endTime = periodSlot.getEndTime();
            //SPIKING 12h-13 lunch time
            if (!startTime.equals(LocalTime.of(12,0))) {
                currentDateTimeVerification = currentDateTimeVerification.plusMinutes(durationInMinutes);
                continue;
            }
            assertThat(date).isEqualTo(currentDateTimeVerification.toLocalDate());
            assertThat(startTime).isEqualTo(currentDateTimeVerification.toLocalTime());

            currentDateTimeVerification = currentDateTimeVerification.plusMinutes(durationInMinutes);
            assertThat(endTime).isEqualTo(currentDateTimeVerification.toLocalTime());
        }
    }

    @Test
    public void shouldBuildAgendaWithLunchBlocked() {
        FreeAppointmentsSlotsGenerator freeAppointmentsSlotsGenerator = new FreeAppointmentsSlotsGenerator();
        //GIVEN A BLOCKED DAYS AND TIMES CONFIGURATION
        ProfessionalBlockTimeConfig professionalBlockTimeConfig = new TimeBlockedConfigBuilder(new Professional())
                .blockAllDays(LocalTime.of(18,0), LocalTime.of(23,59))
                .blockAllDays(LocalTime.of(00,00), LocalTime.of(10,0))
                .blockAllDays(LocalTime.of(12,00), LocalTime.of(13,0))
                .blockSunday()
                .build();

        //GIVEN A TIME PERIOD OF AGENDA (sunday-blocked, monday-free (10h-18))
        LocalDateTime mondayStart = LocalDateTime.of(2021, 9, 20, 00, 00);
        LocalDateTime tuesdayStart = LocalDateTime.of(2021, 9, 21, 00, 00);
        //GIVEN A PERIOD OF TIME OF 60 min
        int durationInMinutes = 60;

        //WHEN ASKED TO CREATE AGENDA
        FreeAppointmentsSlots agendaOfPeriod =
                freeAppointmentsSlotsGenerator.generate (
                        durationInMinutes, mondayStart,
                        tuesdayStart,
                        professionalBlockTimeConfig.getPeriodsOfTimesBlocked(),
                        professionalBlockTimeConfig.getWholeDaysOfWeekBlocked()
                );

        //THEN the agenda should be fine
        List<PeriodSlot> periodSlots = agendaOfPeriod.getPeriodSlots();

        //THEN SHOULD HAVE CREATE 8 slots
        assertThat(periodSlots.size()).isEqualTo(7);
        LocalDateTime currentDateTimeVerification =  LocalDateTime.of(2021, 9, 20, 10, 00);
        for (PeriodSlot periodSlot : periodSlots) {
            LocalDate date = periodSlot.getDate();
            LocalTime startTime = periodSlot.getStartTime();
            LocalTime endTime = periodSlot.getEndTime();
            //AVOID lunch time
            if (currentDateTimeVerification.toLocalTime().equals(LocalTime.of(12, 0))) {
                currentDateTimeVerification = currentDateTimeVerification.plusMinutes(durationInMinutes);
            }
            assertThat(date).isEqualTo(currentDateTimeVerification.toLocalDate());
            assertThat(startTime).isEqualTo(currentDateTimeVerification.toLocalTime());

            currentDateTimeVerification = currentDateTimeVerification.plusMinutes(durationInMinutes);
            assertThat(endTime).isEqualTo(currentDateTimeVerification.toLocalTime());
        }
    }



    @Test
    public void shouldBuildWithMultipleDays() {
        FreeAppointmentsSlotsGenerator freeAppointmentsSlotsGenerator = new FreeAppointmentsSlotsGenerator();
        //GIVEN A BLOCKED DAYS AND TIMES CONFIGURATION
        ProfessionalBlockTimeConfig professionalBlockTimeConfig = new TimeBlockedConfigBuilder(new Professional())
                .blockAllDays(LocalTime.of(18,0), LocalTime.of(23,59))
                .blockAllDays(LocalTime.of(00,00), LocalTime.of(10,0))
                .blockAllDays(LocalTime.of(12,00), LocalTime.of(13,0))
                .blockSunday()
                .build();

        //GIVEN A TIME PERIOD OF AGENDA (sunday-blocked, monday-free (10h-18))
        LocalDateTime mondayStart = LocalDateTime.of(2021, 9, 20, 00, 00);
        LocalDateTime tuesdayStart = LocalDateTime.of(2021, 9, 21, 00, 00);
        //GIVEN A PERIOD OF TIME OF 60 min
        int durationInMinutes = 60;

        //WHEN ASKED TO CREATE AGENDA
        FreeAppointmentsSlots agendaOfPeriod =
                freeAppointmentsSlotsGenerator.generate (
                        durationInMinutes, mondayStart,
                        tuesdayStart,
                        professionalBlockTimeConfig.getPeriodsOfTimesBlocked(),
                        professionalBlockTimeConfig.getWholeDaysOfWeekBlocked()
                );

        //THEN the agenda should be fine
        List<PeriodSlot> periodSlots = agendaOfPeriod.getPeriodSlots();

        //THEN SHOULD HAVE CREATE 7 slots
        assertThat(periodSlots.size()).isEqualTo(7);
        LocalDateTime currentDateTimeVerification =  LocalDateTime.of(2021, 9, 20, 10, 00);
        for (PeriodSlot periodSlot : periodSlots) {
            LocalDate date = periodSlot.getDate();
            LocalTime startTime = periodSlot.getStartTime();
            LocalTime endTime = periodSlot.getEndTime();
            //AVOID lunch time
            if (currentDateTimeVerification.toLocalTime().equals(LocalTime.of(12, 0))) {
                currentDateTimeVerification = currentDateTimeVerification.plusMinutes(durationInMinutes);
            }
            assertThat(date).isEqualTo(currentDateTimeVerification.toLocalDate());
            assertThat(startTime).isEqualTo(currentDateTimeVerification.toLocalTime());

            currentDateTimeVerification = currentDateTimeVerification.plusMinutes(durationInMinutes);
            assertThat(endTime).isEqualTo(currentDateTimeVerification.toLocalTime());
        }
    }
}
