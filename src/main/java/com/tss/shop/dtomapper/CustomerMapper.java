package com.tss.shop.dtomapper;

import com.tss.shop.dto.request.CustomerRequest;
import com.tss.shop.dto.response.CustomerResponse;
import com.tss.shop.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequest request);

    CustomerResponse toResponse(Customer customer);

    void updateEntity(CustomerRequest request, @MappingTarget Customer customer);
}
