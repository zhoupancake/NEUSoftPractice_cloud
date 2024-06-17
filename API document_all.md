# API document for NEUSoft Environmental Protection Public Supervision System

## Content

[TOC]


## Introduction

This project is implemented based on the Spring Cloud framework, which needs several ports. So here comes the descriptions for the port and module usages.

- Air Data Management Module

  This module is designed to accept and process the requests concerning the CURD operations of the air data containing the air condition in specific location on specific time. It executes on the port . 

- Permission Control Module

  This module is designed to accept and process the requests concerning the human resource management and authentication. It executes on the port 8081.

- Report Management Module

  This module is designed to accept and process the requests concerning the CRUD operations of the reports submitted by public supervisors. It executes on the port .

- Submission Management Module

  This module is designed to accept and process the requests concerning the CRUD operations of the submissions submitted after grid AQI detectors achieve the tasks. It executes on the port.

- Task Management Module

  This module is designed to accept and process the requests concerning the CURD operations of the tasks delivered by the system administrators based on the reports. It executes on the port .

- Zone Management Module

  This module is designed as a static resource module to accept internal requests to transform the city id to the city name or query the city id based on the city name. It executes on the port .

The following parts will explain the detail request paths of the specific operations. But it is worth to mention that this document contains the descriptions of all the interfaces used in the system consisting the internal call interfaces. So this document is designed for testing all the interfaces used in the system.

Moreover, aiming at simplify the testing process, the request paths used in this document  work only in test environment. They will be banned when deploy on the real executing environment.

## Framework of the Micro-service Module

Each micro-service module contains three kinds of interfaces.

- API

  The interfaces exist in API are the internal interfaces called by other micro-service modules in the system. So this kind of interface only processes basis request to operate on database like CRUD operations, which means that the work of edge control and value verification will be completed by the controller layer function that calls this interface.

- Action Controller

  The interfaces exist in Action Controller are the external interfaces called by the front-end. So these interfaces contain the majority of the service logic. They need to process the edge control logic and value verification logic. If the value is legal, these interfaces will  pass it to the API interfaces or operate the database directly.

- Object Controller

  The interfaces exist in Object Controller are the basis CRUD operations of the corresponding object. They will not be called by any other character or micro-service modules. They act as a backup or test template to test whether the database operation interfaces execute correctly. Their request paths will be hidden when the system is deployed on the real executed environment.
  

## Air Data Manage Module

### Air Data API

#### Add air data

This interface should accept the air data object and save it into the database. If save successfully then return "true", otherwise it should return "false".

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8085/api/AirData/addAirData

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "cityId" : 1,
        "location" : "Zhongnanhai",
        "pm25" : 13,
        "pm10" : 12,
        "so2" : 13,
        "no2" : 45,
        "co" : 12,
        "o3" : 123
    }
    ```

    Response body

    ```json
    true
    ```
  
  - Error test case: pass into invalid air condition data
  
    Request body
  
    ```json
    {
        "cityId" : 1,
        "location" : "Zhongnanhai",
        "pm25" : -1,
        "pm10" : 12,
        "so2" : 13,
        "no2" : 45,
        "co" : 12,
        "o3" : 123
    }
    ```
  
    Response body
  
    ```json
    false
    ```

### Action Controller

The create operations of air data in the system is attached with other operations, there is no requirement that the characters can create the air data directly. So there is no creation method in the external interfaces.

#### Select all

- Request character: administrator, digital screen

- Request method: GET

- Request method: http://localhost:8085/airData/adminstrator/selectAll

- Test cases

  - Correct test case

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115664407388483584",
                "province": "Beijing",
                "city": "Beijing",
                "location": "No. 23, Jianguo Road, Chaoyang District",
                "date": "2024-03-22T13:33:07",
                "pm25": 28.0,
                "pm10": 61.0,
                "so2": 5.0,
                "no2": 49.0,
                "co": 0.73,
                "o3": 10.0,
                "aqiLevel": 1,
                "aqi": 55
            },
            {
                "id": "1115664407388483585",
                "province": "Beijing",
                "city": "Beijing",,
                "location": "No. 44, Jingshan Xijie, Xicheng District",
                "date": "2024-04-23T11:29:52",
                "pm25": 51.0,
                "pm10": 79.0,
                "so2": 5.0,
                "no2": 47.0,
                "co": 1.19,
                "o3": 13.0,
                "aqiLevel": 2,
                "aqi": 72
            }
            ......
        ],
        "message": "query successfully"
    }
    ```

#### Select all with page query

- Request character: administrator, digital screen

- Request method: POST

- Request method: http://localhost:8085/administrator/airData/selectAll/page

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "pageNum": 1,
        "pageSize": 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115664407388483584",
                "province": "Beijing",
                "city": "Beijing",
                "location": "No. 23, Jianguo Road, Chaoyang District",
                "date": "2024-03-22T13:33:07",
                "pm25": 28.0,
                "pm10": 61.0,
                "so2": 5.0,
                "no2": 49.0,
                "co": 0.73,
                "o3": 10.0,
                "aqiLevel": 1,
                "aqi": 55
            },
            {
                "id": "1115664407388483585",
                "province": "Beijing",
                "city": "Beijing",
                "location": "No. 44, Jingshan Xijie, Xicheng District",
                "date": "2024-04-23T11:29:52",
                "pm25": 51.0,
                "pm10": 79.0,
                "so2": 5.0,
                "no2": 47.0,
                "co": 1.19,
                "o3": 13.0,
                "aqiLevel": 2,
                "aqi": 72
            },
            {
                "id": "1115664407388483586",
                "province": "Beijing",
                "city": "Beijing",
                "location": "No. 19, Xinjiangongmen Road, Haidian District",
                "date": "2024-04-25T10:32:57",
                "pm25": 13.0,
                "pm10": 30.0,
                "so2": 3.0,
                "no2": 30.0,
                "co": 0.42,
                "o3": 29.0,
                "aqiLevel": 1,
                "aqi": 30
            },
            {
                "id": "1115664407388483587",
                "province": "Beijing",
                "city": "Beijing",
                "location": "Jia 1, Tiantan Dongli, Dongcheng District",
                "date": "2024-03-28T15:46:56",
                "pm25": 15.0,
                "pm10": 37.0,
                "so2": 3.0,
                "no2": 23.0,
                "co": 0.42,
                "o3": 39.0,
                "aqiLevel": 1,
                "aqi": 37
            },
            {
                "id": "1115664407388483588",
                "province": "Beijing",
                "city": "Beijing",
                "location": "No. 28, Qinghua West Road, Haidian District",
                "date": "2024-04-15T15:35:09",
                "pm25": 10.0,
                "pm10": 27.0,
                "so2": 2.0,
                "no2": 18.0,
                "co": 0.3,
                "o3": 40.0,
                "aqiLevel": 1,
                "aqi": 28
            }
        ],
        "message": "query successfully"
    }
    ```

  - Error test case: invalidated page number and page size

    Request body

    ```json
    {
        "pageNum": 0,
        "pageSize": 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "pageNum and pageSize must be positive"
    }
    ```

#### Select air data in the same province with page query

- Request character: administrator, digital screen

- Request method: POST

- Request path: http://localhost:8085/administrator/airData/selectByProvince

- Test case

  - Correct test case

    Request body

    ```json
    {
        "pageSize": 2,
        "pageNum": 1,
        "province": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115664407388483584",
                "province": "Beijing",
                "city": "Beijing",
                "location": "No. 23, Jianguo Road, Chaoyang District",
                "date": "2024-03-22T13:33:07",
                "pm25": 28.0,
                "pm10": 61.0,
                "so2": 5.0,
                "no2": 49.0,
                "co": 0.73,
                "o3": 10.0,
                "aqiLevel": 1,
                "aqi": 55
            },
            {
                "id": "1115664407388483585",
                "province": "Beijing",
                "city": "Beijing",
                "location": "No. 44, Jingshan Xijie, Xicheng District",
                "date": "2024-04-23T11:29:52",
                "pm25": 51.0,
                "pm10": 79.0,
                "so2": 5.0,
                "no2": 47.0,
                "co": 1.19,
                "o3": 13.0,
                "aqiLevel": 2,
                "aqi": 72
            }
        ],
        "message": "query successfully"
    }
    ```

### Air data Controller

#### add air data

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8085/hide/airData/addAirData

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "cityId" : 1,
        "location" : "Zhongnanhai",
        "pm25" : 13,
        "pm10" : 12,
        "so2" : 13,
        "no2" : 45,
        "co" : 12,
        "o3" : 123
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "create airData successfully"
    }
    ```

  - Error test case: pass into invalid air condition data

    Request body

    ```json
    {
        "cityId" : 1,
        "location" : "Zhongnanhai",
        "pm25" : -1,
        "pm10" : 12,
        "so2" : 13,
        "no2" : 45,
        "co" : 12,
        "o3" : 123
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "airData value must be positive"
    }
    ```

