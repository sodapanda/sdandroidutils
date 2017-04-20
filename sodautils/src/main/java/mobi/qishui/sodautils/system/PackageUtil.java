package mobi.qishui.sodautils.system;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by wangxiao on 2017/4/20.
 * <p>
 * Package searching utils
 */

public class PackageUtil {

    /**
     * check if some package is installed;
     *
     * @param context     context
     * @param packageName name of package be checked
     * @return if this package installed
     */
    public boolean isPackageInstalled(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            throw new IllegalArgumentException("package name can't be empty");
        }
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> infoList = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES);

        if (infoList == null) {
            return false;
        }
        for (PackageInfo info : infoList) {
            if (!TextUtils.isEmpty(info.packageName) && info.packageName.equals(packageName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Launch other App's activity
     *
     * @param context     caller activity
     * @param packageName package to be launched
     */
    public void launchApp(Context context, String packageName) {
        if (!isPackageInstalled(context, packageName)) {
            throw new IllegalArgumentException("Package not installed:" + packageName);
        }

        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(launchIntent);
    }
}
