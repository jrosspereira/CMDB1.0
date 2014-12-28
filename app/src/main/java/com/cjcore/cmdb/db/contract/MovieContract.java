package com.cjcore.cmdb.db.contract;

import android.provider.BaseColumns;

/**
 * Created by jpereira on 12/28/2014.
 */
public class MovieContract {

    public MovieContract(){

    }

    public static abstract class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "CMDB";
        public static final String COLUMN_NAME_MOVIE_ID = "movie_id";
        public static final String COLUMN_NAME_MOVIE_NAME = "movie_name";
        public static final String COLUMN_NAME_SUMMARY = "summary";
        public static final String COLUMN_NAME_STARS = "stars";
        public static final String COLUMN_NAME_YEAR_RELEASE = "year_release";
        public static final String COLUMN_NAME_RATE = "rate";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_CATEGORIES = "categories";
        public static final String COLUMN_NAME_DIRECTOR = "director";
        public static final String COLUMN_NAME_RATED = "rated";
        public static final String COLUMN_NAME_RUN_TIME = "run_time";
        public static final String COLUMN_NAME_IMDB_ID = "imdb_id";
        public static final String COLUMN_NAME_FILE_NAME= "file_name";
        public static final String COLUMN_NAME_FILE_SIZE = "file_size";
        public static final String COLUMN_NAME_REMARKS = "remarks";
        public static final String COLUMN_NAME_REVIEWS = "reviews";
        public static final String COLUMN_NAME_HD_LOCATION = "hd_location";

    }
}
