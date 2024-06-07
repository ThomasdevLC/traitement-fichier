package fr.yuka;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "additif")
public class Additif {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String libelle;

	public Additif(String libelle) {

		this.libelle = libelle;
	}

	public Additif() {
	}

	@ManyToMany
	@JoinTable(name = "produits_additifs", joinColumns = @JoinColumn(name = "id_additifs", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"))
	private List<Produit> produits;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @return the produits
	 */
	public List<Produit> getProduits() {
		return produits;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @param produits the produits to set
	 */
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

}
