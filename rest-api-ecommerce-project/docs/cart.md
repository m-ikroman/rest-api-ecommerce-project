# Cart API Spec

## Add Item to Cart

Endpoint : POST /api/carts

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "productId" : "random string",
  "quantity" : 2
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "randomstring",
    "productId" : "random string",
    "productName" : "Baju Kaos Polos",
    "price" : 150000,
    "quantity" : 2,
    "subtotal" : 300000
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Product is not found"
}
```

## Update Cart Item

Endpoint : PUT /api/carts/{idCartItem}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "quantity" : 3
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "randomstring",
    "productId" : "random string",
    "productName" : "Baju Kaos Polos",
    "price" : 150000,
    "quantity" : 3,
    "subtotal" : 450000
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Cart item is not found"
}
```

## Get Cart

Endpoint : GET /api/carts

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "items": [
      {
        "id": "randomstring",
        "productId": "random string",
        "productName": "Baju Kaos Polos",
        "price": 150000,
        "quantity": 3,
        "subtotal": 450000
      }
    ],
    "totalPrice": 450000
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Unauthorized"
}
```

## Remove Cart Item

Endpoint : DELETE /api/carts/{idCartItem}

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
  "errors" : "Cart item is not found"
}
```

## Clear Cart

Endpoint : DELETE /api/carts

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
  "errors" : "Unauthorized"
}
```
