package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pet.store.controller.model.PetStoreData.PetStore.petStoreContributor;
import pet.store.entity.Employee;

@Data
@NoArgsConstructor
public class PetStoreData {
	private pet.store.entity.PetStore dbPetStore;

	public PetStoreData(pet.store.entity.PetStore dbPetStore) {
		this.dbPetStore = dbPetStore;
	}

	public class PetStore {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long petStoreId;
		private String storeName;
		private String storeEmail;
		private String directions;
		private String stateOrProvience;
		private String country;
		private petStoreContributor contributor;

		public PetStore(PetStore petStore) {
			petStoreId = petStore.getPetStoreId();
			storeName = petStore.getStoreName();
			directions = petStore.getDirections();
			stateOrProvience = petStore.getStateOrProvince();
			country = petStore.getCountry();
			contributor = new petStoreContributor(petStore.getContributor());
		}

		@Data
		@NoArgsConstructor
		public static class petStoreContributor {
			private PetStoreContributorResponse contributor;

			public petStoreContributor(PetStoreContributorResponse contributor) {
				this.contributor = new PetStoreContributorResponse(PetStore.getContributor());

			}

			private Long contributorId;
			private static String contributorName;
			@Column(unique = true)
			private static String contributorEmail;

		}

		public PetStore(Contributor contributor) {
			petStoreId = contributor.getContributorId();
			storeName = contributor.getContributorName();
			storeEmail = contributor.getContributorEmail();

		}

		public Long getPetStoreId() {
			return petStoreId;
		}

		public String getStoreName() {
			return storeName;
		}

		public String getDirections() {
			return directions;
		}

		public String getStateOrProvince() {
			return stateOrProvience;
		}

		public String getCountry() {
			return country;
		}

		public static PetStoreContributorResponse getContributor() {
			return getContributor();
		}
	}

	class PetStoreContributorResponse {
		private Long contributorId;
		private String contributorName;

		public PetStoreContributorResponse(PetStoreContributorResponse petStoreContributorResponse) {
			this.contributorId = petStoreContributorResponse.getContributorId();
			this.contributorName = petStoreContributorResponse.getContributorName();
		}

		public Long getContributorId() {
			return contributorId;
		}

		public String getContributorName() {
			return contributorName;
		}
	}

	public Long setPetStoreId(Long storeId) {
		return storeId;

	}

	public Long getPetStoreId() {

		return getPetStoreId();
	}

	public Object getStoreName() {

		return getStoreName();
	}

	public Object getStateOrProvince() {
	
		return getStateOrProvince();
	}

	public Object getDirections() {
		
		return getDirections();
	}

	public Object getCountry() {
	
		return getCountry();
	}

}
