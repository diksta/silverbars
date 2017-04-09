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
- That there is a requirement to display registered orders (the question did not specify this) and they are shown in the order they were registered
- Buys and Sells for the same amount are combined:
  i.e. Buy 3.5 kg for £306 and Sell 3 kg for £306 
       are combined to get Buy 0.5kg for £306       
- Sell orders are displayed separately to Buy orders in the summary.

## Design decisions

- No assumption is made about the consumer, so the library has been left as a service
- Getters have been added to Order domain objects to allow for potential transfer object mapping or alternative display requirements
- Composition was preferred over inheritance for the Buy and Sell SummaryItems. This results in more classes but a more flexible implementation
- Order quantity is stored as grams. The default summary toString converts to Kg as this is how it is displayed in the spec.
  Order currently has a getter for grams and a method that converts to kilograms has not been implemented as there was no clear requirement.
- Orders are kept in memory in the service rather than a separate repository as it felt like overkill to implement an extra class to hold the set.