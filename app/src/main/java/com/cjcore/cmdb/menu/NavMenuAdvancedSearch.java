package com.cjcore.cmdb.menu;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjcore.cmdb.R;

/**
 * Created by jpereira on 12/14/2014.
 */
public class NavMenuAdvancedSearch extends Fragment{
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.nav_menu_advanced_search, container, false);

        return rootview;
    }
}
