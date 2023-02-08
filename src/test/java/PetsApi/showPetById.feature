@openapi-file=src/main/resources/openapi.yaml
Feature: Info for a specific pet
	

Background:
* url baseUrl

@operationId=showPetById
Scenario Outline: Test showPetById for <status> status code

	* def params = __row
	* def result = call read('showPetById.feature@operation') { statusCode: #(+params.status), params: #(params), matchResponse: #(params.matchResponse) }
	* match result.responseStatus == <status>
		Examples:
		| status | petId | matchResponse |
		| 200    | 1 | true          |
		| 200    | 2 | true          |
    | 200    | 0 | true          |


@operationId=showPetById
Scenario: explore showPetById inline
	You can use this test to quickly explore your API.
	You can then copy this payload and paste it with Ctrl+Shift+V as an Example row above.
* def statusCode = 200
* def params = {"petId":0}
* def matchResponse = true
* call read('showPetById.feature@operation') 


@ignore
@operation @operationId=showPetById @openapi-file=src/main/resources/openapi.yaml
Scenario: operation PetsApi/showPetById
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
Given path '/pets/', args.params.petId
And headers headers
When method GET
# validate status code
* if (args.statusCode && responseStatus != args.statusCode) karate.fail(`status code was: ${responseStatus}, expected: ${args.statusCode}`)
# validate response body
* if (args.matchResponse === true) karate.call('showPetById.feature@validate')

@ignore @validate
Scenario: validates showPetById response

* def responseMatch =
"""
{
  "id": "#number",
  "name": "#string",
  "tag": "##string"
}
"""
* match  response contains responseMatch

