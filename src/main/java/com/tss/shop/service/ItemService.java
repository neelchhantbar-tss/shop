package com.tss.shop.service;

import com.tss.shop.dto.request.ItemRequest;
import com.tss.shop.dto.response.ItemResponse;
import java.util.List;

public interface ItemService {

    ItemResponse create(ItemRequest request);

    ItemResponse getById(Long id);

    List<ItemResponse> getAll();

    ItemResponse update(Long id, ItemRequest request);

    void delete(Long id);
}
