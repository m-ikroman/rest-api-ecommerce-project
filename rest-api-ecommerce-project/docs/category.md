# Category API Spec

## Create Category

Endpoint : POST /api/categories

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name" : "Elektronik"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "randomstring",
    "name" : "Elektronik"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Category already exists"
}
```

## Update Category

Endpoint : PUT /api/categories/{idCategory}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name" : "Elektronik & Gadget"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "randomstring",
    "name" : "Elektronik & Gadget"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Category is not found"
}
```

## Get Category

Endpoint : GET /api/categories/{idCategory}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "id" : "randomstring",
    "name" : "Elektronik"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Category is not found"
}
```

## Remove Category

Endpoint : DELETE /api/categories/{idCategory}

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
  "errors" : "Category is not found"
}
```

## List Category

Endpoint : GET /api/categories

Query Param :

- name: String, category name (Optional)
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
      "name": "Elektronik"
    }
  ],
  "paging": {
    "currentPage": 0,
    "totalPage": 3,
    "size": 10
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Unauthorized"
}
```
