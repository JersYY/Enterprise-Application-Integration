# Product API

Backend Spring Boot untuk Tugas 3 Fullstack Enterprise Application Integration B.

## Fitur

- `GET /api/products` untuk mengambil daftar produk
- `POST /api/products` untuk menambahkan produk baru
- Validasi input request
- Database H2 in-memory
- Test controller, service, repository, dan model

## Menjalankan Project

```bash
./mvnw spring-boot:run
```

API berjalan di `http://localhost:8080/api/products`.

## Menjalankan Test

```bash
./mvnw test
```
