package dauphine.eu.payement_process.controller;


import dauphine.eu.payement_process.model.Payment;
import dauphine.eu.payement_process.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping
    public Payment processPayment(@RequestBody Payment payment) {
        payment.setPaymentStatus("SUCCESS");
        return paymentRepository.save(payment);
    }

    @GetMapping("/status/{id}")
    public Payment getPaymentStatus(@PathVariable Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Paiement non trouvé"));
    }
}

