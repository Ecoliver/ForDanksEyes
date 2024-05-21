package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.controller.model.ContributorData.PetStoreResponse;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data
public class ContributorData {

	private Long contributorId;
	private static String contributorName;
	private static String contributorEmail;
	private Set<PetStoreResponse> petStore = new HashSet<>();

	@SuppressWarnings("unchecked")
	public ContributorData(Contributor save) {
		contributorId = Contributor.getContributorId();
		contributorName = Contributor.getContributorName();
		contributorName = Contributor.getContributorEmail();

		for (PetStore petStore : Contributor.getPetStores()) {
			((Set<PetStoreResponse>) petStore).add(new PetStoreResponse(petStore));
		}

	}

	@Data
	@NoArgsConstructor
	static class PetStoreResponse {
		private Long petStoreId;
		private String storeName;
		private String directions;
		private String stateOrProvince;
		private String country;
		private Set<String> amenities = new HashSet<>();

		PetStoreResponse(PetStore petStore) {
			petStoreId = petStore.getPetStoreId();
			storeName = petStore.getStoreName();
			directions = petStore.getDirections();
			stateOrProvince = petStore.StateOrProvince();
			country = petStore.getCountry();

			class PetStoreCustomer {
				private Long customerId;
				private String customerName;

				public PetStoreCustomer(Customer customer) {
					this.customerId = Customer.getId();
					this.customerName = Customer.getName();
				}
			}
		}

		@Data
		@NoArgsConstructor
		public class PetStoreEmployee {
			private Long employeeId;
			private String employeeName;

			public PetStoreEmployee(Employee employee) {
				this.employeeId = Employee.getId();
				this.employeeName = Employee.getName();
			}
		}
	}

	public static String getContributorName() {
		return contributorName;
	}

	public static String getContributorEmail() {
		return contributorEmail;
	}

	public ContributorData setContributorId(Long contributorId) {
		Long setContributorId = Contributor.getContributorId();
		ContributorData contributor = setContributorId(contributorId);

		return new ContributorData(ContributorData.save(contributorId));

	}

	private static Contributor save(Long contributorId) {

		return save(contributorId);
	}
}
