# Welcome to Book store!
This microservice deals with online book store management

# Setup

1. Create db using below script
```
CREATE SEQUENCE IF NOT EXISTS books_id_seq START WITH 1000;

CREATE TABLE IF NOT EXISTS books(
    id INTEGER PRIMARY KEY DEFAULT nextval('books_id_seq'),
    title TEXT UNIQUE NOT NULL,
    authors TEXT[] NOT NULL,
    categories TEXT[] NOT NULL,
    description TEXT,
    price DECIMAL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER SEQUENCE books_id_seq OWNED BY books.id;
```

2. Post book api

```curl --location --request POST 'localhost:8081/bookStore/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title":"title",
    "authors":["author1","author2"],
    "categories":["LITERATURE", "SCIENCE"],
    "description":"description1",
    "price":1234
}' 
```
 3. Delete book by id api

```
curl --location --request DELETE 'localhost:8081/bookStore/1002'
 ```

4. Get book by id api
```
curl --location --request GET 'localhost:8081/bookStore/1002'
```

5.Get all books 
```
curl --location --request GET 'localhost:8081/bookStore/all?page=0&size=1'
```

6. Get books by category name api
```
curl --location --request GET 'localhost:8081/bookStore/byCategory?category=SCIENCE&page=0&size=2'
```

7. Get books by author name api
```
curl --location --request GET 'localhost:8081/bookStore/byAuthor/author2?page=0&size=1'
```

