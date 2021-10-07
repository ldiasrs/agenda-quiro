package br.com.agendaquiro.domain.appointment;

import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.AppointmentRepository;
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
public class PerformedAppointment {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   @EqualsAndHashCode.Include
   private Long id;

   @OneToOne
   private Appointment appointment;
   private String painComplaint;
   private String performedProcedures;
   private String postServiceProcedures;
}
