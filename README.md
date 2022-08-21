# MyLoc App Backend API

## Database Diagram

![db_diagram drawio](https://user-images.githubusercontent.com/58569590/185811982-20f1c7df-b28f-45d2-b7af-9dcc80a96ddc.png)

### Endpoints

#### Register

```
POST /auth/register HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{
    "name": "user",
    "username": "user",
    "password": "user"
}
```

#### Login

```
POST /auth/login HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{
    "username": "user",
    "password": "user"
}
```


#### Save One Location

```
POST /location HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{
    "name": "Place Name",
    "longtitude": 41.008583,
    "latitude": 28.980175,
    "price": 50
}
```

#### Get All Locations

```
GET /location HTTP/1.1
Host: localhost:8080
```

#### Upload Image for Location History

```
POST /history/upload HTTP/1.1
Host: localhost:8080
Content-Type: multipart/form-data  
```


#### Create One Location History Item

```
POST /history HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{
    "user_id" : 1,
    "location_id" : 1,
    "comment": "Very nice place!"
}
```

#### Delete Location by ID

```
DELETE /location/{location_id} HTTP/1.1
Host: localhost:8080
```

#### Get Location History Items Of User by userId

```
GET /history/{user_id} HTTP/1.1
Host: localhost:8080
```

#### Add Money To Card (50)

```
GET /user/add-to-card/{user_id} HTTP/1.1
Host: localhost:8080
```
