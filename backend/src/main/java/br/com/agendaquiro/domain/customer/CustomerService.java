package br.com.agendaquiro.domain.customer;

import br.com.agendaquiro.domain.appointment.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CustomerService {
   private Appointment appointment;
   private String painComplaint;
   private String performedProcedures;
   private String postServiceProcedures;
}
