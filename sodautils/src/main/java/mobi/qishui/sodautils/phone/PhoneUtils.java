package mobi.qishui.sodautils.phone;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import com.trello.navi.Event;
import com.trello.navi.NaviComponent;
import com.trello.navi.rx.RxNavi;

import mobi.qishui.sodautils.Constants;

/**
 * Created by wangxiao on 2017/4/20.
 * Phone call utils
 */

public class PhoneUtils {
    /**
     * open dialer with number
     *
     * @param context  context
     * @param phoneNum number to call
     */
    public void openDialer(Context context, String phoneNum) {
        if (context == null || TextUtils.isEmpty(phoneNum)) {
            throw new IllegalArgumentException("context and number can't be null");
        }

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNum));
        context.startActivity(intent);
    }

    public void directCall(Activity context, NaviComponent component, String phoneNum) {
        if (context == null || TextUtils.isEmpty(phoneNum)) {
            throw new IllegalArgumentException("context and number can't be null");
        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNum));
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.CALL_PHONE},
                    Constants.PERMISSION_CALL_CODE);

            RxNavi.observe(component, Event.REQUEST_PERMISSIONS_RESULT).subscribe((msg) -> {
                if (msg.requestCode() != Constants.PERMISSION_CALL_CODE) {
                    return;
                }

                if (msg.grantResults().length > 0 &&
                        msg.grantResults()[0] == PackageManager.PERMISSION_GRANTED) {
                    context.startActivity(intent);
                }
            });

        } else {
            context.startActivity(intent);
        }
    }
}
