# Review API Spec

## Create Review

Endpoint : POST /api/products/{idProduct}/reviews

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "rating" : 5,
  "comment" : "Bagus dan nyaman dipakai"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "randomstring",
    "username" : "ikroman",
    "rating" : 5,
    "comment" : "Bagus dan nyaman dipakai"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Product is not found"
}
```

## Update Review

Endpoint : PUT /api/products/{idProduct}/reviews/{idReview}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "rating" : 4,
  "comment" : "Bahan bagus, ukuran agak kebesaran"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "randomstring",
    "username" : "ikroman",
    "rating" : 4,
    "comment" : "Bahan bagus, ukuran agak kebesaran"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Review is not found"
}
```

## Get Review

Endpoint : GET /api/products/{idProduct}/reviews/{idReview}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "id" : "randomstring",
    "username" : "ikroman",
    "rating" : 5,
    "comment" : "Bagus dan nyaman dipakai"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Review is not found"
}
```

## Remove Review

Endpoint : DELETE /api/products/{idProduct}/reviews/{idReview}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Review is not found"
}
```

## List Review

Endpoint : GET /api/products/{idProduct}/reviews

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": [
    {
      "id": "randomstring",
      "username": "ikroman",
      "rating": 5,
      "comment": "Bagus dan nyaman dipakai"
    }
  ]
}
```

Response Body (Failed) :

```json
{
  "errors" : "Product is not found"
}
```
