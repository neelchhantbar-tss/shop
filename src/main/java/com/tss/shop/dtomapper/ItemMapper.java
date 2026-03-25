package com.tss.shop.dtomapper;

import com.tss.shop.dto.request.ItemRequest;
import com.tss.shop.dto.response.ItemResponse;
import com.tss.shop.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(ItemRequest request);

    ItemResponse toResponse(Item item);

    void updateEntity(ItemRequest request, @MappingTarget Item item);
}
