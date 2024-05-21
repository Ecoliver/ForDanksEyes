package pet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.Contributor;
import pet.store.controller.model.ContributorData;
import pet.store.controller.model.PetStoreData;
import pet.store.dao.ContributorDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class StoreService {

	@Autowired
	private ContributorDao contributorDao;

	@Autowired
	private PetStoreDao petStoreDao;

	@Transactional(readOnly = false)
	public ContributorData saveContributor(ContributorData contributorData) {
		Long contributorId = Contributor.getContributorId();
		Contributor contributor = findOrCreateContributor(contributorId);

		setFieldsInContributor(contributor, contributorData);
		return new ContributorData(contributorDao.save(contributor));

	}

	private void setFieldsInContributor(Contributor contributor, ContributorData contributorData) {
		contributor.setContributorEmail(Contributor.getContributorEmail());
		contributor.setContributorName(Contributor.getContributorName());

	}

	private Contributor findOrCreateContributor(Long contributorId) {
		Contributor contributor;

		if (Objects.isNull(contributorId)) {
			contributor = new Contributor();
		} else {
			contributor = findContributorById(contributorId);
		}
		return contributor;
	}

	public Contributor findContributorById(Long contributorId) {
		return contributorDao.findById(contributorId).orElseThrow(
				() -> new NoSuchElementException("Contributor with ID =" + contributorId + "was not found."));
	}

	@Transactional(readOnly = true)
	public List<ContributorData> retrieveAllContributors() {
		List<Contributor> contributors = null;
		try {
			contributors = contributorDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ContributorData> response = new LinkedList<>();

		for (Contributor contributor : contributors) {
			response.add(new ContributorData(contributor));
		}
		return response;

	}

	@Service
	public class PetStoreService {
		@SuppressWarnings("unused")
		private final ContributorDao contributorDao;

		@Autowired
		public PetStoreService(ContributorDao ContributorDao) {
			this.contributorDao = ContributorDao;
		}

		public void savePetStore(ContributorData petStoreData) {

		}
	}

	@Transactional(readOnly = true)
	public void deleteContributorById(Long contributorId) {
		Contributor contributo = findContributorById(contributorId);
		contributorDao.delete(contributo);

	}

	@Transactional(readOnly = false)
	public PetStoreData savePetStore(Long contributorId, PetStoreData petStoreData) {
		Contributor contributor = findContributorById(contributorId);

		PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		setPetStoreFields(petStore, petStoreData);

		petStore.setContributor(contributor);
		contributor.getPetStores().add(petStore);
		PetStore dbPetStore = petStoreDao.save(petStore);
		return new PetStoreData(dbPetStore);

	}

	private void setPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		petStore.setCountry(petStoreData.getCountry());
		petStore.setDirections(petStoreData.getDirections());
		petStore.setStateOrProvience(petStoreData.getStateOrProvince());
		petStore.setStoreName(petStoreData.getStoreName());

	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		PetStore petStore;

		if (Objects.isNull(petStoreId)) {
			petStore = new PetStore();

		} else {
			petStore = findPetStoreById(petStoreId);
		}
		return petStore;
	}

	private PetStore findPetStoreById(Long petStoreId) throws NoSuchElementException {
		try {
			return PetStoreDao.findById(petStoreId)
					.orElseThrow(() -> new NoSuchElementException("Pet store with ID=" + petStoreId + "does not exist."));
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Transactional(readOnly = true)
	public PetStoreData retievePetStoreById(Long contributorId, Long storeId) {
		findContributorById(contributorId);
		PetStore petStore = findPetStoreById(storeId);

		if (petStore.getContributor().getContributorId() != contributorId) {
			throw new IllegalStateException(
					"Pet store with ID=" + storeId + " is not owned by contributor with ID=" + contributorId);

		}
		return new PetStoreData(petStore);

	}

}
