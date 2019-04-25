# UK Self-Employed Tax Calculation API
A simple UK self-employed tax calculation API written in Java and fully tested with JUnit5.
Testing style is mostly BDD which also explains basic use cases. 

The smallest unit for computation is a weekly value. This is informed by the fact that the UK self-employment tax is calculated on a weekly basis. For example, a self-employed NI2 is usually a weekly flat rate. See [self-employment National Insurance rates](https://www.gov.uk/self-employed-national-insurance-rates). The API is applicable to taxable income up to a limit of £150,000 and therefore not applicable to income higher than this threshold. 

## Unit Tests
The test suite for the API can be found in the test [folder](https://github.com/Codeama/TaxCalculationAPI/tree/master/src/test/java/com/bukola).

##  Features
1. Class for calculating weekly income/wages
2. Class for calculating weekly expenses
3. Class for calculating profits/actual earnings
4. Class for calculating weekly National Insurance 2
5. Class for calculating weekly National Insurance 4
6. Class for calculating weekly Income Tax
7. Class for aggregating total weekly payable tax, which is a total of all four above

## Uses
This API can be integrated into any self-employed application that requires a UK tax calculation.

## Documentation
The API documentation can be found [here.](https://codeama.github.io/UK_Self-Employed_Tax_Calculation_API/)

## Technologies Used
- Java - this API was compiled and build against Java 8 (openJDK 8)
- JUnit5
- Intellij IDEA

## Download
Download the executable jar from [Releases.](https://github.com/Codeama/UK_Self-Employed_Tax_Calculation_API/releases)
