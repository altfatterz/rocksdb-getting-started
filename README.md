# rocksdb-getting-started

```bash
$ git clone https://github.com/altfatterz/rocksdb-getting-started
$ cd rocksdb-getting-started
$ ./gradlew clean build
```

Endpoints:

### Upsert

```bash
$ echo "bar" | http post :8080/api/foo
```

### Get

```bash
$ http post :8080/api/foo

bar
```

### Delete

```bash
$ http delete :8080/api/foo
```




Resources:

1. https://dev.to/thegroo/simple-rocksdb-with-java-crash-course-20o7
2. https://github.com/facebook/rocksdb/wiki/RocksJava-Basics