#### Delete air data

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8085/hide/airData/deleteAirData

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id": "1118528051075870720"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "delete airData successfully"
    }
    ```

  - Error test case: pass into a non-existed air data id

    Request body

    ```json
    {
        "id": "1118528051075780720"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to delete airData "
    }
    ```

#### Modify air data

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8085/hide/airData/deleteAirData

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1118785669233004544",
        "cityId" : 1,
        "location" : "Zhongnanhai",
        "pm25" : 13,
        "pm10" : 12,
        "so2" : 13,
        "no2" : 45,
        "co" : 12,
        "o3" : 123
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "modify airDatasuccessfully"
    }
    ```

  - Error test case: pass into a non-existed air data id

    Request body

    ```json
    {
        "id" : "1118785669233004544",
        "cityId" : 1,
        "location" : "Zhongnanhai",
        "pm25" : 13,
        "pm10" : 12,
        "so2" : 13,
        "no2" : 45,
        "co" : 12,
        "o3" : 123
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "airData not exist"
    }
    ```

  - Error test case: pass into invalid air condition value

    Request body

    ```json
    {
        "id" : "1118785669233004544",
        "cityId" : 1,
        "location" : "Zhongnanhai",
        "pm25" : -1,
        "pm10" : 12,
        "so2" : 13,
        "no2" : 45,
        "co" : 12,
        "o3" : 123
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "airData value must be positive"
    }
    ```

#### Query air data list with page query

-  Request character: project tester

- Request method: POST

- Request path: http://localhost:8085/hide/airData/query

- Test cases

  - Correct test case

    Request body

    ```json
    ```

    Response body

    ```
    ```

## Permission Control module

### Administrator API

#### Get administrator by Id

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8081/api/administrator/getAdminstratorById

- Test cases

  - Correct test case

    Request body

    ```json
    1115604743795765248
    ```

    Response body

    ```json
    {
        "id": "1115604743795765248",
        "idCard": "2021123456",
        "name": "Yeung Ling Ling"
    }
    ```

  - Error test case: require a non-existed administrator

    Request body

    ```json
    1
    ```

    Response body

    ```json
    null
    ```

### Grid detector API

#### Get detector by id

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8081/api/gridDetector/getGridDetectorById

- Test cases

  - Correct test case

    Request body

    ```json
    1115614307672911872
    ```

    Response body

    ```json
    {
        "id": "1115614307672911872",
        "cityId": 4,
        "idCard": "2012567890",
        "name": "grid_test4"
    }
    ```

  -  Error test case: require a non-existed grid detector

    Request body

    ```json
    1
    ```

    Response body

    ```json
    null
    ```

#### Get detector by city

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8081/api/gridDetector/getDetectorSameCity

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" :2,
        "pageNum" :1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    [
        {
            "id": "1115613655483805696",
            "cityId": 2,
            "idCard": "2021345678",
            "name": "grid_test2"
        },
        {
            "id": "1116545704042319872",
            "cityId": 2,
            "idCard": "2012567890",
            "name": "testDetector"
        }
    ]
    ```

    Request body

    ```json
    {
        "id" :11,
        "pageNum" :1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    []
    ```


#### Get detector by province

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8081/api/gridDetector/getDetectorSameProvince?pageNum=1&pageSize=5

- Test cases

  - Correct test case

    Request body

    ```json
    [2]
    ```
    
    Response body
    
    ```json
    [
        {
            "id": "1115613655483805696",
            "cityId": 2,
            "idCard": "2021345678",
            "name": "grid_test2"
        },
        {
            "id": "1116415903470080000",
            "cityId": 2,
            "idCard": "2012567890",
            "name": "testDetector"
        },
        {
            "id": "1116545704042319872",
            "cityId": 2,
            "idCard": "2012567890",
            "name": "testDetector"
        }
    ]
    ```
    
    Request bosy
    
    ```json
    [11]
    ```

    Response body
    
    ```json
    []
    ```

#### Get detector of other province

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8081/api/gridDetector/getDetectorSameProvince?pageNum=1&pageSize=5

- Test cases

  - Correct test case

    Request body

    ```json
    [2]
    ```
    
    Response body
    
    ```json
    [
        {
            "id": "1115613612534132736",
            "cityId": 1,
            "idCard": "2015876543",
            "name": "grid_test1"
        },
        ......
    ]
    ```
    
    Request body
    
    ```json
    [1,2,3,4,5,6,7,8,9,10]
    ```

    Response body
    
    ```json
    []
    ```
    
    

### Supervisor API

#### Get supervisor by id

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8081/api/supervisor/getSupervisorById

- Test cases

  - Correct test case

    Request body

    ```json
    1115616037923975168
    ```

    Response body

    ```json
    {
        "id": "1115616037923975168",
        "tel": "18901234567",
        "name": "super6",
        "birthYear": 1999,
        "sex": 0,
        "cityId": 6
    }
    ```

  - Error test case: pass into a non-existed supervisor id

    Request body

    ```json
    1115616037923975110
    ```

    Response body

    ```json
    null
    ```

### Action Controller( User Controller)

#### Login

- Request character: administrator, supervisor, grid detector

- Request method: POST

- Request path: http://localhost:8081/user/login

- Test cases

  - Correct test case

    Request body

    ```json
    {  
        "username": "2013567890",
        "password": "admin2"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6ImFkbWluMiIsInJvbGUiOiJBZG1pbmlzdHJhdG9yIiwibmFtZSI6IjIwMTM1Njc4OTAiLCJpZCI6IjExMTU2MDQ1MTE4NzU5MTk4NzIiLCJleHAiOjE3MTgzNzA1NzN9.dBi_WIjxwaBFA2-JVhp_iorEqJUHmnoqQ6z2bT8zkRA",
        "message": "Login successfully"
    }
    ```

  - Error test case: pass into error username or password

    Request body

    ``` json
    {  
        "username": "323232",
        "password": "grid5"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to login for error username or password"
    }
    ```

  - Error test case: request to login a deactivated account

    Request body

    ```json
    {  
        "username": "2016456789",
        "password": "admin3"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to login, The account is banned"
    }
    ```

  - Error test case: use error password over 5 times

    Request body

    ```json
    {  
        "username": "2015328754",
        "password": "admin"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to The account is locked with 15 minutes"
    }
    ```

#### Change password

- Request character: administrator, supervisor, grid detector

- Request method: POST

- Request path: http://localhost:8081/user/changePassword

- Test cases

  - Correct test case

    Request body

    ```json
    {
    	"id": "1115614529362849792",
    	"username" : "2017890123",
    	"password" : "grid8",
    	"newPassword" : "gridDetector8"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "modify successfully"
    }
    ```

  - Error test case: change the password of a non-existed user

    Request body

    ```json
    {
    	"id": "1116514529362849792",
    	"username" : "2017890123",
    	"password" : "grid8",
    	"newPassword" : "gridDetector8"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to change password because the modify user is not exist"
    }
    ```

  - Error test case: original password is wrong

    Request body

    ```json
    {
    	"id": "1115614529362849792",
    	"username" : "2017890123",
    	"password" : "grid8",
    	"newPassword" : "gridDetector8"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to change password because the original password is wrong"
    }
    ```

  - Error test case: pass into wrong password over 5 times

    Request body

    ```json
    {
    	"id": "1115614529362849792",
    	"username" : "2017890123",
    	"password" : "grid8",
    	"newPassword" : "gridDetector8"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to change password because the account is locked with 15 minutes"
    }
    ```

  - Error test case: change password of a deactivated user

    Request body

    ```json
    {
    	"id": "1115604605748637696",
    	"username" : "2016456789",
    	"password" : "admin3",
    	"newPassword" : "admin"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to change password because the account is banned"
    }
    ```

#### Register

- Request character: supervisor

- Request method: POST

- Request path: http://localhost:8081/user/changePassword

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "tel" : "13866810017",
        "password": "testPassword",
        "name" : "John",
        "age" : 31,
        "sex" : "male",
        "province" : "Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ``` json
    {
        "code": "200",
        "data": "1118919141389914112",
        "message": "create supervisor successfully"
    }
    ```

  - Error test case: invalid telephone number passed in

    Request body

    ```json
    {
        "tel" : "138668100",
        "password": "testPassword",
        "name" : "John",
        "age" : 31,
        "sex" : "male",
        "province" : "Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to register because the tel is not valid"
    }
    ```

  - Error test case: invalid age value passed in

    Request body

    ```json
    {
        "tel" : "13866810017",
        "password": "testPassword",
        "name" : "John",
        "age" : -1,
        "sex" : "male",
        "province" : "Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to register because the age is not valid"
    }
    ```

  - Error test case: create an existed supervisor

    Request body

    ```json
    {
        "tel" : "13866810017",
        "password": "testPassword",
        "name" : "John",
        "age" : 31,
        "sex" : "male",
        "province" : "Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to register because the username is exist"
    }
    ```
  
  - Error test case: create supervisor with a non-existed city
  
    Request body
  
    ```json
    {
        "tel" : "13866810017",
        "password": "testPassword",
        "name" : "John",
        "age" : 31,
        "sex" : "male",
        "province" : "Bejiing",
        "city": "Beijing"
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "the selected city is not exist"
    }
    ```

### Administrator Controller

#### Add administrator

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8081/hide/administrator/addAdministrator

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "idCard": "2021678910",
        "name" : "NPC",
        "password" : "NPCpass"
    }
    ```
    
    Response body
    
    ```json
    {
        "code": "200",
        "data": "1119087906534715392",
        "message": "add administrator successfully"
    }
    ```
  
  - Error test case: pass into error id card number
  
    Request body
  
    ```json
    {
        "idCard": "202167891",
        "name" : "NPC",
        "password" : "NPCpass"
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "idCard is not correct"
    }
    ```
  
  - Error test case: request to create an existed user
  
    Request body
  
    ```json
    {
        "idCard": "2021678910",
        "name" : "NPC",
        "password" : "NPCpass"
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "this person is already exist in the system"
    }
    ```

#### Modify administrator

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8081/hide/administrator/modifyAdministrator

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1115604128331984896",
        "idCard": "2021678910",
        "name" : "NPC",
        "password" : "admin1"
    }
    ```
    
    Response body
    
    ```json
    {
        "code": "200",
        "data": null,
        "message": "modify administrator successfully"
    }
    ```
  
  - Error test case: modify the password
  
    Request body
  
    ```json
    {
        "id" : "1115604128331984896",
        "idCard": "2021678910",
        "name" : "NPC",
        "password" : "admin"
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "The modification of password is forbidden"
    }
    ```
  
  - Error test case: pass into error id card number
  
    Request body
  
    ```json
    {
        "id" : "1115604128331984896",
        "idCard": "202167891",
        "name" : "NPC",
        "password" : "NPCpass"
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "idCard is not correct"
    }
    ```
  
  - Error test case: modify non-existed administrator
  
    Request body
  
    ``` json
    {
        "id" : "1119087906534715316",
        "idCard": "202167891",
        "name" : "NPC",
        "password" : "NPCpass"
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "The modified administrator is not exist"
    }
    ```

#### Delete administrator

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8081/hide/administrator/deleteAdministrator

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id": "1115604994338320384",
        "password": "admin9"
    }
    ```
    
    Response body
    
    ```json
    {
        "code": "200",
        "data": null,
        "message": "delete administrator account successfully"
    }
    ```
  
  - Error test case: delete a non-existed administrator
  
    Request body
  
    ```json
    {
        "id": "1115604994338320384",
        "password": "admin9"
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to The deleted administrator is not exist"
    }
    ```
  
  - Error test case: delete an administrator with wrong password
  
    Request body
  
    ```json
    {
        "id": "1115604864553971712",
        "password": "admin9"
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to The password is wrong"
    }
    ```

