package com.example.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RocksDBInit {

    @PostConstruct
    public void init() {
        // a static method that loads the RocksDB C++ library.
        RocksDB.loadLibrary();

        // the Options class contains a set of configurable DB options
        // that determines the behaviour of the database.
        try (final Options options = new Options().setCreateIfMissing(true)) {

            // a factory method that returns a RocksDB instance
            try (final RocksDB db = RocksDB.open(options, "data")) {

                // do something
            }
        } catch (RocksDBException e) {
            System.out.println("error: " + e);
        }
        System.out.println("RocksDB initialized");
    }

}
