package com.tss.shop.service;

import com.tss.shop.dto.request.CustomerRequest;
import com.tss.shop.dto.response.CustomerResponse;
import com.tss.shop.dtomapper.CustomerMapper;
import com.tss.shop.entity.Customer;
import com.tss.shop.exception.CustomerNotFoundException;
import com.tss.shop.repository.CustomerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse create(CustomerRequest request) {
        Customer customer = customerMapper.toEntity(request);
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    @Override
    public CustomerResponse getById(Long id) {
        return customerMapper.toResponse(findByIdOrThrow(id));
    }

    @Override
    public List<CustomerResponse> getAll() {
        return customerRepository.findAll().stream().map(customerMapper::toResponse).toList();
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer existing = findByIdOrThrow(id);
        customerMapper.updateEntity(request, existing);
        return customerMapper.toResponse(customerRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(findByIdOrThrow(id));
    }

    private Customer findByIdOrThrow(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
