package dauphine.eu.payement_process.service;

import dauphine.eu.payement_process.model.Payment;
import dauphine.eu.payement_process.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayementService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(Payment payment) {
        boolean isFraudulent = checkFraud(payment);
        if (isFraudulent) {
            payment.setPaymentStatus("DECLINED");
            payment.setFraudStatus("Fraudulent");
        } else {
            payment.setPaymentStatus("SUCCESS");
            payment.setFraudStatus("Safe");
        }
        return paymentRepository.save(payment);
    }

    private boolean checkFraud(Payment payment) {

        if (payment.getAmount() > 1000.0) {
            return true;
        }
        if (payment.getCardNumber().startsWith("1234")) {
            return true;
        }

        return false;
    }

    public Payment getPaymentStatus(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé"));
    }

    public List<Payment> getUserPaymentHistory(Long userId) {
        return paymentRepository.findByUserId(userId);
    }

    public Payment cancelPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé"));
        if ("SUCCESS".equals(payment.getPaymentStatus())) {
            payment.setPaymentStatus("CANCELLED");
            return paymentRepository.save(payment);
        } else {
            throw new RuntimeException("Seuls les paiements réussis peuvent être annulés");
        }
    }
}