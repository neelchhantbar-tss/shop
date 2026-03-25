package com.tss.shop.dtomapper;

import com.tss.shop.dto.response.OrderResponse;
import com.tss.shop.entity.CustomerOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ItemMapper.class)
public interface OrderMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.name", target = "customerName")
    OrderResponse toResponse(CustomerOrder order);
}
