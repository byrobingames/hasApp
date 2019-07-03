package com.byrobin.hasApp;

import org.haxe.extension.Extension;
import org.haxe.lime.HaxeObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import java.lang.Thread;


public class HasApp extends Extension
{
    private static HaxeObject callback;
    private static PackageManager pm;

    public static void checkPackage(final HaxeObject cb){

        callback = cb;

        mainActivity.runOnUiThread(new Runnable() {
            public void run() {

                pm = mainContext.getPackageManager();
                List<ApplicationInfo> packageList = pm.getInstalledApplications(PackageManager.GET_META_DATA);
                ArrayList<String> installedapplist = new ArrayList<String>();

                Iterator<ApplicationInfo> it = packageList.iterator();
                while(it.hasNext()){
                    ApplicationInfo pk=(ApplicationInfo)it.next();

                    String packageName=pk.packageName.toString();

                    Log.d("HasApp","PackageName:" + packageName);

                    installedapplist.add(packageName);

                }

                String finalPackages= installedapplist.toString();

                callback.call("isPackageInstalled", new Object[] {finalPackages.replace(", ", ",").replace("]", "").replace("[", "")});
            }
        });
    }

    public static void checkAppName(final HaxeObject cb){

        callback = cb;

        mainActivity.runOnUiThread(new Runnable() {
            public void run() {

                pm = mainContext.getPackageManager();
                List<ApplicationInfo> appList = pm.getInstalledApplications(PackageManager.GET_META_DATA);
                ArrayList<String> installedapplist = new ArrayList<String>();

                Iterator<ApplicationInfo> it = appList.iterator();
                while(it.hasNext()){
                    ApplicationInfo pk=(ApplicationInfo)it.next();

                    String appName = pm.getApplicationLabel(pk).toString();

                    Log.d("HasApp","AppName:" + appName);

                    installedapplist.add(appName);
                }

                String finalAppNames= installedapplist.toString();

                callback.call("isAppInstalled", new Object[] {finalAppNames.replace(", ", ",").replace("]", "").replace("[", "")});

            }
        });
    }


}
