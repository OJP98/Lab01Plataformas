package com.oscarjuarez.proyecto1;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by Oscar Juarez on 1/03/2018.
 */

public class GeneroMusical {

    @ColumnInfo(name = "artistas")
    private String artista;

    @ColumnInfo(name = "generos")
    private String genero;

    public GeneroMusical(String artista, String genero) {
        this.artista = artista;
        this.genero = genero;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
