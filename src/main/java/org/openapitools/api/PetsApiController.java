package org.openapitools.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.openapitools.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-18T12:16:33.061421400+01:00[Europe/Budapest]")
@Controller
@RequestMapping("${openapi.swaggerPetstore.base-path:/v1}")
public class PetsApiController implements PetsApi {
	List<Pet> list = new ArrayList<Pet>();
	private final NativeWebRequest request;
	
	@Autowired
	public PetsApiController(NativeWebRequest request) {
		this.request = request;
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	@Override
	public ResponseEntity<List<Pet>> listPets(@NotNull @Valid Long limit) {

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> createPets(@Valid Pet pet) {
		if(list.stream().findFirst().filter(e -> pet.getId() == e.getId()).isPresent()) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			list.add(new Pet(pet.getId(), pet.getName(), pet.getTag()));			
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
	}

	@Override
	public ResponseEntity<Pet> showPetById(String petId) {
		Optional<Pet> optional = list.stream().findFirst().filter(e -> Long.parseLong(petId) == e.getId());
		if(optional.isPresent()) {
			return new ResponseEntity<>(optional.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null,HttpStatus.OK);
		}

	}

	
	
}
