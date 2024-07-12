package dauphine.eu.payement_process.controller;


import dauphine.eu.payement_process.model.Payment;
import dauphine.eu.payement_process.repository.PaymentRepository;
import dauphine.eu.payement_process.service.PayementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payements")
public class PaymentController {

    @Autowired
    private PayementService paymentService;

    @PostMapping
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
        Payment processedPayment = paymentService.processPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(processedPayment);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Payment> getPaymentStatus(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentStatus(id);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getUserPaymentHistory(@PathVariable Long userId) {
        List<Payment> paymentHistory = paymentService.getUserPaymentHistory(userId);
        return ResponseEntity.ok(paymentHistory);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Payment> cancelPayment(@PathVariable Long id) {
        Payment canceledPayment = paymentService.cancelPayment(id);
        return ResponseEntity.ok(canceledPayment);
    }
}