#### Query administrator list

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8081/hide/administrator/queryAdministratorList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "pageSize": 5,
        "pageNum": 1,
        "id": "",
        "idCard": "2019",
        "name" : ""
    }
    ```
    
    Response body
    
    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115604677030834176",
                "idCard": "2019654321",
                "name": "Tamura Rin"
            }
        ],
        "message": "querysuccessfully"
    }
    ```
  
  - Error test case: invalid page number or page size
  
    Request body
  
    ```json
    {
        "pageSize": 0,
        "pageNum": 1,
        "id": "",
        "idCard": "2019",
        "name" : ""
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "pageNum and pageSize must be greater than 0"
    }
    ```

### Grid Detector Controller

#### Add grid detector

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8081/hide/gridDetector/addGridDetector

- Test cases

  - Correct test case

    Request body

    ```json
    {  
        "password": "testPassword",
        "role": "GridDetector",
        "name": "testDetector",
        "idCard": "2015567890",
        "province":"Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": "1119159800105529344",
        "message": "create grid detector successfully"
    }
    ```

  - Error test case: pass into error id card number

    Request body

    ```json
    {  
        "password": "testPassword",
        "role": "GridDetector",
        "name": "testDetector",
        "idCard": "201557890",
        "province":"Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "idCard is not correct"
    }
    ```

  - Error test case: request to create an existed user

    Request body

    ```json
    {  
        "password": "testPassword",
        "role": "GridDetector",
        "name": "testDetector",
        "idCard": "2014567890",
        "province":"Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "this person is already exist in the system"
    }
    ```

  - Error test case: select a non-existed city when create grid detector

    Request body

    ```json
    {  
        "password": "testPassword",
        "role": "GridDetector",
        "name": "testDetector",
        "idCard": "2014577890",
        "province":"Beiijng",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "the selected city is not exist"
    }
    ```

#### Modify grid detector

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8081/hide/gridDetector/modifyGridDetector

- Test cases

  - Correct test case

    Request body

    ```json
    {  
        "id" : "1115614568881582080",
        "password": "grid9",
        "role": "GridDetector",
        "name": "testDetector",
        "idCard": "2014567890",
        "province":"Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "modify successfully"
    }
    ```

  - Error test case: modify the password

    Request body

    ```json
    {  
        "id" : "1115614529362849792",
        "password": "testPassword",
        "role": "GridDetector",
        "name": "testDetector",
        "idCard": "2014577890",
        "province":"Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "The modification of password is forbidden"
    }
    ```

  - Error test case: pass into error id card number

    Request body

    ```json
    {  
        "id" : "1115614568881582080",
        "password": "grid9",
        "role": "GridDetector",
        "name": "testDetector",
        "idCard": "201456789",
        "province":"Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "idCard is not correct"
    }
    ```

  - Error test case: modify non-existed grid detector

    Request body

    ``` json
    {  
        "id" : "111561456888158208",
        "password": "grid9",
        "role": "GridDetector",
        "name": "testDetector",
        "idCard": "2014567890",
        "province":"Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "The modified gridDetector is not exist"
    }
    ```

#### Delete grid detector

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8081/hide/gridDetector/deleteGridDetector

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1115614457866743808",
        "password" : "grid7"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "delete grid detector account successfully"
    }
    ```

  - Error test case: delete a non-existed administrator

    Request body

    ```json
    {
        "id" : "111561445786674380",
        "password" : "grid7"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "The deleted grid detector is not exist"
    }
    ```

  - Error test case: delete an grid detector with wrong password

    Request body

    ```json
    {
        "id" : "1115614457866743808",
        "password" : "grid"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "The password is wrong"
    }
    ```

#### Query grid detector list

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8081/hide/gridDetector/queryGridDetectorList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "pageSize": 5,
        "pageNum": 1,
        "id": "",
        "idCard": "2019",
        "name" : ""
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115614345065132032",
                "cityId": 5,
                "idCard": "2019432109",
                "name": "grid_test5"
            }
        ],
        "message": "query grid detector successfully"
    }
    ```

  - Error test case: invalid page number or page size

    Request body

    ```json
    {
        "pageSize": 0,
        "pageNum": 1,
        "id": "",
        "idCard": "2019",
        "name" : ""
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "pageNum and pageSize must be greater than 0"
    }
    ```

### Supervisor Controller

#### Add supervisor

- Request character: project tester
- Request method: POST
- Request path: http://localhost:8081/hide/supervisor/addSupervisor

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "tel" : "13866810017",
        "password": "testPassword",
        "name" : "John",
        "age" : 31,
        "sex" : "male",
        "province" : "Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ``` json
    {
        "code": "200",
        "data": "1118919141389914112",
        "message": "create supervisor successfully"
    }
    ```

  - Error test case: invalid telephone number passed in

    Request body

    ```json
    {
        "tel" : "138668100",
        "password": "testPassword",
        "name" : "John",
        "age" : 31,
        "sex" : "male",
        "province" : "Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to create supervisor because the tel is not valid"
    }
    ```

  - Error test case: invalid age value passed in

    Request body

    ```json
    {
        "tel" : "13866810017",
        "password": "testPassword",
        "name" : "John",
        "age" : -1,
        "sex" : "male",
        "province" : "Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to create supervisor because the age is not valid"
    }
    ```

  - Error test case: create an existed supervisor

    Request body

    ```json
    {
        "tel" : "13866810017",
        "password": "testPassword",
        "name" : "John",
        "age" : 31,
        "sex" : "male",
        "province" : "Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to create supervisor because the username is exist"
    }
    ```

  - Error test case: create supervisor with a non-existed city

    Request body

    ```json
    {
        "tel" : "13866810017",
        "password": "testPassword",
        "name" : "John",
        "age" : 31,
        "sex" : "male",
        "province" : "Bejiing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "the selected city is not exist"
    }
    ```

#### Modify supervisor

- Request character: project tester
- Request method: POST
- Request path: http://localhost:8081/hide/supervisor/modifySupervisor

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1115616037923975168",
        "tel" : "18901234567",
        "password": "super6",
        "name" : "John",
        "age" : 31,
        "sex" : "male",
        "province" : "Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ``` json
    {
        "code": "200",
        "data": null,
        "message": "modify supervisor account successfully"
    }
    ```

  - Error test case: invalid telephone number passed in

    Request body

    ```json
    {
        "id" : "1115616037923975168",
        "tel" : "1890123456",
        "password": "super6",
        "name" : "John",
        "age" : 31,
        "sex" : "male",
        "province" : "Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to modify supervisor because the tel is not valid"
    }
    ```

  - Error test case: invalid age value passed in

    Request body

    ```json
    {
        "id" : "1115616037923975168",
        "tel" : "18901234567",
        "password": "super6",
        "name" : "John",
        "age" : 1,
        "sex" : "male",
        "province" : "Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to modify supervisor because the age is not valid"
    }
    ```

  - Error test case: modify a non-existed supervisor

    Request body

    ```json
    {
        "id" : "111561603792397516",
        "tel" : "18901234567",
        "password": "super6",
        "name" : "John",
        "age" : 1,
        "sex" : "male",
        "province" : "Beijing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "the supervisor is not exist"
    }
    ```

  - Error test case: create supervisor with a non-existed city

    Request body

    ```json
    {
        "id" : "111561603792397516",
        "tel" : "13866810017",
        "password": "testPassword",
        "name" : "John",
        "age" : 31,
        "sex" : "male",
        "province" : "Bejiing",
        "city": "Beijing"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "the selected city is not exist"
    }
    ```

#### Delete supervisor

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8081/hide/supervisor/deleteSupervisor

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1115616213161996288",
        "password" : "super10"
    }
    ```
    
    Response body
    
    ```json
    {
        "code": "200",
        "data": null,
        "message": "delete supervisor account successfully"
    }
    ```
  
  - Error test case: delete non-existed supervisor
  
    Request body
  
    ```json
    {
        "id" : "111561621316199628",
        "password" : "super10"
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to delete supervisor for the deleted supervisor is not exist"
    }
    ```
  
  - Error test case: pass into error password
  
    Request body
  
    ```json
    {
        "id" : "1115616172422721536",
        "password" : "super"
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to delete supervisor for the password is wrong"
    }
    ```

