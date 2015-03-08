## Simple Calculator

A simple calculator that evaluates expressions in a very simple integer expression language. The program takes an input on the command line, computes the result, and prints it to the console.

Operations supported - <br />
Add - addition <br />
Sub - subtraction <br />
Mult - multiplication <br />
Div - division <br />

Examples - <br />
add(1,2) <br /> 
Output -> 3

add(1,mult(2,3)) <br /> 
Output -> 7

mult(add(2,2), div(9,3)) <br />
Output -> 12


### Setup
```
git clone https://github.com/rhcode/simple-calculator.git
cd simple-calculator/
```

### Build
```
mvn package
```

### Running the application
```
cd target/
java -jar simple-calculator-1.0-SNAPSHOT-jar-with-dependencies.jar "add(2,2)"
```