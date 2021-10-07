package br.com.agendaquiro.domain.customer;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
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
