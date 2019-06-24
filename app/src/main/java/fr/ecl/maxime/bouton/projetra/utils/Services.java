package fr.ecl.maxime.bouton.projetra.utils;

import fr.ecl.maxime.bouton.projetra.classes.Article;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Max on 2019-06-23.
 */

//Implémentation des requêtes
public interface Services {

    //Requête permettant d'obtenir le JSON associé à un codebarre
    @GET("{codeBarre}")
    Call<Article> obtenirArticle(@Path("codeBarre") String code);

}
