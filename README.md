# Food-Delivery-Platform-Api

## Project Overview
The Food Delivery Platform API is a comprehensive system designed to streamline the process of booking and ordering food from a restaurant. This API allows users to search for foods, view their menus, place orders, and interact with restaurant information.

## Tech Stack
Before you begin, make sure you have the following tools and dependencies installed:

**Java Development Kit (JDK)**

**Languages & FrameWorks :** Java, SpringBoot

**Data Storage :** MySql Data Base

**Tools Used :** IntelliJ, PostMan, Swagger, MySql WorkBench

**Maven or Gradle (for project build management)**

**AWS Deployment(Build and deployed the project on AWS server)**

## Documentation Dataflow

In this Food Delivery Platform API project, the data flows between different components as follows:


### User Management

- **Description**: Users are a fundamental part of the system, and they can be created with essential information.
- **Attributes**:
  - Id: An auto-generated unique identifier for each food User.
  - Name: The user's full name.
  - Email: The user's email address.
  - Password: The user's password for authentication.
  - Phone Number: The user's contact phone number.
- **Storage**: User data is securely stored in the database.

### Admin Management

- **Description**: Admins can get a hold of handling most of the APIs.
- **Attributes**:
  - Id: An auto-generated unique identifier for each Admin.
  - Name: The user's full name.
  - Email: The user's email address.
  - Password: The user's password for authentication.
  - Phone Number: The user's contact phone number.
- **Storage**: Product details are stored in the database and can be easily managed.

### Food Management

- **Description**: The Food Management section of this API addresses the management of food items available for order.
- **Attributes**:
  - Id: An auto-generated unique identifier for each food item.
  - Name: The name of the food item.
  - Description: A brief description of the food item, including ingredients or special features.
  - Price: The price of the food item.
- **Storage**: Address information is stored in the database for easy retrieval.

### Order Management

- **Description**: The Order Management section of this API handles the management of food orders placed by users. Each order is associated with specific attributes and can be linked to the respective user and food item.
- **Attributes**:
  - Id: An auto-generated unique identifier for each order.
  - Quantity: The quantity of food items ordered.
  - StatusFood: The status of the order, represented as an enumeration (e.g., "CREATED," "DISPATCHED," "DELIVERED").
  - CreationTimeStamp: The timestamp when the order was created.
- **Storage**: Order information, including order ID, quantity, status, and timestamp, is stored in the database for tracking and management.
- **Association**:
  - Orders are associated with a user:
    - User Association: Each order is linked to a specific user, indicating who placed the order.
  - Orders are associated with a food item:
    - Food Association: Each order is linked to a specific food item that the user ordered.

This section provides an overview of the attributes associated with food orders, their relationships with users and food items, and how they are stored in the system.


### Token Management

- **Description**:  The Token Management section of this API handles the management of authentication tokens used for user and admin authentication. These tokens are associated with specific attributes and can be linked to either a user or an admin, depending on their purpose.
- **Attributes**:
  - Id: An auto-generated unique identifier for each authentication token.
  - Value: A unique token value used for authentication.
  - CreationTimeStamp: The timestamp when the token was created.
- **Storage**: Token information, including the token ID, value, and timestamp, is stored in the database. This allows for secure user and admin authentication within the system.
- **Token Creation**: Tokens are created with the following characteristics.
  - User Token: When created for a user, the token is associated with that user, and it's generated with a timestamp.
  - Admin Token: When created for an admin, the token is associated with the admin, and it's generated with a timestamp.
- **Association**:  Tokens are associated with either a user or an admin:
  - User Association: Each user token is linked to a user for user authentication.
  - Admin Association: Each admin token is linked to an admin for admin authentication.

## Data Flow Diagram



                            ┌─────────────────────────┐
                            │   Restaurant Booking App│
                            └───────────┬─────────────┘
                                        │
                                        ▼
                            ┌─────────────────────────┐
                            │       Controllers       │
                            └───────────┬─────────────┘
                                        │
                                        ▼
                            ┌─────────────────────────┐
                            │       Services          │
                            └───────────┬─────────────┘
                                        │
                                        ▼
                            ┌─────────────────────────┐
                            │       Repositories      │
                            └───────────┬─────────────┘
                                        │
                                        ▼
                            ┌─────────────────────────┐
                            │       Database          │
                            └─────────────────────────┘


The Restaurant Food Booking API is a comprehensive system designed to streamline the process of booking and ordering food from a restaurant. This API allows administrators and users to perform various actions, including signing up, signing in, managing food items, managing orders, and interacting with users. Below are the available API endpoints for administrators and users.


## Admin API

The Admin API section provides endpoints for administrators to manage the platform. Administrators can perform various actions, including signing up, signing in, managing food items, managing orders, and interacting with users.

### Admin Sign-Up

- **Endpoint:**
```
POST http://localhost:8080/admin/signUp
```
- **Request:** Create a new admin account.
- **Request Body:** Admin object with details.
- **Validation:** Admin details are validated.
- **Response:** Returns a success message if the admin account is created successfully.

## Admin Sign-Up Request Body

```
{
  "adminId": 1,
  "adminName": "JohnDoe",
  "adminEmail": "JohnDoe123@admin.com",
  "adminPassword": "J@hnDoe123",
  "adminContact": "+919876543211"
}
```

### Admin Sign-In

- **Endpoint:**

