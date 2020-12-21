# ATM API

This project is using spring framework for all the heavy lifting.

Main class com.atm.Application.

There is a security layer, the requests will fail with 401 if the Authorization header is missing.

Example flow:

Get authorization > Add money(at startup we have 0 balance) > Withdraw money

**Example authorization request:** 
`curl -X POST \
   http://localhost:8080/authenticate \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: c0f0cbf5-2437-edf4-b283-efb6c02de65c' \
   -d '{
 	"pin": "1234",
 	"magneticStrip": "admin"
 }'`
 
 Response: `{
                "authorization": "33775aa3-b116-4e26-8a5a-220987fff211"
            }`
            
Example add money request:
`curl -X PUT \
   http://localhost:8080/operation/add \
   -H 'authorization: 33775aa3-b116-4e26-8a5a-220987fff211' \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 7687c247-0386-f64a-bece-958c136d8717' \
   -d '{
 	"1": 1000,
 	"1000": "10"
 }'`
 
 Example withdraw:
 `curl -X GET \
    http://localhost:8080/operation/withdraw/1200 \
    -H 'authorization: 33775aa3-b116-4e26-8a5a-220987fff211' \
    -H 'cache-control: no-cache' \
    -H 'postman-token: 0f4fddda-a491-2b96-a536-30c77f355b9a'`
    
    
Left to implement: 
    1. tests
    2. dockerization 