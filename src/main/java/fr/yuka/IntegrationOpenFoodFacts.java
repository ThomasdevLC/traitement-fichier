package fr.yuka;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class IntegrationOpenFoodFacts {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("yuka");
        EntityManager em = emf.createEntityManager();

        Path path = Paths.get("D:/diginamic/12. acces_base_données_JPA/TP/open-food-facts.csv");
        List<String> allLines = Files.readAllLines(path);
        List<String> lines = allLines.subList(1, Math.min(51, allLines.size())); 
        ArrayList<Produit> listeProduits = new ArrayList<>();

        em.getTransaction().begin();

        try {
            for (String line : lines) {
                line = line.replace("_", " ").replace("*", " ").toLowerCase();
                String[] col = line.split("\\|");

                if (col.length >= 30) {
                    String categorieStr = col[0];
                    String marqueStr = col[1];
                    String scoreNutritionnelStr = col[3];
                    String ingredientsStr = col[4];
                    String allergenesStr = col[28];
                    String additifsStr = col[29];

                    Categorie categorie = new Categorie(categorieStr);
                    Marque marque = new Marque(marqueStr);
                    ScoreNutritionnel scoreNutritionnel = new ScoreNutritionnel(scoreNutritionnelStr);
                    List<Ingredient> ingredients = getIngredients(ingredientsStr);
                    List<Additif> additifs = getAdditifs(additifsStr);
                    List<Allergene> allergenes = getAllergenes(allergenesStr);
                    em.persist(categorie);
                    em.persist(marque);
                    em.persist(scoreNutritionnel);
                    for (Ingredient ingredient : ingredients) {
                        em.persist(ingredient);
                    }
                    for (Additif additif : additifs) {
                        em.persist(additif);
                    }
                    for (Allergene allergene : allergenes) {
                        em.persist(allergene);
                    }
                    Produit produit = new Produit(categorie, marque, scoreNutritionnel, ingredients, additifs, allergenes);
                    listeProduits.add(produit);
                } else {
                    System.out.println("La ligne a moins de 30 colonnes : " + line);
                }
            }

            for (Produit produit : listeProduits) {
                em.persist(produit);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

        for (int i = 0; i < listeProduits.size() && i < 10; i++) {
            Produit produit = listeProduits.get(i);
            System.out.println(produit.toString());
        }
    }

    private static List<Ingredient> getIngredients(String ingredientsStr) {
        List<Ingredient> ingredients = new ArrayList<>();
        String[] ingredientArray = ingredientsStr.split(",");

        for (String ingredient : ingredientArray) {
            ingredient = ingredient.trim();
            ingredients.add(new Ingredient(ingredient));
        }

        return ingredients;
    }

    private static List<Allergene> getAllergenes(String allergenesStr) {
        List<Allergene> allergenes = new ArrayList<>();
        String[] allergeneArray = allergenesStr.split(",");

        for (String allergene : allergeneArray) {
            allergene = allergene.trim();
            allergenes.add(new Allergene(allergene));
        }

        return allergenes;
    }

    private static List<Additif> getAdditifs(String additifsStr) {
        List<Additif> additifs = new ArrayList<>();
        String[] additifArray = additifsStr.split(",");

        for (String additif : additifArray) {
            additif = additif.trim();
            additifs.add(new Additif(additif));
        }

        return additifs;
    }
}

