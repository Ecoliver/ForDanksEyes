package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pet.store.controller.model.Contributor;

@Entity
@Data
public class PetStore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petStoreId;
	
	private String storeName;
	private String directions;
	private String stateOrProvience;
	private String country;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contributor_id", nullable = false)
	private Contributor contributor;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name = "pet_store_customer", joinColumns = @JoinColumn(name = "pet_store_id"),
			inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private String contributorEmail;

	@OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Employee> employees = new HashSet<>();

	public Long getPetStoreId() {
        return petStoreId;
    }

    // Getter for storeName
    public String getStoreName() {
        return storeName;
    }

    // Getter for directions
    public String getDirections() {
        return directions;
    }

    // Getter for stateOrProvince
    public String StateOrProvince() {
        return StateOrProvince();
    }

    // Getter for country
    public String getCountry() {
        return country;
    }

	public Contributor setContributor(Contributor contributor) {
		return contributor;
		
	}

	public Contributor getContributor() {
		return contributor;
	}

	public String setStoreName(Object storeName2) {
		return storeName;
		
	}

	public String setStateOrProvience(Object stateOrProvince) {
		return stateOrProvience;
		
	}

	public Object setDirections(Object directions) {
		return directions;
		
	}

	public String setCountry(Object country2) {
		return country;
		
	}
}


	
