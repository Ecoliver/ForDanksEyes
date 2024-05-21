package pet.store.controller.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;
import pet.store.entity.PetStore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import lombok.EqualsAndHashCode;


@Entity
@Data
public class Contributor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long contributorId;
	
	private static String contributorName;
	
	@Column(unique = true)
	private static String contributorEmail;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "contributor", cascade = CascadeType.ALL)
	private static ArrayList<PetStore> petStore = new ArrayList<PetStore>();

	public static Long getContributorId() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getContributorName() {
		return contributorName;
	}

	public static String getContributorEmail() {
		return contributorEmail;
	}

	public static ArrayList<PetStore> getPetStores() {
		return petStore;
	}

	public void setContributorEmail(String contributorEmail2) {

		
	}

	public void setContributorName(String contributorName2) {
		// TODO Auto-generated method stub
		
	}

}
