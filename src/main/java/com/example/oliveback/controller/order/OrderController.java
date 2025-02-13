package com.example.oliveback.controller.order;

import com.example.oliveback.dto.order.OrderRequest;
import com.example.oliveback.dto.order.OrderResponse;
import com.example.oliveback.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{username}")
    public ResponseEntity<OrderResponse> createOrder(
            @PathVariable String username,
            @Valid @RequestBody OrderRequest request) {
        OrderResponse response = orderService.createOrder(username, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@PathVariable String username) {
        List<OrderResponse> orders = orderService.getUserOrders(username);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{username}/all")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@PathVariable String username) {
        List<OrderResponse> orders = orderService.getAllOrders(username);
        return ResponseEntity.ok(orders);
    }
}
