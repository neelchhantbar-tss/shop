package com.tss.shop.service;

import com.tss.shop.dto.request.ItemRequest;
import com.tss.shop.dto.response.ItemResponse;
import com.tss.shop.dtomapper.ItemMapper;
import com.tss.shop.entity.Item;
import com.tss.shop.exception.DuplicateItemException;
import com.tss.shop.exception.ItemNotFoundException;
import com.tss.shop.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public ItemResponse create(ItemRequest request) {
        validateDuplicateName(null, request.getName());
        Item item = itemMapper.toEntity(request);
        Item saved = itemRepository.save(item);
        return itemMapper.toResponse(saved);
    }

    @Override
    public ItemResponse getById(Long id) {
        Item item = findByIdOrThrow(id);
        return itemMapper.toResponse(item);
    }

    @Override
    public List<ItemResponse> getAll() {
        return itemRepository.findAll()
                .stream()
                .map(itemMapper::toResponse)
                .toList();
    }

    @Override
    public ItemResponse update(Long id, ItemRequest request) {
        Item existing = findByIdOrThrow(id);
        validateDuplicateName(id, request.getName());
        itemMapper.updateEntity(request, existing);
        Item updated = itemRepository.save(existing);
        return itemMapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        Item existing = findByIdOrThrow(id);
        itemRepository.delete(existing);
    }

    private Item findByIdOrThrow(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    private void validateDuplicateName(Long currentItemId, String name) {
        itemRepository.findByNameIgnoreCase(name)
                .ifPresent(found -> {
                    if (currentItemId == null || !found.getId().equals(currentItemId)) {
                        throw new DuplicateItemException(name);
                    }
                });
    }
}
