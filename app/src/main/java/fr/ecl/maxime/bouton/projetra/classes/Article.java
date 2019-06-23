package fr.ecl.maxime.bouton.projetra.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Max on 2019-06-23.
 */
public class Article {

    @SerializedName("product")
    @Expose
    private Produit product;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("status_verbose")
    @Expose
    private String statusVerbose;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Produit getProduct() {
        return product;
    }

    public void setProduct(Produit product) {
        this.product = product;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatusVerbose() {
        return statusVerbose;
    }

    public void setStatusVerbose(String statusVerbose) {
        this.statusVerbose = statusVerbose;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
