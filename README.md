<h1> Vending-Machine </h1>

This is a Java OOP console application which simulates a vending machine. This application demonstrates an understanding of OOP principles, the MVC design pattern, service layer, file I/O, user I/O, Unit testing and dependency injection. 

<h2> Basic Functionality </h2>

* The program displays a list of in stock items and prompts the user to input some money. 
* The user then inputs their chosen amount of money and makes a selection. 
* If the user has enough money for the item, the machine will then deduct the cost of this item from their money and output their change in coins. 
* If the user does not have enough money for the item, the machine will let the user know that they have insuffcient funds and ask for another selection. 
* If the user successfully purchases an item, the item is removed from the machines inventory file. 

<h2> MVC Design </h2>

The program follows the Model View Controller (MVC) design pattern and uses Spring Dependency Injection. The program is split into 6 packages which are responsible for the following. 

* **vendingmachine.dto**: This package holds the data transfer objects (The model). There are 3 dtos, item - a class corresponding to an item in the machine, change - a class corresponding to the change the user will recieve, and coin - an enum used to store different denomination of coins.
* **vendingmachine.ui**: This is the view and is responsible for all user interaction.
* **vendingmachine.dao**: Holds the data access objects. Classes are responsible for data marshalling - reading and writing changes in the system to file. 
* **vendingmachine.service**: Implements business logic of the application. In this application, the service layer is responsible for checking if the user has enough money, retrieving instock items and handling change. The service layer also throws application specific exceptions. 
* **vendingmachin.controller**: The controller is respnsible for orchastrating the flow of the program. The controller uses the view and the service layer to achieve the applications key goals. 
* **vendingmachine**: This holds the App class which is the main class for the program. This class is reponsible for running the program by calling the controller. I have implemented spring dependency injection, the configuration of our controller is read to the app class from the applicationproperties.xml file.

<h2> Files </h2> 

