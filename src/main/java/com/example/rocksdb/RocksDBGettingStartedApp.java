package com.example.rocksdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocksDBGettingStartedApp implements CommandLineRunner {

	@Autowired
	private KeyValueRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(RocksDBGettingStartedApp.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		repository.save("foo", "bar");
		System.out.println("key=foo, value=" + repository.find("foo") + "\"");
	}
}
