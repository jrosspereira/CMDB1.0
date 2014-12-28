package com.cjcore.cmdb.xml;

import android.util.Log;

import com.cjcore.cmdb.bean.Movie;

import java.util.ArrayList;

/**
 * Created by jpereira on 12/26/2014.
 */
public class XMLGettersSetters {

    private ArrayList<Movie> movieList = new ArrayList<Movie>();

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(Movie movie) {
        this.movieList.add(movie);
        Log.i("This is the movie:", movie.getName());
    }

}
