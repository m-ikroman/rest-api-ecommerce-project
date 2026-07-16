# Customer API Spec

## Register Customer

Endpoint: POST /api/customers

Request Body:

```json
{
  "username":"johndoe",
  "password":"secret123",
  "fullName":"John Doe"
}
```

Response (Success):

```json
{
  "data":"OK"
}
```

Response (Failed):

```json
{
  "errors":"Invalid data"
}
```

## Login

Endpoint: POST /api/customers/login

Request Body:

```json
{
  "username":"johndoe",
  "password":"secret123"
}
```

Response (Success):

```json
{
  "data":{
    "token":"TOKEN",
    "expiredAt":1234567890
  }
}
```

## Current Customer

Endpoint: GET /api/customers/current

Request Header:

- X-API-TOKEN: Token (Mandatory)

Response:

```json
{
  "data":{
    "username":"johndoe",
    "fullName":"John Doe"
  }
}
```
