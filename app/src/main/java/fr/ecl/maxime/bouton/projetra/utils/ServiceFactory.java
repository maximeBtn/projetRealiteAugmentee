package fr.ecl.maxime.bouton.projetra.utils;

import fr.ecl.maxime.bouton.projetra.activites.ScanActivity;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Max on 2019-06-23.
 */
public class ServiceFactory {

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ScanActivity.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <T> T createService(Class<T> type) {
        return retrofit.create(type);
    }
}
