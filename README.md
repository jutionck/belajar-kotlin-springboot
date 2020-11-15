# Simple Kotlin REST API

![Screenshot](enigma-logo.jpeg)

Start project here : https://start.spring.io/

![Screenshot](create-project.png)

## Project Design
`link: `


## API Spec

### Create Customer
- Request: POST
- Endpoint : `/api/customers`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body : 
```json
{
    "id": "String",
    "firstName": "String",
    "lastName": "String",
    "address": "String"
}
```

Response:
```json
  {
    "code": "Number",
    "Status": "String",
    "Data": {
          "id": "String",
          "firstName": "String",
          "lastName": "String",
          "address": "String",
          "createdAt": "Date",
          "updatedAt": "Date"
    }     
  }
  ```

### Get Customer
- Request: GET
- Endpoint : `/api/customers/{customerId}`
- Header :
    - Content-Type: application/json

Response:
```json
  {
    "code": "Number",
    "Status": "String",
    "Data": {
          "id": "String",
          "firstName": "String",
          "lastName": "String",
          "address": "String",
          "createdAt": "Date",
          "updatedAt": "Date"
    }     
  }
  ```

### Update Customer
- Request: PUT
- Endpoint : `/api/customers/{customerId}`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :
```json
{
    "firstName": "String",
    "lastName": "String",
    "address": "String"
}
```

Response:
```json
  {
    "code": "Number",
    "Status": "String",
    "Data": {
          "id": "String",
          "firstName": "String",
          "lastName": "String",
          "address": "String",
          "createdAt": "Date",
          "updatedAt": "Date"
    }     
  }
  ```

### List Customer
- Request: GET
- Endpoint : `/api/customers`
- Header :
    - Content-Type: application/json
- Query Param :
    - Size : number,
    - Page : number
    
Response:
```json
  {
    "code": "Number",
    "Status": "String"   
  }
  ```


### Delete Customer
- Request: DELETE
- Endpoint : `/api/customers/{customerId}`
- Header :
    - Content-Type: application/json

Response:
```json
  {
    "code": "Number",
    "Status": "String",
    "Data": [
        {
          "id": "String",
          "firstName": "String",
          "lastName": "String",
          "address": "String",
          "createdAt": "Date",
          "updatedAt": "Date"
        } 
    ]    
  }
  ```
