package com.cjcore.cmdb.menu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.cjcore.cmdb.R;
import com.cjcore.cmdb.bean.Movie;
import com.cjcore.cmdb.db.service.MovieService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jpereira on 12/14/2014.
 */
public class NavMenuAdvancedSearch extends Fragment{
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.nav_menu_advanced_search, container, false);

        SeekBar rateSeekBar = (SeekBar)rootview.findViewById(R.id.sb_rate);
        final TextView textView = (TextView)rootview.findViewById(R.id.tv_rate_seekbar);

        rateSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Populate android spinner with years
        ArrayList<String> years = new ArrayList<String>();
        Integer thisYear = Calendar.getInstance().get(Calendar.YEAR);
        Integer minYear = Integer.parseInt(getString(R.string.min_year));

        years.add("");
        for (int i = thisYear; i >= minYear; i--)
        {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, years);

        Spinner spinYear = (Spinner)rootview.findViewById(R.id.spinner_year_release);
        spinYear.setAdapter(yearAdapter);
        spinYear.setSelection(yearAdapter.getPosition(""));

        //Search button onclick
        Button searchButton = (Button)rootview.findViewById(R.id.btn_advanced_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovies();

            }
        });

        return rootview;
    }

    private void searchMovies(){

        Fragment movieListPage = new NavMenuMovielistAdvancedSearch();
        Bundle args = new Bundle();

        EditText et_title = (EditText)rootview.findViewById(R.id.et_search_title);
        EditText et_category = (EditText)rootview.findViewById(R.id.et_search_category);
        EditText et_director = (EditText)rootview.findViewById(R.id.et_search_director);
        EditText et_actor = (EditText)rootview.findViewById(R.id.et_search_actor);
        TextView tv_rate = (TextView)rootview.findViewById(R.id.tv_rate_seekbar);
        Spinner sp_rated = (Spinner)rootview.findViewById(R.id.spinner_rated);
        Spinner sp_year_release = (Spinner)rootview.findViewById(R.id.spinner_year_release);

        args.putString("movieTitle", et_title.getText().toString());
        args.putString("movieCategory",et_category.getText().toString());
        args.putString("movieDirector", et_director.getText().toString());
        args.putString("movieActor", et_actor.getText().toString());
        args.putString("movieRate", tv_rate.getText().toString());
        args.putString("movieRated", sp_rated.getSelectedItem().toString());
        args.putString("movieYearRelease", sp_year_release.getSelectedItem().toString());

        movieListPage.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, movieListPage).commit();


    }


}
