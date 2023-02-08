@openapi-file=src/main/resources/openapi.yaml
Feature: Create a pet
	

Background:
* url baseUrl

@operationId=createPets
Scenario Outline: Test createPets for <status> status code

	* def args = read(<testDataFile>)
	* def result = call read('createPets.feature@operation') args
	* match result.responseStatus == <status>
		Examples:
		| status | testDataFile |
		| 201    | 'test-data/createPets_201.yml' |
		| 201    | 'test-data/createPets_default.yml' |


@operationId=createPets
Scenario: explore createPets inline
	You can use this test to quickly explore your API.
	You can then copy this payload and paste it with Ctrl+Shift+V as an Example row above.
* def payload =
"""
{
  "statusCode": 201,
  "headers": {},
  "params": {},
  "body": {
    "name": "cicae",
    "id": 2,
    "tag": "cirmos"
  },
  "matchResponse": true
}
"""
* call read('createPets.feature@operation') payload


@ignore
@operation @operationId=createPets @openapi-file=src/main/resources/openapi.yaml
Scenario: operation PetsApi/createPets
* def args = 
"""
{
    auth: #(karate.get('auth')), 
    headers: #(karate.get('headers')), 
    params: #(karate.get('params')), 
    body: #(karate.get('body')), 
    statusCode: #(karate.get('statusCode')), 
    matchResponse: #(karate.get('matchResponse'))
}
"""
* def authHeader = call read('classpath:karate-auth.js') args.auth
* def headers = karate.merge(args.headers || {}, authHeader || {})
Given path '/pets'
And headers headers
And request args.body
When method POST
# validate status code
* if (args.statusCode && responseStatus != args.statusCode) karate.fail(`status code was: ${responseStatus}, expected: ${args.statusCode}`)
