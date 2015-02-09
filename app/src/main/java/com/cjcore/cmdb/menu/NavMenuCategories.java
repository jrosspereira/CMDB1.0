package com.cjcore.cmdb.menu;

import android.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjcore.cmdb.R;

import java.lang.SuppressWarnings;

/**
 * Created by jpereira on 12/14/2014.
 */
public class NavMenuCategories extends Fragment{
    View rootview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.nav_menu_categories, container, false);

        return rootview;
    }
}
