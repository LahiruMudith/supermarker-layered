# Supermarket JavaFX Application

This JavaFX application provides efficient management of customers, items, and orders. The project utilizes a layered architecture converted from MVC and is backed by a MySQL database.

## Features

- **Customer Management**: Add, update, delete, and view customer details.
- **Item Management**: Maintain item inventory with ease.
- **Order Management**: Seamlessly create and manage orders.

## Architecture

The application is designed with a layered architecture to enhance separation of concerns and maintainability:

1. **Presentation Layer**: Contains the JavaFX UI components.
2. **Service Layer**: Encapsulates the business logic.
3. **Data Access Layer**: Handles all database interactions.

## Technologies Used

- JavaFX
- MySQL
- JDBC (Java Database Connectivity)
- Conversion from MVC to Layered Architecture

## Database Configuration

To get started, ensure you have MySQL installed and create a database named `your_database_name`. Use the following SQL script to create the necessary tables:

```sql
CREATE DATABASE supermarketfx;
USE supermarketfx;

CREATE TABLE customer (
    customer_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    nic VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20)
);

CREATE TABLE item (
    item_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    quantity INT(11),
    price DECIMAL(10,2)
);

CREATE TABLE orders (
    order_id VARCHAR(10) PRIMARY KEY,
    customer_id VARCHAR(10),
    order_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE orderdetails (
    order_id VARCHAR(10),
    item_id VARCHAR(10),
    quantity INT(11),
    price DECIMAL(10,2),
    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (item_id) REFERENCES item(item_id)
);
```
#CUSTOMER MANAGE
![image](https://github.com/user-attachments/assets/6d8d6c65-c73b-433a-a7a5-614109839227)

#ITEM MANAGE
![image](https://github.com/user-attachments/assets/ab5f3cad-331f-4918-8518-f6dda6e62d83)

#ORDER MANAGE
![image](https://github.com/user-attachments/assets/adf9a3e5-2136-457f-86a4-f11ebf7823c3)