#### Query supervisor list

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8081/hide/supervisor/querySupervisorList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "pageNum": 1,
        "pageSize": 5,
        "id" : "1115616117519282176",
        "tel" : "",
        "name" : "",
        "province" : "Hubei",
        "city": "Wuhan"
    }
    ```
    
    Response body
    
    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115616117519282176",
                "tel": "14512345678",
                "name": "super8",
                "birthYear": 1993,
                "sex": 0,
                "cityId": 8
            }
        ],
        "message": "query successfully"
    }
    ```
    
    Request body
    
    ```json
    {
        "pageNum": 1,
        "pageSize": 5,
        "province" : "Guangdong"
    }
    ```
    
    Response body
    
    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115615900787011584",
                "tel": "13023456789",
                "name": "super3",
                "birthYear": 1999,
                "sex": 1,
                "cityId": 3
            },
            {
                "id": "1115615978398412800",
                "tel": "18655555555",
                "name": "super5",
                "birthYear": 2001,
                "sex": 1,
                "cityId": 5
            }
        ],
        "message": "query successfully"
    }
    ```
    
    Request body
    
    ```json
    {
        "pageNum": 1,
        "pageSize": 5,
        "province" : "Guangdong",
        "city" : "Shenzhen"
    }
    ```
    
    Response body
    
    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115615900787011584",
                "tel": "13023456789",
                "name": "super3",
                "birthYear": 1999,
                "sex": 1,
                "cityId": 3
            }
        ],
        "message": "query successfully"
    }
    ```
    
    
  
  - Error test case: use error city information
  
    Request body
  
    ```json
    {
        "pageNum": 1,
        "pageSize": 5,
        "province" : "Guangdong",
        "city": "Wuhan"
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "the selected city is not exist"
    }
    ```

## Report Management Module

### Report API

#### Get report by id

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8082/api/report/getReportById

- Test cases

  - Correct test case

    Request body

    ```json
    1115675276176519168
    ```

    Response body

    ```json
    {
        "id": "1115675276176519168",
        "submitterId": "1115615978398412800",
        "status": 1,
        "createdTime": "2024-03-14T14:28:06",
        "description": "test description",
        "imageUrl": "",
        "cityId": 1,
        "location": "",
        "forecastAqiLevel": 1
    }
    ```

  - Error test case

    Request body

    ```json
    1115675276176
    ```

    Response body

    ```json
    null
    ```

#### Update report by id

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8082/api/report/updateReportById

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1116980678797291521",
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "status" : 1,
        "createdTime" : "2024-03-14 13:14:45",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```

    Response body

    ```json
    true
    ```

### Action Controller

#### Report

- Request character: supervisor

- Request method: POST

- Request path: http://localhost:8082/report/supervisor/report

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "create report successfully"
    }
    ```

  - Error test case: use non-existed supervisor account to report

    Request body

    ```json
    {
        "submitterId" : "11156158271895596",
        "description" : "test Dscription",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "reported submitter is not exist"
    }
    ```

  - Error test case: report air condition of not supported city

    Request body

    ```json
    {
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "reported location is not exist"
    }
    ```

  - Error test case: report invalid AQI level

    Request body

    ``` json
    {
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 0
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "report forecastAqiLevel is not exist"
    }
    ```

  - Error test case: pass into invalid page number or page size

    Request body

    ```json
    {
        "submitterId" : "1115615978398412800",
        "id" : "",
        "status" : 1,
        "description" : "",
        "location" : "",
        "forecastApiLevel" : 1,
        "startTime" : "2024-01-14",
        "pageNum" : 0,
        "pageSize" : 5
     }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "pageNum or pageSize is not valid"
    }
    ```


#### Query report list

- Request character: administrator

- Request method: POST

- Request path: http://localhost:8082/report/administrator/queryReportList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "submitterId" : "",
        "id" : "",
        "status" : 1,
        "description" : "",
        "location" : "",
        "forecastApiLevel" : 1,
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115675276176519168",
                "submitterId": "1115615978398412800",
                "status": 1,
                "createdTime": "2024-03-14T14:28:06",
                "description": "test description",
                "province": "Shanghai",
                "city": "Shanghai",
                "location": "",
                "forecastAqiLevel": 1
            },
            {
                "id": "1115675276176519169",
                "submitterId": "1115615900787011584",
                "status": 1,
                "createdTime": "2024-03-15T13:40:43",
                "description": "test description",
                "province": "Beijing",
                "city": "Beijing",
                "location": "",
                "forecastAqiLevel": 2
            },
            {
                "id": "1115675276176519170",
                "submitterId": "1115615900787011584",
                "status": 1,
                "createdTime": "2024-04-25T14:04:02",
                "description": "test description",
                "province": "Guangdong",
                "city": "Shenzhen",
                "location": "",
                "forecastAqiLevel": 3
            },
            {
                "id": "1115675276176519171",
                "submitterId": "1115615827189559296",
                "status": 1,
                "createdTime": "2024-03-08T15:16:11",
                "description": "test description",
                "province": "Chongqing",
                "city": "Chongqing",
                "location": "",
                "forecastAqiLevel": 4
            },
            {
                "id": "1115675276176519172",
                "submitterId": "1115615827189559296",
                "status": 1,
                "createdTime": "2024-03-16T14:08:45",
                "description": "test description",
                "province": "Guangdong",
                "city": "Guangzhou",
                "location": "",
                "forecastAqiLevel": 5
            }
        ],
        "message": "querysuccessfully"
    }
    ```

    Request body

    ```json
    {
        "submitterId" : "1115615978398412800",
        "id" : "",
        "status" : 1,
        "description" : "",
        "location" : "",
        "forecastApiLevel" : 1,
        "startTime" : "2024-01-14",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115675276176519168",
                "submitterId": "1115615978398412800",
                "status": 1,
                "createdTime": "2024-03-14T14:28:06",
                "description": "test description",
                "province": "Shanghai",
                "city": "Shanghai",
                "location": "",
                "forecastAqiLevel": 1
            }
        ],
        "message": "query successfully"
    }
    ```

  - Error test case: pass into a non-existed city

    Request body

    ```json
    {
        "submitterId" : "1115615978398412800",
        "id" : "",
        "status" : 1,
        "description" : "",
        "province" : "Shanhai",
        "city": "Shanghai",
        "location" : "",
        "forecastApiLevel" : 1,
        "startTime" : "2023-03-15",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "the selected city is not exist"
    }
    ```

  - Error test case: pass into invalid page number or page size

    Request body

    ```json
    {
        "submitterId" : "1115615978398412800",
        "id" : "",
        "status" : 1,
        "description" : "",
        "location" : "",
        "forecastApiLevel" : 1,
        "startTime" : "2024-01-14",
        "pageNum" : 0,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "pageNum or pageSize is not valid"
    }
    ```


#### Query report list by submitter id

- Request character: supervisor

- Request method: POST

- Request path: http://localhost:8082/report/supervisor/queryReportList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "submitterId" : "1115615978398412800",
        "id" : "",
        "status" : 1,
        "description" : "",
        "location" : "",
        "forecastApiLevel" : 1,
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115675276176519168",
                "submitterId": "1115615978398412800",
                "status": 1,
                "createdTime": "2024-03-14T14:28:06",
                "description": "test description",
                "province": "Shanghai",
                "city": "Shanghai",
                "location": "",
                "forecastAqiLevel": 1
            }
        ],
        "message": "query successfully"
    }
    ```

    Request body

    ```json
    {
        "submitterId" : "1115615978398412800",
        "id" : "",
        "status" : 1,
        "description" : "",
        "location" : "",
        "forecastApiLevel" : 1,
        "startTime" : "2024-01-14",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115675276176519168",
                "submitterId": "1115615978398412800",
                "status": 1,
                "createdTime": "2024-03-14T14:28:06",
                "description": "test description",
                "province": "Shanghai",
                "city": "Shanghai",
                "location": "",
                "forecastAqiLevel": 1
            }
        ],
        "message": "query successfully"
    }
    ```

  - Error test case: pass into a non-existed city

    Request body

    ```json
    {
        "submitterId" : "1115615978398412800",
        "id" : "",
        "status" : 1,
        "description" : "",
        "province" : "Shanhai",
        "city": "Shanghai",
        "location" : "",
        "forecastApiLevel" : 1,
        "startTime" : "2023-03-15",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "the selected city is not exist"
    }
    ```

