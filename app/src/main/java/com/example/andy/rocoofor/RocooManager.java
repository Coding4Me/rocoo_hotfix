package com.example.andy.rocoofor;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.dodola.rocoofix.RocooFix;

import java.io.File;


/**
 * Created by liuqiangbin on 2016/10/11.
 */

public class RocooManager {

    private final static String TAG = "RocooManager";

    private String dexName;
    private String dexpath;

    private static RocooManager minstance;
    public static synchronized RocooManager getInstance(String path,String name){
        if(minstance == null){
            minstance = new RocooManager(path,name);
        }
        return minstance;
    }


    private RocooManager(String path,String name){
        this.dexpath = path;
        this.dexName = name;
    }

    public void init(Context context){
        try {
            RocooFix.init(context);
            initDexpatch(context);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void initDexpatch(Context context){
        //TODO System6.0 check Permission
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            boolean result = (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED);
            if(!result){
                throw new RuntimeException("please check read/write storage permission");
            }
        }

        if(TextUtils.isEmpty(dexpath) || TextUtils.isEmpty(dexName)){
            return;
        }

        String filePath = dexpath + File.separator + dexName;
        File file = new File(filePath);
        if(!file.exists() || !file.canRead()){
            return;
        }

        try {
            RocooFix.applyPatch(context,file.getAbsolutePath());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
