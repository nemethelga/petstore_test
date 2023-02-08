@openapi-file=src/main/resources/openapi.yaml
Feature: List all pets
	

Background:
* url baseUrl

@operationId=listPets
Scenario Outline: Test listPets for <status> status code

	* def params = __row
	* def result = call read('listPets.feature@operation') { statusCode: #(+params.status), params: #(params), matchResponse: #(params.matchResponse) }
	* match result.responseStatus == <status>
		Examples:
		| status | limit | matchResponse |
		| 200    | 25 | true          |
		| 200    | 5  | true          |


@operationId=listPets
Scenario: explore listPets inline
	You can use this test to quickly explore your API.
	You can then copy this payload and paste it with Ctrl+Shift+V as an Example row above.
* def statusCode = 200
* def params = {"limit":25}
* def matchResponse = true
* call read('listPets.feature@operation') 


@ignore
@operation @operationId=listPets @openapi-file=src/main/resources/openapi.yaml
Scenario: operation PetsApi/listPets
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
And param limit = args.params.limit
And headers headers
When method GET
# validate status code
* if (args.statusCode && responseStatus != args.statusCode) karate.fail(`status code was: ${responseStatus}, expected: ${args.statusCode}`)
# validate response body
* if (args.matchResponse === true) karate.call('listPets.feature@validate')

@ignore @validate
Scenario: validates listPets response

* def responseMatch =
"""
{
  "id": "#number",
  "name": "#string",
  "tag": "##string"
}
"""
* match each response contains responseMatch