#### Get report by id

- Request character: grid detector

- Request method: POST

- Request path: http://localhost:8082/report/gridDetector/getReportById

- Test cases

  - Correct test case

    Request body

    ```json
    1115675276176519168
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": {
            "id": "1115675276176519168",
            "submitterId": "1115615978398412800",
            "status": 1,
            "createdTime": "2024-03-14 14:28:06",
            "description": "test description",
            "imageUrl": "",
            "cityId": 1,
            "location": "test location",
            "forecastAqiLevel": 1
        },
        "message": "get reportsuccessfully"
    }
    ```

  - Error test case

    Request body

    ```json
    1
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "report is not exist"
    }
    ```

### Report Controller

#### Add report

- Request character: project tester
- Request method: POST
- Request path: http://localhost:8082/hide/report/addReport

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "create report successfully"
    }
    ```

  - Error test case: use non-existed supervisor account to report

    Request body

    ```json
    {
        "submitterId" : "11156158271895596",
        "description" : "test Dscription",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "reported submitter is not exist"
    }
    ```

  - Error test case: report air condition of not supported city

    Request body

    ```json
    {
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "reported location is not exist"
    }
    ```

  - Error test case: report invalid AQI level

    Request body

    ``` json
    {
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 0
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "report forecastAqiLevel is not exist"
    }
    ```

  - Error test case: pass into invalid page number or page size

    Request body

    ```json
    {
        "submitterId" : "1115615978398412800",
        "id" : "",
        "status" : 1,
        "description" : "",
        "location" : "",
        "forecastApiLevel" : 1,
        "startTime" : "2024-01-14",
        "pageNum" : 0,
        "pageSize" : 5
     }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "pageNum or pageSize is not valid"
    }
    ```

#### Modify report

- Request character: project tester

- Request method: POST

-  Request path: http://localhost:8082/hide/report/modifyReport

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1116980678797291521",
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "status" : 1,
        "createdTime" : "2024-03-14",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```
    
    Response body
    
    ```json
    {
        "code": "200",
        "data": {
            "id": "1116980678797291521",
            "submitterId": "1115615827189559296",
            "status": 1,
            "createdTime": "2024-03-14 00:00:00",
            "description": "test Dscription",
            "imageUrl": null,
            "cityId": 2,
            "location": "test location",
            "forecastAqiLevel": 1
        },
        "message": "modify report successfully"
    }
    ```
    
    Response body
    
    ```json
    {
        "id" : "1116980678797291521",
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "status" : 1,
        "createdTime" : "2024-03-14 13:14:45",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```
    
    Response body
    
    ```json
    {
        "code": "200",
        "data": {
            "id": "1116980678797291521",
            "submitterId": "1115615827189559296",
            "status": 1,
            "createdTime": "2024-03-14 13:14:45",
            "description": "test Dscription",
            "imageUrl": null,
            "cityId": 2,
            "location": "test location",
            "forecastAqiLevel": 1
        },
        "message": "modify report successfully"
    }
    ```
  
  - Error test case: invalid data format
  
    Request body
  
    ```json
    {
        "id" : "1116980678797291521",
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "status" : 1,
        "createdTime" : "2024-03-14 13:1",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "error time format"
    }
    ```
  
  - Error test case
  
    Request body
  
    ```json
    {
        "id" : "111698067879729152=1",
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "status" : 1,
        "createdTime" : "2024-03-14",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "modified report is not exist"
    }
    ```
  
  - Error test case: pass into a non-existed supervisor
  
    Request body
  
    ``` json
    {
        "id" : "1116980678797291521",
        "submitterId" : "111561582718955926",
        "description" : "test Dscription",
        "status" : 1,
        "createdTime" : "2024-03-14",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "reported submitter is not exist"
    }
    ```
  
  - Error test case: pass into an invalid AQI level
  
    Request body
  
    ```json
    {
        "id" : "1116980678797291521",
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "status" : 1,
        "createdTime" : "2024-03-14",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 0
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "reported forecast AQI level is not exist"
    }
    ```
  
  - Error test case: pass into a non-supported city location
  
    Request body
  
    ``` json
    {
        "id" : "1116980678797291521",
        "submitterId" : "1115615827189559296",
        "description" : "test Dscription",
        "status" : 1,
        "createdTime" : "2024-03-14",
        "province" : "Beiijng",
        "city" : "Beijing",
        "location" : "test location",
        "forecastAQILevel": 1
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "reported location is not exist"
    }
    ```

#### Delete report

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8082/hide/report/deleteReport

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1116986091378831360"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": {
            "id": "1116986091378831360",
            "submitterId": null,
            "status": null,
            "createdTime": null,
            "description": null,
            "imageUrl": null,
            "cityId": null,
            "location": null,
            "forecastAqiLevel": null
        },
        "message": "delete report successfully"
    }
    ```

  - Error test case: delete a non-existed report

    Request body

    ```json
    {
        "id" : "111698609137883136"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "deleted report is not exist"
    }
    ```

#### Query report list

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8082/hide/report/queryReportList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "submitterId" : "",
        "id" : "",
        "status" : 1,
        "description" : "",
        "location" : "",
        "forecastApiLevel" : 1,
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115675276176519168",
                "submitterId": "1115615978398412800",
                "status": 1,
                "createdTime": "2024-03-14T14:28:06",
                "description": "test description",
                "province": "Shanghai",
                "city": "Shanghai",
                "location": "",
                "forecastAqiLevel": 1
            },
            {
                "id": "1115675276176519169",
                "submitterId": "1115615900787011584",
                "status": 1,
                "createdTime": "2024-03-15T13:40:43",
                "description": "test description",
                "province": "Beijing",
                "city": "Beijing",
                "location": "",
                "forecastAqiLevel": 2
            },
            {
                "id": "1115675276176519170",
                "submitterId": "1115615900787011584",
                "status": 1,
                "createdTime": "2024-04-25T14:04:02",
                "description": "test description",
                "province": "Guangdong",
                "city": "Shenzhen",
                "location": "",
                "forecastAqiLevel": 3
            },
            {
                "id": "1115675276176519171",
                "submitterId": "1115615827189559296",
                "status": 1,
                "createdTime": "2024-03-08T15:16:11",
                "description": "test description",
                "province": "Chongqing",
                "city": "Chongqing",
                "location": "",
                "forecastAqiLevel": 4
            },
            {
                "id": "1115675276176519172",
                "submitterId": "1115615827189559296",
                "status": 1,
                "createdTime": "2024-03-16T14:08:45",
                "description": "test description",
                "province": "Guangdong",
                "city": "Guangzhou",
                "location": "",
                "forecastAqiLevel": 5
            }
        ],
        "message": "querysuccessfully"
    }
    ```

    Request body

    ```json
    {
        "submitterId" : "1115615978398412800",
        "id" : "",
        "status" : 1,
        "description" : "",
        "location" : "",
        "forecastApiLevel" : 1,
        "startTime" : "2024-01-14",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115675276176519168",
                "submitterId": "1115615978398412800",
                "status": 1,
                "createdTime": "2024-03-14T14:28:06",
                "description": "test description",
                "province": "Shanghai",
                "city": "Shanghai",
                "location": "",
                "forecastAqiLevel": 1
            }
        ],
        "message": "query successfully"
    }
    ```

  - Error test case: pass into a non-existed city

    Request body

    ```json
    {
        "submitterId" : "1115615978398412800",
        "id" : "",
        "status" : 1,
        "description" : "",
        "province" : "Shanhai",
        "city": "Shanghai",
        "location" : "",
        "forecastApiLevel" : 1,
        "startTime" : "2023-03-15",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "the selected city is not exist"
    }
    ```

  - Error test case: pass into invalid page number or page size

    Request body

    ```json
    {
        "submitterId" : "1115615978398412800",
        "id" : "",
        "status" : 1,
        "description" : "",
        "location" : "",
        "forecastApiLevel" : 1,
        "startTime" : "2024-01-14",
        "pageNum" : 0,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "pageNum or pageSize is not valid"
    }
    ```


## Submission Management Module

### Action Controller

#### Submit

- Request character: grid detector

- Request method: POST

