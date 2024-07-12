package dauphine.eu.order_service.feign;

import dauphine.eu.order_service.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "paiement")
public interface PaymentClient {

    @PostMapping("/payments")
    Payment processPayment(@RequestBody Payment payment);

    @GetMapping("/payments/status/{id}")
    Payment getPaymentStatus(@PathVariable("id") Long id);

    @PutMapping("/payments/cancel/{orderId}")
    void cancelPayment(@PathVariable("orderId") Long orderId);
}

