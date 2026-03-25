package com.tss.shop.service;

import com.tss.shop.dto.request.CustomerRequest;
import com.tss.shop.dto.response.CustomerResponse;
import java.util.List;

public interface CustomerService {

    CustomerResponse create(CustomerRequest request);

    CustomerResponse getById(Long id);

    List<CustomerResponse> getAll();

    CustomerResponse update(Long id, CustomerRequest request);

    void delete(Long id);
}
