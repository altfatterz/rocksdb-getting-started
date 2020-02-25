package com.example.rocksdb;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{key}")
public class KeyValueRestController {

    private final KeyValueRepository<String, String> repository;

    public KeyValueRestController(KeyValueRepository<String, String> repository) {
        this.repository = repository;
    }

    @GetMapping
    public String find(@PathVariable String key) {
        return repository.find(key);
    }

    @PostMapping
    public void save(@PathVariable String key, @RequestBody String value) {
        repository.save(key, value);
    }

    @DeleteMapping
    public void delete(@PathVariable String key) {
        repository.delete(key);
    }
}
