# API graphql with spring boot java

## Api simulates a shopping cart, with the options to list, register, update and delete items from the cart

### Technologies used

- Java: 17+
- Spring boot: 3.1.5
- Spring boot graphql: latest
- Graphql datetime spring boot: 6.0.0
- lombok
- database h2
- flywaydb
- modelmapper: 2.3.0

To test requests via graphql, simply access: `http://localhost:8080/graphiql?path=/graphql`

### Example:

Request:
  
```
query{
  purchases(page: 0, size: 1){
    id
    quantity
    status
    date
    customer{
      id
      name
      email
    }
    product{
      id
      name
      amount
    }
  }
}
```

  
Response:

```
{
  "data": {
    "purchases": [
      {
        "id": "5",
        "quantity": 50,
        "status": "Current Status : OK",
        "date": "2020-01-09T14:13:53Z",
        "customer": {
          "id": "1",
          "name": "Cliente A",
          "email": "a@a.com"
        },
        "product": {
          "id": "5",
          "name": "Chuteira",
          "amount": 130
        }
      }
    ]
  }
}
```

### Learn more about graphql with spring and java:
- Building a GraphQL service: https://spring.io/guides/gs/graphql-server
- Getting Started with GraphQL and Spring Boot: https://www.baeldung.com/spring-graphql
- Scalars in GraphQL Java: https://www.graphql-java.com/documentation/scalars

