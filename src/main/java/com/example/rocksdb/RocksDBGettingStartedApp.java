package com.example.rocksdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocksDBGettingStartedApp implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(RocksDBGettingStartedApp.class);

    private final KeyValueRepository repository;

    public RocksDBGettingStartedApp(KeyValueRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RocksDBGettingStartedApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String key = "foo";
        String value = "bar";

        logger.info("Saving value: `{}` with key: `{}` into RocksDB", value, key);
        repository.save("foo", "bar");

        Object result = repository.find(key);
        logger.info("Retrieving value: `{}` with key: `{}` from RocksDB", result, key);

        logger.info("Deleted value with key: `{}`", key);
        repository.delete("foo");

        result = repository.find(key);
        logger.info("Retrieving value: `{}` with key: `{}` from RocksDB", result, key);
    }
}
