# Order API Spec

## Checkout / Create Order

Endpoint : POST /api/orders

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "addressId" : "random string",
  "paymentMethod" : "BANK_TRANSFER"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "randomstring",
    "status" : "WAITING_PAYMENT",
    "paymentMethod" : "BANK_TRANSFER",
    "items" : [
      {
        "productId" : "random string",
        "productName" : "Baju Kaos Polos",
        "price" : 150000,
        "quantity" : 3,
        "subtotal" : 450000
      }
    ],
    "totalPrice" : 450000,
    "createdAt" : 1721030400
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Cart is empty"
}
```

## Get Order

Endpoint : GET /api/orders/{idOrder}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "id" : "randomstring",
    "status" : "WAITING_PAYMENT",
    "paymentMethod" : "BANK_TRANSFER",
    "items" : [
      {
        "productId" : "random string",
        "productName" : "Baju Kaos Polos",
        "price" : 150000,
        "quantity" : 3,
        "subtotal" : 450000
      }
    ],
    "totalPrice" : 450000,
    "createdAt" : 1721030400
  }
}
```

Response Body (Failed, 404) :

```json
{
  "errors" : "Order is not found"
}
```

## Search Order

Endpoint : GET /api/orders

Query Param :
- status: String, order status e.g. WAITING_PAYMENT, PROCESSED, SHIPPED, COMPLETED, CANCELLED (Optional)
- page: Integer, start from 0, default 0
- size: Integer, default 10

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": [
    {
      "id": "randomstring",
      "status": "WAITING_PAYMENT",
      "totalPrice": 450000,
      "createdAt": 1721030400
    }
  ],
  "paging": {
    "currentPage": 0,
    "totalPage": 5,
    "size": 10
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "order is not found"
}
```

## Update Order Status

Endpoint : PATCH /api/orders/{idOrder}/status

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "status" : "SHIPPED"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "randomstring",
    "status" : "SHIPPED"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Order is not found"
}
```

## Cancel Order

Endpoint : DELETE /api/orders/{idOrder}

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
  "errors" : "Order is not found or cannot be cancelled"
}
```
