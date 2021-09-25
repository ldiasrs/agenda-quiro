package br.com.agendaquiro.domain.customer;

import br.com.agendaquiro.domain.appointment.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer_service")
public class CustomerService {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   @EqualsAndHashCode.Include
   private Long id;

   private Long appointmentId;
   private String painComplaint;
   private String performedProcedures;
   private String postServiceProcedures;
}
