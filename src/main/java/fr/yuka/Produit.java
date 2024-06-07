package fr.yuka;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produit")
public class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_categorie")
	private Categorie categorie;
	
	@ManyToOne
	@JoinColumn(name = "id_marque")
	private Marque marque;
	
	@ManyToOne
	@JoinColumn(name = "id_score")
	private ScoreNutritionnel scoreNutritionnel;
	
	@ManyToMany
	@JoinTable(name = "produits_ingredients", joinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "id_ingredient", referencedColumnName = "id"))	
	private List<Ingredient> ingredients;
	
	@ManyToMany
	@JoinTable(name = "produits_additifs", joinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "id_additifs", referencedColumnName = "id"))	
	private List<Additif> additifs;
	
	@ManyToMany
	@JoinTable(name = "produits_allergenes", joinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "id_allergenes", referencedColumnName = "id"))	
	private List<Allergene> allergenes;

	public Produit(Categorie categorie, Marque marque, ScoreNutritionnel scoreNutritionnel,
			List<Ingredient> ingredients, List<Additif> additifs, List<Allergene> allergenes) {
		super();
		this.categorie = categorie;
		this.marque = marque;
		this.scoreNutritionnel = scoreNutritionnel;
		this.ingredients = ingredients;
		this.additifs = additifs;
		this.allergenes = allergenes;
	}

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @return the marque
	 */
	public Marque getMarque() {
		return marque;
	}

	/**
	 * @return the scoreNutritionnel
	 */
	public ScoreNutritionnel getScoreNutritionnel() {
		return scoreNutritionnel;
	}

	/**
	 * @return the ingredients
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @return the additifs
	 */
	public List<Additif> getAdditifs() {
		return additifs;
	}

	/**
	 * @return the allergenes
	 */
	public List<Allergene> getAllergenes() {
		return allergenes;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	/**
	 * @param scoreNutritionnel the scoreNutritionnel to set
	 */
	public void setScoreNutritionnel(ScoreNutritionnel scoreNutritionnel) {
		this.scoreNutritionnel = scoreNutritionnel;
	}

	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * @param additifs the additifs to set
	 */
	public void setAdditifs(List<Additif> additifs) {
		this.additifs = additifs;
	}

	/**
	 * @param allergenes the allergenes to set
	 */
	public void setAllergenes(List<Allergene> allergenes) {
		this.allergenes = allergenes;
	}

	@Override
	public String toString() {
		return "Produit [categorie=" + categorie.getLibelle() + ", marque=" + marque.getLibelle()
				+ ", scoreNutritionnel=" + scoreNutritionnel.getLibelle() + ", ingredients=" + ingredients
				+ ", additifs=" + additifs + ", allergenes=" + allergenes + "]";
	}

}
