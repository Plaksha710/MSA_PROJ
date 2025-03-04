# MSA_PROJ
This E-Bookstore Project includes 5 Microservices.
1. User
2. Book
3. Cart
4. Order
5. Payment

# The port Numbers for these Microservices are:
1. User    - 8084
2. Book    - 8081
3. Cart    - 8082
4. Order   - 8083
5. Payment - 8085

# API Endpoints:
# User Service (/users) → Manages user-related operations.
* GET : /users → Get all users
* GET : /users/{userId} → Get user by ID
* GET : /users/{userId}/exists → Check if a user exists
* POST : /users → Create a new user
* DELETE : /users/{userId} → Delete user by ID

# Book Service (/books) → Manages book-related operations.
* POST : /books → Create a new book
* GET : /books → Get all books
* GET : /books/{id} → Get book by ID
* PUT : /books/{id} → Update book by ID
* DELETE : /books/{id} → Delete book by ID

# Cart Service (/carts) → Manages cart-related operations.
* POST : /carts → Add an item to the cart
* GET : /carts → Get all cart items
* GET : /carts/{id} → Get cart item by ID
* GET : /carts/{id}/book → Get cart item with book details by ID
* PUT : /carts/{id} → Update cart item by ID
* DELETE : /carts/{id} → Delete cart item by ID

# Order Service (/orders) → Manages order-related operations.
* POST : /orders → Create a new order
* GET : /orders → Get all orders
* GET : /orders/{id} → Get order by ID
* DELETE : /orders/{id} → Delete order by ID

# Payment Service (/payments) → Manages payment-related operations.
* POST : /payments → Create a new payment
* GET : /payments → Get all payments
* GET : /payments/{id} → Get payment by ID
* DELETE : /payments/{id} → Delete payment by ID

# Microservices Communication
* The Cart Microservice communicates with the Book Microservice to get book details when retrieving cart items (/carts/{id}/book).
* The Order Microservice should communicate with both the Cart Microservice (to retrieve cart items before placing an order) and the User Microservice (to associate orders with users).
* The Payment Microservice interacts with the Order Microservice to process payments for specific orders.
