package com.diva.basics;

import java.util.concurrent.atomic.AtomicInteger;

public class ProxyDemo {

    public static void main(String[] args) {
        OrderService orderService = new OrderServiceProxy(new OrderServiceImpl());
        orderService.processOrder();
    }

}

class OrderServiceProxy implements OrderService {
    private final OrderServiceImpl orderService;

    public OrderServiceProxy(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Override
    public void processOrder() {
        System.out.println("Before place order (log/security/transaction)");
        orderService.processOrder();
        System.out.println("After place order (commit/lot)");
    }
    
}

class OrderServiceImpl implements OrderService {

    @Override
    public void processOrder() {
        System.out.println("Processing order .....");
    }

} 

interface OrderService {
    void processOrder();
}