# silverbars

## To build

./gradlew clean install

## Artifacts

./build/libs/orderboard-1.0-SNAPSHOT.jar

## Services

### Order Service

Register orders, fetch or cancel existing orders, or retrieve an order summary. 

## Assumptions

- Weights are to the nearest gram
- There is a requirement to display registered orders (the question did not specify this) and that the order of those orders is in the order they were registered
- The 
- Buys and Sells for the same amount are combined:
  i.e. Buy 3.5 kg for £306 and Sell 3 kg for £306 
       are combined to get Buy 0.5kg for £306       
- Sell orders are displayed separately to Buy orders in the summary
