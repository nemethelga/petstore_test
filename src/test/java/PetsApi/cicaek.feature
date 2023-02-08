@openapi-file=src/main/resources/openapi.yaml
Feature: PetsApi cicaek

Background:
* url baseUrl

@business-flow 
@operationId=createPets @operationId=listPets
Scenario: PetsApi cicaek

* def auth = { username: '', password: '' }

# createPets 
# Create a pet
Given def body =
"""
{
  "name": "cica",
  "id": 3,
  "tag": "szurke"
}
"""
When call read('classpath:PetsApi/createPets.feature@operation')
Then match responseStatus == 201
* def createPetsResponse = response

# listPets 
# List all pets
Given def params = {"limit":25}
When call read('classpath:PetsApi/listPets.feature@operation')
Then match responseStatus == 200
* def listPetsResponse = response

