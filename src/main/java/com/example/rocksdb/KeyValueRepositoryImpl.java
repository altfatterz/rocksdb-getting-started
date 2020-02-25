package com.example.rocksdb;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class KeyValueRepositoryImpl implements KeyValueRepository<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(KeyValueRepository.class);

    private RocksDB db;

    @PostConstruct
    public void init() {
        // a static method that loads the RocksDB C++ library.
         RocksDB.loadLibrary();

        // a factory method that returns a RocksDB instance
        try (final RocksDB db = RocksDB.open("data")) {
            this.db = db;
        } catch (RocksDBException e) {
            System.out.println("error: " + e);
        }
        System.out.println("RocksDB initialized");
    }


    @Override
    public void save(String key, String value) {
        try {
            db.put(key.getBytes(), value.getBytes());
        } catch (RocksDBException e) {
            logger.error("could not store value {} with key {} into RocksDB", value, key);
        }

    }

    @Override
    public String find(String key) {
        try {
            return new String(db.get(key.getBytes()));
        } catch (RocksDBException e) {
            logger.error("could not retrieve with key{}", key);
        }
        return null;
    }

    @Override
    public void delete(String key) {

    }
}
