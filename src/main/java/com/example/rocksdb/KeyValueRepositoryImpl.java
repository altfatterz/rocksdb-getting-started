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
    public void init() throws RocksDBException {
        RocksDB.loadLibrary();
        db = RocksDB.open("data");
    }


    @Override
    public void save(String key, String value) {
        try {
            db.put(key.getBytes(), value.getBytes());
        } catch (RocksDBException e) {
            logger.error("could not store value: {} with key: {} into RocksDB", value, key);
        }

    }

    @Override
    public String find(String key) {
        byte[] result = null;
        try {
            result = db.get(key.getBytes());
        } catch (RocksDBException e) {
            logger.error("could not retrieve value with key: {}, reason: {}", key, e.getMessage());
        }
        return (result != null) ? new String(result) : null;
    }

    @Override
    public void delete(String key) {
        try {
            db.delete(key.getBytes());
        } catch (RocksDBException e) {
            logger.error("could not delete the value with key: {}, reason: {}", key, e.getMessage());
        }
    }
}
