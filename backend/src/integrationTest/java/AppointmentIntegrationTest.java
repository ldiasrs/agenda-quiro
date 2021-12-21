import br.com.agendaquiro.controller.calendar.CalendarResponse;
import br.com.agendaquiro.controller.calendar.PeriodSlotResponse;
import br.com.agendaquiro.domain.appointment.AppointmentCreateRequest;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class AppointmentIntegrationTest {

    private static final IntegrationTestClient clientAppointment =
            new IntegrationTestClient("appointments", "appointments");

    @Test
    public void createTest()  {
        RestTemplate restTemplate = new RestTemplate();
        CalendarResponse response = restTemplate.getForObject(
                IntegrationTestClient.URL+"/calendar/user/1", CalendarResponse.class);
        PeriodSlotResponse slot = response.getPeriodSlots().get(0);
        AppointmentCreateRequest appointment = AppointmentCreateRequest.builder()
                .amountPaid(BigDecimal.ZERO)
                .date(slot.getDate())
                .startTime(slot.getStartTime())
                .endTime(slot.getEndTime())
                .customerId(1L)
                .professionalServiceId(1L)
                .observation("Integration Test")
                .build();
        clientAppointment.create(appointment);
    }

}
