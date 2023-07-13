package com.mtech.orderservice.service;

import com.mtech.orderservice.dto.OrderLineItemDto;
import com.mtech.orderservice.dto.OrderRequest;
import com.mtech.orderservice.model.Order;
import com.mtech.orderservice.model.OrderLineItem;
import com.mtech.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItemList = orderRequest.getOrderLineItems().stream().map(this::mapToDto).toList();
        order.setOrderLineItems(orderLineItemList);

        orderRepository.save(order);
    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());

        return orderLineItem;
    }
}
