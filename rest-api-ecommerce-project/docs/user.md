# User API Spec

## Register

Endpoint: POST /api/users

Request Body:

```json
{
  "username": "ikroman",
  "password": "rahasia",
  "name": "Muhamad Ikroman",
  "email": "ikroman9a@gmail.com",
  "phone": "0808080080"
}
```

Response Body (Success):

```json
{
  "data": "OK"
}
```

Response Body (Failed):

```json
{
  "errors": "Invalid data"
}
```

## Login

Endpoint: POST /api/auth/login

Request Body:

```json
{
  "username": "ikroman",
  "password": "rahasia"
}
```

Response Body (Success):

```json
{
  "data": {
    "token": "TOKEN",
    "expiredAt": 21232
  }
}
```

Response Body (Failed, 401):

```json
{
  "errors": "Login failed, Invalid data"
}
```

## GET

Endpoint: GET /api/users/current

Request Header:

- X-API-TOKEN : Token (Mandatory)

Response Body (Success):

```json
{
  "data": {
    "username": "ikroman",
    "name": "Muhamad Ikroman",
    "email": "ikroman9a@gmail.com",
    "phone": "0808080080"
  }
}
```

Response Body (Failed, 401):

```json
{
  "errors": "Unauthorized"
}
```

## UPDATE

Endpoint: PATCH /api/users/current

Request Header:

- X-API-TOKEN : Token (Mandatory)

Request Body:

```json
{
  "name": "update name",
  "email": "update email",
  "phone": "update phone",
  "password": "update password"
}
```

Response Body (Success) :

```json
{
  "data": {
    "username": "ikroman",
    "name": "updated name",
    "email": "updated email",
    "phone": "updated phone"
  }
}
```

Response Body (Failed) :

```json
{
  "errors": "Invalid data"
}
```

## Logout

Endpoint: DELETE /api/auth/logout

Request Header:

- X-API-TOKEN : Token (Mandatory)

Response Body (Success):

```json
{
  "data": "OK"
}
```
