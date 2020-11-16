# Simple Kotlin REST API

![Screenshot](enigma-logo.jpeg)

Start project here : https://start.spring.io/

![Screenshot](create-project.png)

## Create Db, this db creates with Docker Compose
1. Create a file `docker-compose.yml`
2. Copy this script :
   ```
   version: '3.5'
   
   services:
     kotlin-restful-api-postgres:
       container_name: "kotlin-restful-api-postgres"
       image: postgres:latest
       ports:
         - 5432:5432
       environment:
         POSTGRES_PASSWORD: kotlin
         POSTGRES_USER: kotlin
         POSTGRES_DB: restful-api

   ```
   For default postgres port is `5432`.
3. Type this for process file `docker-compose.yml` :
   ```
   docker-compose -f docker-compose.yml up -d   
    ```
4. Check :
   ```
    docker container ls
    docker container logs kotlin-restful-api-postgres
    ```
5. Open Database in Intellij:
![Screenshot](open-db-intellij.png)

6. Configuration and if successfully:
![Screenshot](restful_api.png)

7. Setting `application.properties`
   ```
    spring.datasource.data-username=postgres
    spring.datasource.data-password=12345qwe
    spring.datasource.url=jdbc:postgresql://localhost:5432/restful_api
    
    spring.jpa.hibernate.ddl-auto=update

    ``` 

## Structure Project
### Create Customer
1. Create a package `entity` in `com.enigmacamp.kotlin.kotlinresapi`
2. Create a class `Customer` in package `entity`
   ```kotlin
   @Entity
   @Table(name = "m_customer")
   data class Customer (
   
           @Id
           @GeneratedValue(generator = "UUID")
           @GenericGenerator(
           name = "UUID",
                   strategy = "org.hibernate.id.UUIDGenerator",
           )
           @Column(name = "id", updatable = false, nullable = false)
           val id: String,
   
           @Column(name = "first_name")
           val firstName: String,
   
           @Column(name = "last_name")
           val lastName: String,
   
           @Column(name = "address")
           val address: String,
   
           @Column(name = "created_at")
           val createdAt: Date,
   
           @Column(name = "updated_at")
           val updatedAt: Date
   )
    ```
3. Create a package `respository` in `com.enigmacamp.kotlin.kotlinresapi`
4. Create a interface `CustomerRepostory`
   ```kotlin
    interface CustomerRepository: JpaRepository<Customer, String> {}
    ```
5. Create a package `model` in `com.enigmacamp.kotlin.kotlinresapi`
6. Create a class `CreateCustomerRequest`
   ```kotlin
    data class CreateCustomerRequest (
    
            val id: String,
    
            val firstName: String,
    
            val lastName: String,
    
            val address: String,
    )
   ```
7. Create a class `CustomerResponse`
   ```kotlin
    data class CustomerResponse (
    
            val id: String,
    
            val firstName: String,
    
            val lastName: String,
    
            val address: String,
    
            val createdAt: Date,
    
            val updatedAt: Date?
    )
   ```
8. Create a package `service` in `com.enigmacamp.kotlin.kotlinresapi`
9. Create a interface `CustomerService`
   ```kotlin
    interface CustomerService {
    
        fun create(createCustomerRequest: CreateCustomerRequest): CustomerResponse
    }
   ```
10. Create a package `impl` in package `service` and create class `CustomerServiceImpl`
       ```kotlin
        @Service
        class CustomerServiceImpl(val customerRepository: CustomerRepository): CustomerService {
        
            override fun create(createCustomerRequest: CreateCustomerRequest): CustomerResponse {
        
                val customer = Customer (
                        id = createCustomerRequest.id,
                        firstName = createCustomerRequest.firstName,
                        lastName = createCustomerRequest.lastName,
                        address = createCustomerRequest.address,
                        createdAt = Date(),
                        updatedAt = null
                )
        
                customerRepository.save(customer);
        
                return CustomerResponse(
                        id = customer.id,
                        firstName = customer.firstName,
                        lastName = customer.lastName,
                        address = customer.address,
                        createdAt = customer.createdAt,
                        updatedAt = customer.updatedAt
                )
            }
        }
       ```
11. Create a package `controller` in package `com.enigmacamp.kotlin.kotlinresapi` and create class `CustomerController`
       ```kotlin
        @RestController
        @RequestMapping(value = ["/api/customers"])
        class CustomerController(val customerService: CustomerService) {
        
            @PostMapping(
                    value = ["/"],
                    produces = ["application/json"],
                    consumes = ["application/json"]
            )
            fun createCustomer(@RequestBody body: CreateCustomerRequest): WebResponse<CustomerResponse> {
                val customerResponse =  customerService.create(body)
                return WebResponse(
                        code = 200,
                        status = "OK",
                        data = customerResponse
                )
            }
        }

       ```    
12. Open main file for running project
    
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
