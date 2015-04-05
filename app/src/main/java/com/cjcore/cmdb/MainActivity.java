package com.cjcore.cmdb;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cjcore.cmdb.menu.NavMenuAdvancedSearch;
import com.cjcore.cmdb.menu.NavMenuCategories;
import com.cjcore.cmdb.menu.NavMenuMovielist;
import com.cjcore.cmdb.menu.NavMenuRandomMovies;
import com.cjcore.cmdb.menu.NavMenuSingleMovie;
import com.cjcore.cmdb.menu.NavMenuUploadSource;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
       Fragment objFragment =  null;
       String fragmentName  = "";

        switch(position){
            case 0:
                objFragment = new NavMenuCategories();
                fragmentName = "Category";
                break;
            case 1:
                objFragment = new NavMenuAdvancedSearch();
                fragmentName = "AdvancedSearch";
                break;
            case 2:
                objFragment = new NavMenuRandomMovies();
                fragmentName = "RandomMovies";
                break;
            case 3:
                objFragment = new NavMenuUploadSource();
                fragmentName = "UploadSource";
                break;

        }

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.container, objFragment)
                .addToBackStack(fragmentName).commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_categories);
                break;
            case 2:
                mTitle = getString(R.string.title_advanced_search);
                break;
            case 3:
                mTitle = getString(R.string.title_random_movies);
                break;
            case 4:
                mTitle = getString(R.string.title_upload_source);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public void viewMovieOnclick(View v){
        TextView movieTitle = (TextView)v.findViewById(R.id.tv_mTitle);

        Long id = (Long)movieTitle.getTag();

        Fragment singleMoviePage = new NavMenuSingleMovie();
        Bundle args = new Bundle();
        args.putLong("movieId", id);
        singleMoviePage.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack("MovieList").replace(R.id.container, singleMoviePage).commit();

    }

    public void searchMovie(View v){
        TextView searchString = (TextView)v.findViewById(R.id.et_search);

        Fragment movieListPage = new NavMenuMovielist();
        Bundle args = new Bundle();
        args.putString("searchString", searchString.toString());
        movieListPage.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack("MovieView").replace(R.id.container, movieListPage).commit();

    }

}
