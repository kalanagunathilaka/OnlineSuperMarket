package com.architects.orderService.controllers;

import com.architects.orderService.dto.request.OrderRequestDTO;
import com.architects.orderService.dto.response.OrderResponse;
import com.architects.orderService.dto.response.OrdersResponse;
import com.architects.orderService.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/v1/orders")

public class OrderController {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/assign-delivery-person-to-order/{orderId}/{deliveryPersonId}")
    public void assignDeliveryPersonToOrder(@PathVariable Long orderId, @PathVariable Long deliveryPersonId) {
        System.out.println(deliveryPersonId);
        System.out.println("Hello");
        orderService.assignDeliveryPersonToOrder(orderId, deliveryPersonId);
    }

    //Place an order
    @PostMapping("/place-order")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse placeOrder(@RequestHeader("customerId") Long customerId) {
        return orderService.placeOrder(customerId);
    }

    @GetMapping("/get-orders")
    @ResponseStatus(HttpStatus.OK)
    public OrdersResponse getOrders(@RequestHeader("customerId") Long customerId){
        return orderService.getAllByCustomerId(customerId);
    }
    @GetMapping("/get-order/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrder(@PathVariable String orderNumber,@RequestHeader("customerId") Long customerId){
        return orderService.getOrder(orderNumber, customerId);
    }


    @DeleteMapping("/cancel-order/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse cancelOrder(@PathVariable String orderNumber, @RequestHeader("customerId") Long customerId){
        return orderService.cancelOrder(orderNumber, customerId);
    }

}
