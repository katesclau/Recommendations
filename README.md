# Recommendations
Test project with spring rest-service, cache and rest-consume

## Requirements

- Java8
- Maven >= 3.0.5

## Start the server

- `mvn spring-boot:run`

Now you can make API requests, eg: `curl -i localhost:8080/api/recent_purchases/{{username}}

## API Reference

### GET api/recent_purchases/{{username}}

- path params:
  - username

- response (json):

Sorted (by popularity) array of purchases related to users last purchases

```
{
  "purchases": [
    {
      "id": (int),
      "product_id": (int),
      "username": (string),
      "date": (iso8601 string),
      "recent": [ "Martin","John",...]
    },
    ...
  ]
}
