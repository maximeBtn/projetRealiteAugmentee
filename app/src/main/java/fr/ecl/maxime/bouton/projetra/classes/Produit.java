package fr.ecl.maxime.bouton.projetra.classes;

/**
 * Created by Max on 2019-06-23.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Produit {

    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("categories")
    @Expose
    private String categories;
    @SerializedName("stores")
    @Expose
    private String stores;
    @SerializedName("ingredients_from_palm_oil_n")
    @Expose
    private Integer ingredientsFromPalmOilN;
    @SerializedName("brands")
    @Expose
    private String brands;
    @SerializedName("origins")
    @Expose
    private String origins;
    @SerializedName("labels")
    @Expose
    private String labels;
    @SerializedName("ingredients_text")
    @Expose
    private String ingredientsText;
    @SerializedName("nutrition_grades")
    @Expose
    private String nutritionGrades;
    @SerializedName("image_front_small_url")
    @Expose
    private String imageFrontSmallUrl;
    @SerializedName("packaging")
    @Expose
    private String packaging;
    @SerializedName("quantity")
    @Expose
    private String quantity;

    public String getProductName() {
        return productName;
    }

    public void setProductNameFr(String productName) {
        this.productName = productName;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getStores() {
        return stores;
    }

    public void setStores(String stores) {
        this.stores = stores;
    }

    public Integer getIngredientsFromPalmOilN() {
        return ingredientsFromPalmOilN;
    }

    public void setIngredientsFromPalmOilN(Integer ingredientsFromPalmOilN) {
        this.ingredientsFromPalmOilN = ingredientsFromPalmOilN;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getOrigins() {
        return origins;
    }

    public void setOrigins(String origins) {
        this.origins = origins;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getIngredientsText() {
        return ingredientsText;
    }

    public void setIngredientsText(String ingredientsText) {
        this.ingredientsText = ingredientsText;
    }

    public String getNutritionGrades() {
        return nutritionGrades;
    }

    public void setNutritionGrades(String nutritionGrades) {
        this.nutritionGrades = nutritionGrades;
    }

    public String getImageFrontSmallUrl() {
        return imageFrontSmallUrl;
    }

    public void setImageFrontSmallUrl(String imageFrontSmallUrl) {
        this.imageFrontSmallUrl = imageFrontSmallUrl;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
