# Joellibee Fast Food Ordering System

A Java-based desktop application that simulates a fast-food restaurant ordering system with a user-friendly interface built using Java Swing.

## 🍔 Overview

Joellibee is a fast food ordering application that allows users to:

- Browse a menu of food items categorized by type
- Customize items with various options
- Add items to a shopping cart
- Choose between dine-in, take-out, and delivery options
- Complete orders with a streamlined checkout process

## ✨ Features

- **Interactive Menu**: Visually appealing menu with images and descriptions
- **Customization Options**:
  - Meals: Add fries (+₱20) or drinks (+₱25)
  - Drinks: Size options (Small, Medium +₱10, Large +₱20)
  - Quantity selection for all items
- **Order Types**:
  - Dine-in
  - Take-out
  - Delivery (with address collection and ₱50 delivery fee)
- **Shopping Cart**: Real-time updates and order summary
- **MVC Architecture**: Clean separation of concerns for better maintainability

## 🏗️ Architecture

The application follows the Model-View-Controller (MVC) pattern:

- **Model**: Data structures and business logic
- **View**: UI components built with Java Swing
- **Controller**: Handling user interactions and state updates

## 📁 Project Structure

```
src/
├── JoellibeeApp.java             # Application entry point
├── model/                        # Data models
│   ├── CurrencyFormatter.java    # Utility for price formatting
│   ├── MenuItem.java             # Menu item representation
│   ├── OrderItem.java            # Cart item with customizations
│   └── OrderModel.java           # Order state management
├── view/                         # UI components
│   ├── DrinkPanel.java           # Panel for drink items
│   ├── ItemPanel.java            # Base panel for menu items
│   ├── MainView.java             # Main application window
│   ├── MealPanel.java            # Panel for meal items
│   ├── RoundedPanel.java         # Custom panel with rounded corners
│   └── SimpleItemPanel.java      # Panel for simple items
└── controller/                   # Application logic
    ├── MenuManager.java          # Menu initialization and management
    └── OrderController.java      # Handles user interactions
```

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Java Swing (included in JDK)

### Running the Application

1. Compile the Java files:
   ```
   javac -d bin src/JoellibeeApp.java src/model/*.java src/view/*.java src/controller/*.java
   ```

2. Run the application:
   ```
   java -cp bin JoellibeeApp
   ```

## 📱 User Interface

The application features a clean, modern interface with:

- A header featuring the Joellibee logo and name
- A central menu grid showing all available items
- A sidebar cart panel displaying the current order details
- Order type selection and delivery information fields
- Action buttons for order management

## 🧰 Key Components

### Menu Items

The system offers various menu categories:
- **Meals**: Main dishes (Burgers, Spaghetti, Chicken, etc.)
- **Drinks**: Beverages (Soda, Iced Tea, Coffee)
- **Desserts**: Sweet items (Sundae, Cone Twirl)
- **Sides**: Side dishes (Fries, Nuggets)

### Order Process

1. Browse the menu and select items
2. Customize items by:
   - Setting quantity
   - Selecting size (for drinks)
   - Adding extras (for meals)
3. Add items to cart
4. Select order type
5. Provide delivery information (if delivery selected)
6. Review order summary
7. Complete checkout

## 💻 Development

The application is built using:
- Java Swing for the GUI
- MVC architecture for clean code organization
- Custom components for enhanced visual appeal

## 📋 License

This project is available for educational purposes.

## 🙏 Acknowledgments

- Inspired by popular fast food ordering systems
- Uses a simplified version of a popular Filipino fast food chain's branding for educational purposes
