# fruit-subscription-app

## Functionality
- order fruit from inventory
- subscribe when fruit is in stock

## Overview
Inventory Serive inter communicated with order service using webflux to check if there are enough products in the stock.

## Inventory Service - 8082
- GET ```localhost:8082/api/inventory/{skuCode}```  
![Screenshot 2023-06-25 131801](https://github.com/AEsir777/fruit-subscription-app/assets/77596290/586b9cc5-a438-4be4-9d27-b0e900c17c62)  
Check if the inventory exists in the database or not  
![Screenshot 2023-06-25 131818](https://github.com/AEsir777/fruit-subscription-app/assets/77596290/bb0bff16-3060-4413-b566-2fe1218b9847)
![Screenshot 2023-06-25 131829](https://github.com/AEsir777/fruit-subscription-app/assets/77596290/074bf95f-9683-43a2-a23d-c8fcfdf8c1c2)
 
## Order Service - 8081
- POST ```localhost:8081/api/order```
The database of inventoryservice contains orange and blueberry
![image](https://github.com/AEsir777/fruit-subscription-app/assets/77596290/2e7072d7-004b-4acb-a102-d3b4dacf5b2f)
By submitting post request for the order items, a synchronous communication to ```localhost:8082/api/inventory/{skuCode}```  
![image](https://github.com/AEsir777/fruit-subscription-app/assets/77596290/27321d7f-7d9d-4f09-9e43-ec309ebe1e51)


If item not exist in the database, throws exception.
![image](https://github.com/AEsir777/fruit-subscription-app/assets/77596290/dbd20a18-f304-4ae4-a26c-329f426c878e)

## TODO
- [ ] add dicovery server
- [ ] add more Junit Test 
- [ ] add notification server for subscription


