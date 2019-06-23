package fr.ecl.maxime.bouton.projetra.utils;

import fr.ecl.maxime.bouton.projetra.classes.Article;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Max on 2019-06-23.
 */
public interface Services {

    @GET("{codeBarre}")
    Call<Article> obtenirArticle(@Path("codeBarre") String code);

}
