package dauphine.eu.payement_process.repository;


import dauphine.eu.payement_process.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

