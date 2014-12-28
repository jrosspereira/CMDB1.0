package com.cjcore.cmdb.menu;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjcore.cmdb.R;

/**
 * Created by jpereira on 12/14/2014.
 */
public class NavMenuRandomMovies extends Fragment{
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.nav_menu_random_movies, container, false);

        return rootview;
    }
}
