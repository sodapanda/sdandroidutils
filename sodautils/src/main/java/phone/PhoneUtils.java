package phone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

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

    public void directCall(Context context, String phoneNum) {
        if (context == null || TextUtils.isEmpty(phoneNum)) {
            throw new IllegalArgumentException("context and number can't be null");
        }

        //todo implement
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        intent.setData(Uri.parse("tel:" + phoneNum));
//        context.startActivity(intent);


    }
}