- Request path: http://localhost:8083/submission/gridDetector/submit

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "taskId" : "1115676903893626884",
        "submitterId" : "1116545704042319872",
        "description" : "test description",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : 90,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "create submission successfully"
    }
    ```

  - Error test case: submit to a non-existed task

    Request body

    ```json
    {
        "taskId" : "1",
        "submitterId" : "1116545704042319872",
        "description" : "test description",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : 90,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "task not exist"
    }
    ```

  - Error test case: submit to a finished task

    Request body

    ```json
    {
        "taskId" : "1115676903893626882",
        "submitterId" : "1116545704042319872",
        "description" : "test description",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : 90,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "task is finished"
    }
    ```

  - Error test case: use a non-existed grid detector account to submit

    Request body

    ```json
    {
        "taskId" : "1115676903893626884",
        "submitterId" : "111654570404231",
        "description" : "test description",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : 90,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "submitter not exist"
    }
    ```

  - Error test case: submit with a non-supported city

    Request body

    ```json
    {
        "taskId" : "1115676903893626884",
        "submitterId" : "1116545704042319872",
        "description" : "test description",
        "province" : "Beiijng",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : 90,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "city not exist"
    }
    ```

  - Error test case: invalid air condition information input

    Request body

    ```json
    {
        "taskId" : "1115676903893626883",
        "submitterId" : "1116545704042319872",
        "description" : "test description",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : -1,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to create submission "
    }
    ```

#### Query submission list(grid detector)

- Request character: grid detector

- Request method: POST

- Request path: http://localhost:8083/submission/gridDetector/querySubmissionList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1115676903893626886",
        "taskId" : "1115676903893626880",
        "description" : "test",
        "startTime" : "2024-03-01",
        "endTime" : "2024-03-18",
        "submitterId" : "1115613655483805696",
        "administratorId" : "1115604128331984896",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115676903893626886",
                "taskId": "1115676903893626880",
                "submitterId": "1115613655483805696",
                "description": "test descrption",
                "relatedAirDataId": "1115664407392677898",
                "imageUrl": null,
                "submittedTime": "2024-03-16 11:24:28"
            }
        ],
        "message": "query submissionsuccessfully"
    }
    ```

  - Error test case: invalid date input

    Request body

    ```json
    {
        "id" : "1115676903893626886",
        "taskId" : "1115676903893626880",
        "description" : "test",
        "startTime" : "2024-03-01",
        "endTime" : "2024-18",
        "submitterId" : "1115613655483805696",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "Unparseable date: \"2024-18\""
    }
    ```

  - Error test case: invalid page number or page size input

    Request body

    ```json
    {
        "id" : "1115676903893626886",
        "taskId" : "1115676903893626880",
        "description" : "test",
        "startTime" : "2024-03-01",
        "endTime" : "2024-03-18",
        "submitterId" : "1115613655483805696",
        "pageNum" : 1,
        "pageSize" : 0
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "pageNum or pageSize is invalid"
    }
    ```

#### Query submission list(administrator)

- Request character: administrator

- Request method: POST

- Request path: http://localhost:8083/submission/administrator/querySubmissionList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1115676903893626886",
        "taskId" : "1115676903893626880",
        "description" : "test",
        "startTime" : "2024-03-01",
        "endTime" : "2024-03-17",
        "submitterId" : "1115613655483805696",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115676903893626886",
                "taskId": "1115676903893626880",
                "submitterId": "1115613655483805696",
                "description": "test descrption",
                "relatedAirDataId": "1115664407392677898",
                "imageUrl": null,
                "submittedTime": "2024-03-16 11:24:28"
            }
        ],
        "message": "query submissionsuccessfully"
    }
    ```

  - Error test case: invalid date input

    Request body

    ```json
    {
        "id" : "1115676903893626886",
        "taskId" : "1115676903893626880",
        "description" : "test",
        "startTime" : "2024-03-01",
        "endTime" : "2024-18",
        "submitterId" : "1115613655483805696",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "Unparseable date: \"2024-18\""
    }
    ```

  - Error test case: invalid page number or page size input

    Request body

    ```json
    {
        "id" : "1115676903893626886",
        "taskId" : "1115676903893626880",
        "description" : "test",
        "startTime" : "2024-03-01",
        "endTime" : "2024-03-18",
        "submitterId" : "1115613655483805696",
        "pageNum" : 1,
        "pageSize" : 0
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "pageNum or pageSize is invalid"
    }
    ```

### Submission

### Submission Controller

#### Add submission

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8083/hide/submission/addSubmission

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "taskId" : "1115676903893626884",
        "submitterId" : "1116545704042319872",
        "description" : "test description",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : 90,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "create submission successfully"
    }
    ```

  - Error test case: submit to a non-existed task

    Request body

    ```json
    {
        "taskId" : "1",
        "submitterId" : "1116545704042319872",
        "description" : "test description",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : 90,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "task not exist"
    }
    ```

  - Error test case: submit to a finished task

    Request body

    ```json
    {
        "taskId" : "1115676903893626882",
        "submitterId" : "1116545704042319872",
        "description" : "test description",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : 90,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "task is finished"
    }
    ```

  - Error test case: use a non-existed grid detector account to submit

    Request body

    ```json
    {
        "taskId" : "1115676903893626884",
        "submitterId" : "111654570404231",
        "description" : "test description",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : 90,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "submitter not exist"
    }
    ```

  - Error test case: submit with a non-supported city

    Request body

    ```json
    {
        "taskId" : "1115676903893626884",
        "submitterId" : "1116545704042319872",
        "description" : "test description",
        "province" : "Beiijng",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : 90,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "city not exist"
    }
    ```

  - Error test case: invalid air condition information input

    Request body

    ```json
    {
        "taskId" : "1115676903893626883",
        "submitterId" : "1116545704042319872",
        "description" : "test description",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : -1,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```
    
    Response body
    
    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to create submission "
    }
    ```

#### Modify submission

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8083/hide/submission/modifySubmission

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1802516610955636737",
        "taskId" : "1115676903893626883",
        "submitterId" : "1116545704042319872",
        "description" : "test description",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : 13,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "modify successfully"
    }
    ```

  - Error test case: modify a non-existed submission

    Request body

    ```json
    {
        "id" : "18025166109556",
        "taskId" : "1115676903893626883",
        "submitterId" : "1116545704042319872",
        "description" : "test description",
        "province" : "Beijing",
        "city" : "Beijing",
        "location" : "test location", 
        "pm25" : 149,
        "pm10" : 13,
        "so2" : 2,
        "no2" : 18,
        "co" : 6,
        "o3" : 39
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "modifyed submission not exist"
    }
    ```

#### Delete Submission

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8083/hide/submission/deleteSubmission

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1115676903893626888"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "delete successfully"
    }
    ```

  - Error test case: delete a non-existed submission

    Request body

    ```json
    {
        "id" : "1115676903893626"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "deleted submission not exist"
    }
    ```

#### Query submission list

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8083/hide/submission/querySubmissionList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "pageNum" : 1,
        "pageSize" : 5,
        "submitterId" : "",
        "id" : "111567690389",
        "taskId" : "",
        "description" : "",
        "relatedAirDataId" : "",
        "startTime" : "",
        "endTime" : ""
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115676903893626886",
                "taskId": "1115676903893626880",
                "submitterId": "1115613655483805696",
                "description": "test descrption",
                "relatedAirDataId": "1115664407392677898",
                "imageUrl": null,
                "submittedTime": "2024-03-16 11:24:28"
            },
            {
                "id": "1115676903893626887",
                "taskId": "1115676903893626881",
                "submitterId": "1115614307672911872",
                "description": "test descrption",
                "relatedAirDataId": "1115664407396872193",
                "imageUrl": null,
                "submittedTime": "2024-03-17 10:11:35"
            }
        ],
        "message": "querysuccessfully"
    }
    ```

## Task Management Module

### Task API

#### Get task by id

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8084/api/task/getReportById

- Test cases

  - Correct test case

    Request body

    ```json
    1115676903893626880
    ```
    
    Response body
    
    ```json
    {
        "id": "1115676903893626880",
        "appointerId": "1115604128331984896",
        "appointeeId": "1115615978398412800",
        "createdTime": "2024-03-15T11:28:06",
        "status": 1,
        "relativeReportId": "1115675276176519168"
    }
    ```
  
  - Error test case: pass into a non-existed task id
  
    Request body
  
    ``` json
    1
    ```
  
    Response body
  
    ```json
    null
    ```

#### Update task by id

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8084/api/task/updateTaskById

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1115676903893626883",
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582080",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    true
    ```

  - Error test case: modify a non-existed task

    Request body

    ```json
    {
        "id" : "11156769038936263",
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582080",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    false
    ```

  - Error test case: using a non-existed administrator

    Request body

    ```json
    {
        "appointerId" : "111560474379578",
        "appointeeId" : "1115614529362849792",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    false
    ```

  - Error test case: appointing a non-existed grid detector

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    false
    ```

  - Error test case: appoint a non-existed report

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582",
        "relativeReportId" : "11156752761765"
    }
    ```

    Response body

    ```json
    false
    ```

  - Error test case: appoint an appointer report

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614529362849792",
        "relativeReportId" : "1115675276176519168"
    }
    ```

    Response body

    ```json
    false
    ```

#### Get task id by appointer id

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8084/api/task/getTaskIdByAppointerId

- Test cases

  - Correct test case

    Request body

    ```json
    1115604511875919872
    ```

    Response body

    ```json
    [
        "1115676903893626881"
    ]
    ```

  - Error test case

    Request body

    ```json
    1115604511875919
    ```

    Response body

    ```json
    null
    ```

### Action Controller

#### Get the local grid detector

- Request character: administrator

- Request method: POST

- Request path: http://localhost:8084/task/administrator/getAppointee/local

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "province" : "Beijing",
        "city" : "Beijing",
        "pageNum" : 1,
        "pageSize" :5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115613655483805696",
                "cityId": 2,
                "idCard": "2021345678",
                "name": "grid_test2"
            },
            {
                "id": "1116415903470080000",
                "cityId": 2,
                "idCard": "2012567890",
                "name": "testDetector"
            },
            {
                "id": "1116545704042319872",
                "cityId": 2,
                "idCard": "2012567890",
                "name": "testDetector"
            },
            {
                "id": "1119159800105529344",
                "cityId": 2,
                "idCard": "2015567890",
                "name": "testDetector"
            }
        ],
        "message": "get appointeesuccessfully"
    }
    ```

  - Error test case: require the grid detector of non supported city

    Request body

    ```json
    {
        "province" : "Beijing",
        "city" : "Beiing",
        "pageNum" : 1,
        "pageSize" :5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "request city is not supported"
    }
    ```

  - Error test case: invalid page number or page size input

    Request body

    ```json
    {
        "province" : "Beijing",
        "city" : "Beijing",
        "pageNum" : 1,
        "pageSize" : 0
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "The page size and the page number should be positive"
    }
    ```

#### Get the appointee in the same province

- Request character: administrator

- Request method: POST

- Request path: http://localhost:8084/administrator/getAppointee/province

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "province" : "Guangdong",
        "city" : "Shenzhen",
        "pageSize" : 5,
        "pageNum" : 1
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115614345065132032",
                "cityId": 5,
                "idCard": "2019432109",
                "name": "grid_test5"
            },
            {
                "id": "1115614529362849792",
                "cityId": 9,
                "idCard": "2017890123",
                "name": "grid_test8"
            }
        ],
        "message": "get appointee successfully"
    }
    ```

  - Error test case: the request province has no other supported city

    Request body

    ```json
    {
        "province" : "Beijing",
        "city" : "Beijing",
        "pageSize" : 5,
        "pageNum" : 1
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "request province has no other supported city"
    }
    ```

  - Error test case: use invalid page size or page number

    Request body

    ```json
    {
        "province" : "Guangdong",
        "city" : "Shenzhen",
        "pageSize" : 5,
        "pageNum" : 0
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "The page size and the page number should be positive"
    }
    ```

  - Error test case: request a non-existed city

    Request body

    ```json
    {
        "province" : "Guangdong",
        "city" : "Shenzhe",
        "pageSize" : 5,
        "pageNum" : 1
    }
    ```

    Response body

    ````json
    {
        "code": "0",
        "data": null,
        "message": "request city is not supported"
    }
    ````

