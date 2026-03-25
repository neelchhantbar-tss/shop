package com.tss.shop.service;

import com.tss.shop.dto.request.OrderRequest;
import com.tss.shop.dto.response.OrderResponse;
import com.tss.shop.dtomapper.OrderMapper;
import com.tss.shop.entity.Customer;
import com.tss.shop.entity.CustomerOrder;
import com.tss.shop.entity.Item;
import com.tss.shop.exception.CustomerNotFoundException;
import com.tss.shop.exception.ItemNotFoundException;
import com.tss.shop.exception.OrderNotFoundException;
import com.tss.shop.repository.CustomerRepository;
import com.tss.shop.repository.ItemRepository;
import com.tss.shop.repository.OrderRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse create(OrderRequest request) {
        Customer customer = findCustomerOrThrow(request.getCustomerId());
        Set<Item> items = findItemsOrThrow(request.getItemIds());

        CustomerOrder order = new CustomerOrder();
        order.setCustomer(customer);
        order.setItems(items);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse getById(Long id) {
        return orderMapper.toResponse(findOrderOrThrow(id));
    }

    @Override
    public List<OrderResponse> getAll() {
        return orderRepository.findAll().stream().map(orderMapper::toResponse).toList();
    }

    @Override
    public OrderResponse update(Long id, OrderRequest request) {
        CustomerOrder existing = findOrderOrThrow(id);
        existing.setCustomer(findCustomerOrThrow(request.getCustomerId()));
        existing.setItems(findItemsOrThrow(request.getItemIds()));
        return orderMapper.toResponse(orderRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(findOrderOrThrow(id));
    }

    private CustomerOrder findOrderOrThrow(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    private Customer findCustomerOrThrow(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    private Set<Item> findItemsOrThrow(Set<Long> itemIds) {
        Set<Long> ids = itemIds == null ? Set.of() : itemIds;
        Set<Item> items = new HashSet<>();
        for (Long itemId : ids) {
            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() -> new ItemNotFoundException(itemId));
            items.add(item);
        }
        return items;
    }
}
