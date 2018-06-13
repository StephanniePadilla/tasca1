package com.example.steph.tasta1sarapadilla;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APII {

    @GET("/getPartides")
    Call<List<Partida>> getPartides();

}