#### Get  the appointee in the same province

- Request character: administrator

- Request method: POST

- Request path: http://localhost:8084/administrator/getAppointee/province

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "province" : "Beijing",
        "city" : "Beijing",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ````json
    {
        "code": "200",
        "data": [
            {
                "id": "1115613612534132736",
                "cityId": 1,
                "idCard": "2015876543",
                "name": "grid_test1"
            },
            {
                "id": "1115614307672911872",
                "cityId": 4,
                "idCard": "2012567890",
                "name": "grid_test4"
            },
            {
                "id": "1115614345065132032",
                "cityId": 5,
                "idCard": "2019432109",
                "name": "grid_test5"
            },
            {
                "id": "1115614529362849792",
                "cityId": 9,
                "idCard": "2017890123",
                "name": "grid_test8"
            },
            {
                "id": "1115614627866079232",
                "cityId": 7,
                "idCard": "2022098765",
                "name": "grid_test10"
            }
        ],
        "message": "get appointee successfully"
    }
    ````

  - Error test case: invalid page size and page number

    Request body

    ```json
    {
        "province" : "Beijing",
        "city" : "Beijing",
        "pageNum" : 1,
        "pageSize" : 0
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "The page size and the page number should be positive"
    }
    ```

  - Error test case: use not supported city

    Request body

    ```json
    {
        "province" : "Beijing",
        "city" : "Beiing",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "request city is not supported"
    }
    ```

#### Appoint

- Request character: administrator

- Request method: POST

- Request path: http://localhost:8084/task/administrator/appoint

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582080",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "create task successfully"
    }
    ```

  - Error test case: using a non-existed administrator

    Request body

    ```json
    {
        "appointerId" : "111560474379578",
        "appointeeId" : "1115614529362849792",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "administrator not found"
    }
    ```

  - Error test case: appointing a non-existed grid detector

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "grid detector not found"
    }
    ```

  - Error test case: appoint a non-existed report

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582",
        "relativeReportId" : "11156752761765"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "report not found"
    }
    ```

  - Error test case: appoint an appointer report

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614529362849792",
        "relativeReportId" : "1115675276176519168"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "report has been appointed"
    }
    ```

#### Query task list(grid detector)

-  Request character: grid detector
- Request method: POST
- Request path: http://localhost:8084/task/gridDetector/queryTaskList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1115676903893626880",
        "appointerId" : "1115604128331984896",
        "appointeeId" : "1115615978398412800",
        "status" : 1,
        "startTime" : "",
        "endTime" : "",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115676903893626880",
                "appointerId": "1115604128331984896",
                "appointee": "1115615978398412800",
                "createdTime": "2024-03-15T11:28:06",
                "status": 1,
                "province": "Shanghai",
                "city": "Shanghai",
                "location": "test location",
                "reportForecastAqiLevel": 1
            }
        ],
        "message": "query successfully"
    }
    ```

    Request body

    ```json
    {
        "id" : "1115676903893626880",
        "appointerId" : "1115604128331984896",
        "appointeeId" : "1115615978398412800",
        "status" : 1,
        "startTime" : "2024-03-01",
        "endTime" : "2024-03-16",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115676903893626880",
                "appointerId": "1115604128331984896",
                "appointee": "1115615978398412800",
                "createdTime": "2024-03-15T11:28:06",
                "status": 1,
                "province": "Shanghai",
                "city": "Shanghai",
                "location": "test location",
                "reportForecastAqiLevel": 1
            }
        ],
        "message": "querysuccessfully"
    }
    ```

  - Error test case: query the data without grid detector id

    Request body

    ```json
    {
        "status" : 1,
        "startTime" : "",
        "endTime" : "",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "appointeeId is required"
    }
    ```

  - Error test case: use invalid page number or page size

    Request body

    ```json
    {
        "id" : "1115676903893626880",
        "appointerId" : "1115604128331984896",
        "appointeeId" : "1115615978398412800",
        "status" : 1,
        "startTime" : "",
        "endTime" : "",
        "pageNum" : 1,
        "pageSize" : 0
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "The page size and the page number should be positive"
    }
    ```

  - Error test case: pass into invalid date

    Request body

    ```json
    {
        "id" : "1115676903893626880",
        "appointerId" : "1115604128331984896",
        "appointeeId" : "1115615978398412800",
        "status" : 1,
        "startTime" : "2024-0301",
        "endTime" : "2024-03-16",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "Unparseable date: \"2024-0301\""
    }
    ```

#### Query task list(administrator)

- Request character: administrator
- Request method: POST
- Request path: http://localhost:8084/task/administrator/queryTaskList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1115676903893626880",
        "appointerId" : "1115604128331984896",
        "appointeeId" : "1115615978398412800",
        "status" : 1,
        "startTime" : "",
        "endTime" : "",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115676903893626880",
                "appointerId": "1115604128331984896",
                "appointee": "1115615978398412800",
                "createdTime": "2024-03-15T11:28:06",
                "status": 1,
                "province": "Shanghai",
                "city": "Shanghai",
                "location": "test location",
                "reportForecastAqiLevel": 1
            }
        ],
        "message": "query successfully"
    }
    ```

    Request body

    ```json
    {
        "id" : "1115676903893626880",
        "appointerId" : "1115604128331984896",
        "appointeeId" : "1115615978398412800",
        "status" : 1,
        "startTime" : "2024-03-01",
        "endTime" : "2024-03-16",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115676903893626880",
                "appointerId": "1115604128331984896",
                "appointee": "1115615978398412800",
                "createdTime": "2024-03-15T11:28:06",
                "status": 1,
                "province": "Shanghai",
                "city": "Shanghai",
                "location": "test location",
                "reportForecastAqiLevel": 1
            }
        ],
        "message": "querysuccessfully"
    }
    ```

  - Error test case: query the data without administrator id

    Request body

    ```json
    {
        "status" : 1,
        "startTime" : "",
        "endTime" : "",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "appointerId is required"
    }
    ```

  - Error test case: use invalid page number or page size

    Request body

    ```json
    {
        "id" : "1115676903893626880",
        "appointerId" : "1115604128331984896",
        "appointeeId" : "1115615978398412800",
        "status" : 1,
        "startTime" : "",
        "endTime" : "",
        "pageNum" : 1,
        "pageSize" : 0
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "The page size and the page number should be positive"
    }
    ```

  - Error test case: pass into invalid date

    Request body

    ```json
    {
        "id" : "1115676903893626880",
        "appointerId" : "1115604128331984896",
        "appointeeId" : "1115615978398412800",
        "status" : 1,
        "startTime" : "2024-0301",
        "endTime" : "2024-03-16",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "Unparseable date: \"2024-0301\""
    }
    ```

### Task Controller

#### Add task

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8084/hide/task/addTask

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582080",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "create task successfully"
    }
    ```

  - Error test case: using a non-existed administrator

    Request body

    ```json
    {
        "appointerId" : "111560474379578",
        "appointeeId" : "1115614529362849792",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "administrator not found"
    }
    ```

  - Error test case: appointing a non-existed grid detector

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "grid detector not found"
    }
    ```

  - Error test case: appoint a non-existed report

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582",
        "relativeReportId" : "11156752761765"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "report not found"
    }
    ```

  - Error test case: appoint an appointer report

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614529362849792",
        "relativeReportId" : "1115675276176519168"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "report has been appointed"
    }
    ```

