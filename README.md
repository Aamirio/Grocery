# Aamir's Grocery Application
Aamir's Grocery Application adds available items to basket applying any applicable discounts

### Building the application
On command line:
1. Clone the repository  
`git clone` https://github.com/Aamirio/Grocery.git  

2. Navigate to cloned directory  
`cd Grocery/`

3. Build and package the application  
`mvn clean package`

### Running the application
After building the application, run the executable jar by using the `java -jar <jar-path>` utility followed by the 
required args
1. The first arg describes the day of purchase and must be any number equal to or greater than zero. 0 means today, 1 means tomorrow, and so forth
2. All subsequent args are names of items you wish to purchase

##### Examples
* To buy 2 apples and a bottle of milk today:   
`java -jar target/grocery-0.0.1-SNAPSHOT.jar 0 Apple Apple Milk`

* To buy 2 apples and a bottle of milk the day after tomorrow:   
`java -jar target/grocery-0.0.1-SNAPSHOT.jar 2 Apple Apple Milk`

### Future Releases...

... should contain a more suitable CLI where you can add items to your basket one at a time
 
