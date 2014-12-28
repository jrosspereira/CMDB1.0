package com.cjcore.cmdb.menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cjcore.cmdb.R;
import com.cjcore.cmdb.xml.XMLParser;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by jpereira on 12/14/2014.
 */
public class NavMenuUploadSource extends Fragment {
    View rootview;
    private String[] mFileList;
    private String mChosenFile;

    private File mPath = new File(Environment.getExternalStorageDirectory() + "//files//");
    private final String fileSeparator = "//";
    private static final String FTYPE = ".xml";
    private static final int DIALOG_LOAD_FILE = 1;
    private static final String TAG = "Upload Source";
    private static final int FILE_SELECT_CODE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.nav_menu_upload_source, container, false);

        Button btnSelectFile = (Button)rootview.findViewById(R.id.selectfile);

        btnSelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFileList();

                Dialog dialog = onCreateDialog(DIALOG_LOAD_FILE);

                dialog.show();
            }
        });

        Button btnUploadXml = (Button)rootview.findViewById(R.id.uploaddata);

        btnUploadXml.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                parseXml();
            }
        });

        return rootview;
    }

    private void loadFileList() {
        Log.wtf(TAG, Environment.getExternalStorageDirectory() + "//files//");
        try {
            mPath.mkdirs();
        } catch (SecurityException e) {
            Log.e(TAG, "unable to write on the sd card " + e.toString());
        }
        if (mPath.exists()) {
            FilenameFilter filter = new FilenameFilter() {

                @Override
                public boolean accept(File dir, String filename) {
                    File sel = new File(dir, filename);
                    //return filename.contains(FTYPE) || sel.isDirectory();
                    return filename.contains(FTYPE);
                }

            };
            mFileList = mPath.list(filter);
        } else {
            mFileList = new String[0];
        }
    }

    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        switch (id) {
            case DIALOG_LOAD_FILE:
                builder.setTitle("Choose your file");
                if (mFileList == null) {
                    Log.e(TAG, "Showing file picker before loading the file list");
                    dialog = builder.create();
                    return dialog;
                }
                builder.setItems(mFileList, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mChosenFile = mFileList[which];
                        //you can do stuff with the file here too
                        TextView txtFileName = (TextView)rootview.findViewById(R.id.fileName);

                        txtFileName.setText(mChosenFile);
                    }
                });
                break;
        }
        dialog = builder.show();
        return dialog;
    }

    private void parseXml(){
        XMLParser xmlParser = new XMLParser();

        xmlParser.parseXml(mPath + fileSeparator + mChosenFile);
    }
}