```
POST http://localhost:8080/admin/signIn/email/{email}/password/{password}
```

- **Request:** Authenticate as an admin.
- **Path Parameters:** `email` (string), `password` (string)
- **Response:** Returns a success message upon successful admin authentication.

### Admin Sign-Out

- **Endpoint:**

```
DELETE http://localhost:8080/admin/signOut/email/{email}/tokenValue/{tokenValue}
```

- **Request:** Sign out as an admin.
- **Path Parameters:** `email` (string), `tokenValue` (string)
- **Response:** Returns a success message upon successful sign-out.

### Food API

### Create Food

- **Endpoint:**

```
POST http://localhost:8080/addFood/adminEmail/{adminEmail}/tokenValue/{tokenValue}
```

- **Request:** Add one or more food items.
- **Request Body:** List of food objects.
- **Validation:** Food details are validated.
- **Response:** Returns a success message if food items are added successfully.

## Create Food Request Body

```
{
  "foodId": 1,
  "foodName": "Burger",
  "foodDescription": "Crispy Chicken Burger",
  "foodPrice": 250
}
```

### Get All Foods

- **Endpoint:**

```
GET http://localhost:8080/orders/adminEmail/{adminEmail}/tokenValue/{tokenValue}
```

- **Request:** Retrieve a list of all orders.
- **Response:** Returns a list of all orders on the platform.

## User API

The User API section provides endpoints for users to interact with the platform. Users can perform various actions, including signing up, signing in, placing orders, and managing their account.

### User Sign-Up

- **Endpoint:**

```
POST http://localhost:8080/signUp
```

- **Request:** Create a new user account.
- **Request Body:** User object with details.
- **Validation:** User details are validated.
- **Response:** Returns a success message if the user account is created successfully.

## User Sign-Up Request Body

```
{
  "userId": 1,
  "userName": "John Wick",
  "userEmail": "johnwick123@gmail.com",
  "userPassword": "J@hnwick123",
  "userContact": "+918987868583"
}
```

### User Sign-In

- **Endpoint:**

```
POST http://localhost:8080/signIn/email/{email}/password/{password}
```

- **Request:** Authenticate as a user.
- **Path Parameters:** `email` (string), `password` (string)
- **Response:** Returns a success message upon successful user authentication.

### User Sign-Out

- **Endpoint:**

```
DELETE http://localhost:8080/signOut/email/{email}/tokenValue/{tokenValue}
```

- **Request:** Sign out as a user.
- **Path Parameters:** `email` (string), `tokenValue` (string)
- **Response:** Returns a success message upon successful sign-out.

### Place an Order

- **Endpoint:**

```
POST http://localhost:8080/placeOrder
```

- **Request:** Place an order by specifying the order details.
- **Request Body:** Place Order DTO with user and order details.
- **Response:** Returns a success message if the order is placed successfully.

## Place Order Request Body

```
{
  "authenticationInputDto": {
    "email": "johnwick123@gmail.com",
    "tokenValue": "sdfgsdgdfthhhr"
  },
  "order": {
    "orderId": 1,
    "orderQuantity": 1,
    "orderStatusFood": "CREATED",
    "orderCreationTimeStamp": "2023-10-29T15:20:38.478Z",
    "user": {
      "userId": 1,
      "userName": "John Wick",
      "userEmail": "johnwick123@gmail.com",
      "userPassword": "J@hnWick123",
      "userContact": "+918987868584"
    },
    "food": {
      "foodId": 1,
      "foodName": "Burger",
      "foodDescription": "Crispy Chicken Burger",
      "foodPrice": 250
    }
  }
}
```

### Cancel an Order

- **Endpoint:**

```
DELETE http://localhost:8080/cancelOrder/orderId/{orderId}
```

- **Request:** Cancel an order by its ID.
- **Path Parameters:** `orderId` (integer)
- **Response:** Returns a success message if the order is canceled successfully.

## Cancel Order Request Body

```
{
  "email": "johnwick123@gmail.com",
  "tokenValue": "sdfgsdgdfthhhr"
}
```

### Get All Foods

- **Endpoint:**

```
GET http://localhost:8080/foods
```

- **Request:** Retrieve a list of all available food items.
- **Response:** Returns a list of all food items available on the platform.

## Get All Foods Request Body

```
{
  "email": "johnwick123@gmail.com",
  "tokenValue": "sdfgsdgdfthhhr"
}
```

### Get All Orders

- **Endpoint:**

```
GET http://localhost:8080/previousOrders/email/{email}/tokenValue/{tokenValue}
```

- **Request:** Retrieve a list of all orders placed by the user.
- **Path Parameters:** `email` (string), `tokenValue` (string)
- **Response:** Returns a list of all orders placed by the user.

## Get All Orders Request Body

```
{
  "email": "johnwick123@gmail.com",
  "tokenValue": "sdfgsdgdfthhhr"
}
```

### Get Order by ID

- **Endpoint:**

```
GET http://localhost:8080/orderById/{orderId}/email/{email}/tokenValue/{tokenValue}
```

- **Request:** Retrieve an order by its ID.

## Get Order by ID Request Body

```
{
  "email": "johnwick123@gmail.com",
  "tokenValue": "sdfgsdgdfthhhr"
}
```


## Contributors

- **Syed Waseem** : [syed.waseemXXXX@gmail.com]()

We welcome contributions and feedback to improve and expand this project.
