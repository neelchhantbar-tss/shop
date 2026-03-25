package com.tss.shop.controller;

import com.tss.shop.dto.request.ItemRequest;
import com.tss.shop.dto.response.ItemResponse;
import com.tss.shop.service.ItemService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemResponse> create(@RequestBody ItemRequest request) {
        ItemResponse response = itemService.create(request);
        return ResponseEntity
                .created(URI.create("/api/items/" + response.getId()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ItemResponse getById(@PathVariable Long id) {
        return itemService.getById(id);
    }

    @GetMapping
    public List<ItemResponse> getAll() {
        return itemService.getAll();
    }

    @PutMapping("/{id}")
    public ItemResponse update(@PathVariable Long id, @RequestBody ItemRequest request) {
        return itemService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
