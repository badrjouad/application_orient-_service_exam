package dauphine.eu.order_service.controller;


import dauphine.eu.order_service.feign.PaymentClient;
import dauphine.eu.order_service.model.Order;
import dauphine.eu.order_service.model.Payment;
import dauphine.eu.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Order placeOrder(@RequestBody Order order) {
        order.setOrderStatus("PENDING");
        Order savedOrder = orderRepository.save(order);

        // Process payment using PaymentClient
        Payment payment = new Payment();
        payment.setOrderId(savedOrder.getId());
        payment.setAmount(savedOrder.getTotalAmount());
        Payment processedPayment = paymentClient.processPayment(payment);

        if ("SUCCESS".equals(processedPayment.getPaymentStatus())) {
            savedOrder.setOrderStatus("CONFIRMED");
        } else {
            savedOrder.setOrderStatus("FAILED");
        }

        return orderRepository.save(savedOrder);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande non trouvé"));
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomerId(@PathVariable Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
