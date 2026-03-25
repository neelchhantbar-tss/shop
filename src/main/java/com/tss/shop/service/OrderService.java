package com.tss.shop.service;

import com.tss.shop.dto.request.OrderRequest;
import com.tss.shop.dto.response.OrderResponse;
import java.util.List;

public interface OrderService {

    OrderResponse create(OrderRequest request);

    OrderResponse getById(Long id);

    List<OrderResponse> getAll();

    OrderResponse update(Long id, OrderRequest request);

    void delete(Long id);
}
