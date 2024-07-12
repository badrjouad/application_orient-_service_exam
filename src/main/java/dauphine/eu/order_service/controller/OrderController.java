package dauphine.eu.order_service.controller;


import dauphine.eu.order_service.feign.PaymentClient;
import dauphine.eu.order_service.model.Order;
import dauphine.eu.order_service.model.Payment;
import dauphine.eu.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentClient paymentClient;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        order.setOrderStatus("PENDING");
        Order savedOrder = orderRepository.save(order);

        Payment payment = new Payment();
        payment.setOrderId(savedOrder.getId());
        payment.setAmount(savedOrder.getTotalAmount());
        Payment processedPayment = paymentClient.processPayment(payment);

        if ("SUCCESS".equals(processedPayment.getPaymentStatus())) {
            savedOrder.setOrderStatus("CONFIRMED");
        } else {
            savedOrder.setOrderStatus("FAILED");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(orderRepository.save(savedOrder));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        if ("PENDING".equals(order.getOrderStatus())) {
            order.setOrderStatus("CANCELLED");
            paymentClient.cancelPayment(order.getId());
            return ResponseEntity.ok(orderRepository.save(order));
        } else {
            throw new RuntimeException("Seules les commandes en attente peuvent être annulées");
        }
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        order.setOrderStatus(status);
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @GetMapping("/history/{customerId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }
}