#### Modify task

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8084/hide/task/modifyTask

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1115676903893626883",
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582080",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "modify successfully"
    }
    ```

  - Error test case: modify a non-existed task

    Request body

    ```json
    {
        "id" : "11156769038936263",
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582080",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "task not found"
    }
    ```

  - Error test case: using a non-existed administrator

    Request body

    ```json
    {
        "appointerId" : "111560474379578",
        "appointeeId" : "1115614529362849792",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "administrator not found"
    }
    ```

  - Error test case: appointing a non-existed grid detector

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582",
        "relativeReportId" : "1115675276176519176"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "grid detector not found"
    }
    ```

  - Error test case: appoint a non-existed report

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614568881582",
        "relativeReportId" : "11156752761765"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "report not found"
    }
    ```

  - Error test case: appoint an appointer report

    Request body

    ```json
    {
        "appointerId" : "1115604743795765248",
        "appointeeId" : "1115614529362849792",
        "relativeReportId" : "1115675276176519168"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "report has been appointed"
    }
    ```

#### Delete task

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8084/hide/task/deleteTask

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id": "1117050544552144896"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "delete task recordsuccessfully"
    }
    ```

  - Error test case: delete a non-existed task

    Request body

    ```json
    {
        "id": "1"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "task not found"
    }
    ```

#### Query task list

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8084/hide/task/queryTaskList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : "1115676903893626880",
        "appointerId" : "1115604128331984896",
        "appointeeId" : "1115615978398412800",
        "status" : 1,
        "startTime" : "",
        "endTime" : "",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115676903893626880",
                "appointerId": "1115604128331984896",
                "appointee": "1115615978398412800",
                "createdTime": "2024-03-15T11:28:06",
                "status": 1,
                "province": "Shanghai",
                "city": "Shanghai",
                "location": "test location",
                "reportForecastAqiLevel": 1
            }
        ],
        "message": "query successfully"
    }
    ```
    
    Request body
    
    ```json
    {
        "id" : "1115676903893626880",
        "appointerId" : "1115604128331984896",
        "appointeeId" : "1115615978398412800",
        "status" : 1,
        "startTime" : "2024-03-01",
        "endTime" : "2024-03-16",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```
    
    Response body
    
    ```json
    {
        "code": "200",
        "data": [
            {
                "id": "1115676903893626880",
                "appointerId": "1115604128331984896",
                "appointee": "1115615978398412800",
                "createdTime": "2024-03-15T11:28:06",
                "status": 1,
                "province": "Shanghai",
                "city": "Shanghai",
                "location": "test location",
                "reportForecastAqiLevel": 1
            }
        ],
        "message": "querysuccessfully"
    }
    ```
  
  - Error test case: query the data without grid detector id
  
    Request body
  
    ```json
    {
        "status" : 1,
        "startTime" : "",
        "endTime" : "",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "appointeeId is required"
    }
    ```
  
  - Error test case: use invalid page number or page size
  
    Request body
  
    ```json
    {
        "id" : "1115676903893626880",
        "appointerId" : "1115604128331984896",
        "appointeeId" : "1115615978398412800",
        "status" : 1,
        "startTime" : "",
        "endTime" : "",
        "pageNum" : 1,
        "pageSize" : 0
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "The page size and the page number should be positive"
    }
    ```
  
  - Error test case: pass into invalid date
  
    Request body
  
    ```json
    {
        "id" : "1115676903893626880",
        "appointerId" : "1115604128331984896",
        "appointeeId" : "1115615978398412800",
        "status" : 1,
        "startTime" : "2024-0301",
        "endTime" : "2024-03-16",
        "pageNum" : 1,
        "pageSize" : 5
    }
    ```
  
    Response body
  
    ```json
    {
        "code": "0",
        "data": null,
        "message": "Unparseable date: \"2024-0301\""
    }
    ```

## Zone Management Module

### City API

#### Get city by id

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8086/api/city/getCityById

- Test cases

  - Correct test case

    request body

    ```json
    1
    ```

    response body

    ```json
    {
        "id": 1,
        "name": "Shanghai",
        "province": "Shanghai",
        "level": "megaCity"
    }
    ```

  - Error test case: get city by non-existed city id

    request body

    ```json
    1000
    ```

    response body

    ```json
    null
    ```

#### Get city by location

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8086/api/city/getCityById

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "province": "Beijing",
        "city" :"Beijing"
    }
    ```

    Response body

    ```json
    {
        "id": 2,
        "name": "Beijing",
        "province": "Beijing",
        "level": "megaCity"
    }
    ```

  - Error test case: error city information input

    Request body

    ```json
    {
        "province": "Beijnig",
        "city" :"Beijing"
    }
    ```

    Response body

    ```json
    null
    ```

#### Get cities in specific province

- Request character: inner micro-service module

- Request method: POST

- Request path: http://9096/api/city/getCitiesByProvince

- Test cases

  - Correct test case

    Request body

    ```json
    Shaanxi
    ```

    Response body

    ```json
    [
        10,
        71,
        127,
        133
    ]
    ```

  - Error test case: error province name input

    Request body

    ```json
    Shaandong
    ```

    Response body

    ```json
    []
    ```

#### Get other cities in same province

- Request character: inner micro-service module

- Request method: POST

- Request path: http://localhost:8086/api/city/getCitiesSameProvince

- Test cases

  - Correct test case

    Request body

    ```json
    10
    ```

    Response body

    ```json
    [
        71,
        127,
        133
    ]
    ```

    Request body

    ```json
    1
    ```

    Response body

    ```json
    []
    ```

  - Error test case: pass into a non-existed city id

    Request body

    ```json
    1000
    ```

    Response body

    ```json
    null
    ```

### Action Controller

#### Select all city

- Request character: all

- Request method: GET

- Request path: http://localhost:8086/city/selectAll

- Test case

  - Correct test case

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": 1,
                "name": "Shanghai",
                "province": "Shanghai",
                "level": "megaCity"
            },
            {
                "id": 2,
                "name": "Beijing",
                "province": "Beijing",
                "level": "megaCity"
            }
            ......
        ],
        "message": "query successfully"
    }
    ```

#### Select by province

- Request character: all

- Request method: POST

- Request path: http://localhost:8086/city/selectByProvince

- Test cases

  - Correct test case

    Request body

    ```json
    Shaanxi
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": 10,
                "name": "Xi'an",
                "province": "Shaanxi",
                "level": "majorCity"
            },
            {
                "id": 71,
                "name": "Xianyang",
                "province": "Shaanxi",
                "level": "bigCity"
            },
            {
                "id": 127,
                "name": "Baoji",
                "province": "Shaanxi",
                "level": "bigCity"
            },
            {
                "id": 133,
                "name": "Yulin",
                "province": "Shaanxi",
                "level": "mediunCity"
            }
        ],
        "message": "query successfully"
    }
    ```

  - Error test case: pass into non-existed province or non-supported province

    Request body

    ``` json
    Beiijng
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to query "
    }
    ```

### City Controller

#### Add city

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8086/hide/city/addCity

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "name" : "testName",
        "province" : "testProvince",
        "level" : "simi-city"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "create city successfully"
    }
    ```

#### Modify city

-  Request character: project tester

- Request method: POST

- Request path: http://localhost:8086/hide/city/modifyCity

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "id" : 155,
        "name" : "Yulin",
        "province" : "Guangxi",
        "level" : "semi-city"
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "modify city successfully"
    }
    ```

  - Error test case: pass into a non-existed city id

    Request body

    ```json
    {
        "id" : "1000",
        "name" : "Yulin",
        "province" : "Guangxi",
        "level" : "semi-city"
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "fail to modify city"
    }
    ```

#### delete city

- Request character: project tester

- Request method: POST

- Request path: http://localhost:8086/hide/city/deleteCity

- Test cases

  - Correct test case

    Request body

    ```json
    {
    	 "id" : 100
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "delete city successfully"
    }
    ```

  - Error test case: pass into non-existed city id

    Request body

    ```json
    {
    	"id": 1000 
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": null,
        "message": "fail to delete city"
    }
    ```

#### Query city list

- Request character: project tester

- Request method: POST

- Request path:  http://localhost:8086/hide/city/queryCityList

- Test cases

  - Correct test case

    Request body

    ```json
    {
        "level": "bigCity",
        "pageNum": 1,
        "pageSize": 5
    }
    ```

    Response body

    ```json
    {
        "code": "200",
        "data": [
            {
                "id": 12,
                "name": "Foshan",
                "province": "Guangdong",
                "level": "bigCity"
            },
            {
                "id": 18,
                "name": "Harbin",
                "province": "Heilongjiang",
                "level": "bigCity"
            },
            {
                "id": 20,
                "name": "Kunming",
                "province": "Yunnan",
                "level": "bigCity"
            },
            {
                "id": 21,
                "name": "Dalian",
                "province": "Liaoning",
                "level": "bigCity"
            },
            {
                "id": 22,
                "name": "Nanning",
                "province": "Guangxi",
                "level": "bigCity"
            }
        ],
        "message": "query citysuccessfully"
    }
    ```

  - Error request body: invalidated page number and page size

    Request body

    ```json
    {
        "level": "bigCity",
        "pageNum": 0,
        "pageSize": 5
    }
    ```

    Response body

    ```json
    {
        "code": "0",
        "data": null,
        "message": "pageNum or pageSize is invalid"
    }
    ```


## Gateway Module
