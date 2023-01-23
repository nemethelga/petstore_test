package org.openapitools.api;

import org.openapitools.model.Error;
import org.openapitools.model.Pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

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
		list.add(new Pet(pet.getId(), pet.getName(), pet.getTag()));
		return PetsApi.super.createPets(pet);
	}

	@Override
	public ResponseEntity<Pet> showPetById(String petId) {

		return new ResponseEntity<>(list.stream().findFirst().filter(e -> Long.parseLong(petId) == e.getId()).get(),
				HttpStatus.OK);
	}

}
