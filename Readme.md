# For runnig 

You need to add the Envoiroment variables 

| name | description |
|--|--|
| STORE_ACCESS_KEY | Access key for the S3 Storage |
| STORE_SECRET_KEY | Secret key for the S3 Storage |
| DB_URL | Url for the JDBC DB sintaxis `'jdbc:mariadb://${host}:${port}/${db_name}'` |
| DB_USER | user for the dataBase |
| DB_PASSWORD | password for the dataBase |
| FLYWAY_USER| user for flyway connection to the data base|
| FLYWAY_PASS| password for flyway connection to the data base|

> **Note:** The sintaxis **${}** indicates a variable.

# Running in the comman line
example how to running in the command line create the .env file and add the envoiroment variables.

```
export $(cat example.env | xargs)

$ java -jar ReceiptApi-0.0.1-SNAPSHOT.jar
```

