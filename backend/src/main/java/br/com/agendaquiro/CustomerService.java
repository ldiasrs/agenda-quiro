package br.com.agendaquiro;

import br.com.agendaquiro.domain.professsional.Professional;
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
