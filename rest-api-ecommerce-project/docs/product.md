# Product API Spec

## Create

Endpoint: POST /api/products

Request Header:

- X-API-TOKEN: Token (Mandatory)

Request Body:

```json
{
  "name": "Baju Kaos Polos",
  "categoryId": "random string",
  "price": 150000,
  "stock": 100,
  "description": "Kaos katun combed 30s, tersedia berbagai ukuran"
}
```

Response Body (Success):

```json
{
  "data": {
    "id": "random string",
    "name": "Baju Kaos Polos",
    "categoryId": "random string",
    "price": 150000,
    "stock": 100,
    "description": "Kaos katun combed 30s, tersedia berbagai ukuran"
  }
}
```

Response Body (Failed):

```json
{
  "errors": "Failed Create Product"
}
```

## Update

Endpoint: PUT /api/products/{idProduct}

Request Header:

- X-API-TOKEN: Token (Mandatory)

Request Body:

```json
{
  "name": "Baju Kaos Polos Premium",
  "categoryId": "random string",
  "price": 175000,
  "stock": 80,
  "description": "Kaos katun combed 30s premium"
}
```

Response Body (Success):

```json
{
  "data": {
    "id": "random string",
    "name": "Baju Kaos Polos Premium",
    "categoryId": "random string",
    "price": 175000,
    "stock": 80,
    "description": "Kaos katun combed 30s premium"
  }
}
```

Response Body (Failed):

```json
{
  "errors": "Product is not found"
}
```

## Get

Endpoint: GET /api/products/{idProduct}

Request Header:

- X-API-TOKEN: Token (Mandatory)

Response Body (Success):

```json
{
  "data": {
    "id": "random string",
    "name": "Baju Kaos Polos",
    "categoryId": "random string",
    "price": 150000,
    "stock": 100,
    "description": "Kaos katun combed 30s, tersedia berbagai ukuran"
  }
}
```

Response Body (Failed, 404):

```json
{
  "errors": "Product is not found"
}
```

## Search

Endpoint: GET /api/products

Query Param:
- name: String, product name (Optional)
- categoryId: String, category id (Optional)
- minPrice: Integer, minimum price (Optional)
- maxPrice: Integer, maximum price (Optional)
- page: Integer, start from 0, default 0
- size: Integer, default 10

Request Header:

- X-API-TOKEN: Token (Mandatory)

Response Body (Success):

```json
{
  "data": [
    {
      "id": "random string",
      "name": "Baju Kaos Polos",
      "categoryId": "random string",
      "price": 150000,
      "stock": 100,
      "description": "Kaos katun combed 30s, tersedia berbagai ukuran"
    }
  ],
  "paging": {
    "currentPage": 0,
    "totalPage": 10,
    "size": 10
  }
}
```

Response Body (Failed):

```json
{
  "errors": "product is not found"
}
```

## Remove

Endpoint: DELETE /api/products/{idProduct}

Request Header:

- X-API-TOKEN: Token (Mandatory)

Response Body (Success):

```json
{
  "data": "OK"
}
```

Response Body (Failed):

```json
{
  "errors": "product is not found"
}
```
