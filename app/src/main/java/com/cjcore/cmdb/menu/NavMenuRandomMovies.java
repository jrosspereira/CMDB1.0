package com.cjcore.cmdb.menu;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.cjcore.cmdb.R;
import com.cjcore.cmdb.bean.Movie;
import com.cjcore.cmdb.db.service.MovieService;
import com.cjcore.cmdb.view.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpereira on 12/14/2014.
 */
public class NavMenuRandomMovies extends Fragment{
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.nav_menu_random_movies, container, false);

        ArrayList<Movie> movies = new ArrayList<Movie>(getMovieList());

        final ListView lv = (ListView)rootview.findViewById(R.id.listView);
        lv.setAdapter(new MovieAdapter(rootview.getContext(), movies));

        //code to set adapter to populate list
        View footerView =  ((LayoutInflater)rootview.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.footer_random_moview, null, false);
        lv.addFooterView(footerView);

        Button btnMoreRandom = (Button)footerView.findViewById(R.id.btn_random);

        btnMoreRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateNewRandomMovies(lv);
            }
        });
        return rootview;
    }

    private List<Movie> getMovieList(){
        MovieService movieService = new MovieService(rootview.getContext());

        return movieService.findRandomMovies();

    }

    private void generateNewRandomMovies(ListView lv){
        ArrayList<Movie> movies = new ArrayList<Movie>(getMovieList());

        lv.setAdapter(new MovieAdapter(rootview.getContext(), movies));
    }
}
