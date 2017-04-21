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
import mobi.qishui.sodautils.lifecycle.LifeCycleWatcher;
import rx.Subscription;

/**
 * Created by wangxiao on 2017/4/20.
 * Phone call utils
 */

public class PhoneUtils implements LifeCycleWatcher {
    private boolean mHasWatched;
    private Activity mAct;
    private String mPhoneNum;

    public PhoneUtils(Activity mAct) {
        this.mAct = mAct;
    }

    @Override
    public <T> Subscription watch(NaviComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("component and events can't be null");
        }

        mHasWatched = true;

        return RxNavi.observe(component, Event.REQUEST_PERMISSIONS_RESULT).subscribe((msg) -> {
            if (msg.requestCode() != Constants.PERMISSION_CALL_CODE) {
                return;
            }

            if (msg.grantResults().length > 0 &&
                    msg.grantResults()[0] == PackageManager.PERMISSION_GRANTED) {
                mAct.startActivity(getCallIntent());
            }
        });
    }

    private Intent getCallIntent() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mPhoneNum));
        return intent;
    }

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

    /**
     * make a call not open a dialer
     *
     * @param context   context
     * @param component component
     * @param phoneNum  number to call
     */
    public void directCall(Activity context, NaviComponent component, String phoneNum) {
        if (context == null || TextUtils.isEmpty(phoneNum)) {
            throw new IllegalArgumentException("context and number can't be null");
        }

        if (!mHasWatched) {
            throw new IllegalStateException("not watched!call watch first");
        }

        this.mPhoneNum = phoneNum;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.CALL_PHONE},
                    Constants.PERMISSION_CALL_CODE);
        } else {
            context.startActivity(getCallIntent());
        }
    }
}
