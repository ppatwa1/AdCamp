# AdCamp

AdCamp is a web application implemented in Spring boot that allows a user to create ad campaigns. It is a maven project. It can be started by using the command in command prompt brwosed to the folder location: mvn spring-boot:run

The application has the following functionalities:
1. Create a new Ad
2. Get a single Ad based on partner_id
3. Get all Ads (Does not allow the user with partner_id: all): returns a list of JSON objects

The Ad request has following attributes:
1. partner_id
2. duration
3. ad_content

* ConcurrentHashMap is used to store ad campaign data
* Only one active campaign exists for a given partner
* Supports multiple Ad campaigns for a single partner
* AdTest.java tests creation of new ad campaign for a given partnerId if there are no active campaigns for the partner
* It tests retrieval of active Ad given a partnerId
* Retrieval of all campaigns

URL for various features(tested using postman):
1. Create a new campaign: http://localhost:8080/ad/createAd
Request body : {
                 "partner_id":"Ram",
                 "duration":40,
                 "ad_content":"Ad by Ram"
               }

Response: {
           "respMsg": "New Ad Campaign created at - http://localhost:8080/ad/Ram",
           "respCd": 201,
           "adInfo": null
          }

2. Get ad data: http://localhost:8080/ad/Ram

3. Return list of all campaigns: http://localhost:8080/ad/all


CURL commands:

1. Curl command to create a new Ad: curl -X "POST" "http://localhost:8080/ad/createAd -H "Content-Type: application/json" -d $'{"partner_id": "Ram", "duration": 40, "ad_content": "Ad by Ram"}'

2. Curl command to get a single Ad: curl -X GET -D headers http://localhost:8080/ad/Ram

3. Curl command to get all ads: curl -X GET -D headers http://localhost:8080/ad/all
