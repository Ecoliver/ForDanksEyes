package pet.store.controller;

import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.Contributor;
import pet.store.controller.model.ContributorData;
import pet.store.controller.model.PetStoreData;
import pet.store.service.StoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class StoreController {
	@Autowired
	private StoreService storeService;

	@PostMapping("/contributor")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ContributorData insertContributor(@RequestBody ContributorData contributorData) {
		log.info("Creating contriubtor {}", contributorData);
		return storeService.saveContributor(contributorData);

	}

	@PutMapping("/contributor/{contributorId}")
	public ContributorData updateContributor(@PathVariable Long contributorId,
			@RequestBody ContributorData contributorData) {
		contributorData.setContributorId(contributorId);

		log.info("Updating contributor {}", contributorData);
		return storeService.saveContributor(contributorData);
	}

	@GetMapping("/contributor")
	public List<ContributorData> retrieveAllContributors() {
		log.info("Retrieve all contributors.");
		return storeService.retrieveAllContributors();
	}

	@GetMapping("/contributor/{contributorId}")
	public Contributor retrieveContributorById(@PathVariable Long contributorId) {
		log.info("Retrieving contributor with ID= {}", contributorId);
		return storeService.findContributorById(contributorId);
	}

	@DeleteMapping("/contributor")
	public void deleteAllContributors() {
		log.info("Attempting to delete all contributors");
		throw new UnsupportedOperationException("Deleting all contributors is not allowed.");
	}

	@DeleteMapping("/contributor/(contributorId)")
	public Map<String, String> deleteContributorById(@PathVariable Long contributorId) {
		log.info("Deleting contributor with ID={}", contributorId);
		storeService.deleteContributorById(contributorId);

		return Map.of("Message", "Deletion of contributor with ID=" + contributorId + " was successful");
	}

	@PostMapping("/contributor/{contributorId}/park")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertPetStore(@PathVariable Long contributorId, @RequestBody PetStoreData petStoreData) {
		log.info("Creating store {} for contributor with ID= {}", petStoreData, contributorId);

		return storeService.savePetStore(contributorId, petStoreData);

	}

	@PutMapping("/contributor/{contributorId}/park/{storeId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData updatePetStore(@PathVariable Long contributorId, @PathVariable Long storeId,
			@RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(storeId);

		log.info("Creating store {} for contributor with ID= {}", petStoreData, contributorId);

		return storeService.savePetStore(contributorId, petStoreData);

	}

	@GetMapping("/contributor/{contributorId}/store/{storeId} ")
	public PetStoreData retievePetStoreById(@PathVariable Long contributorId, @PathVariable Long storeId) {
		log.info("Retrieving pet store with ID={} for contributor with ID={}", storeId, contributorId);
		return storeService.retievePetStoreById(contributorId, storeId);
	}
}
