package mobi.qishui.sodaandroidutil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.trello.navi.Event;

import mobi.qishui.sodautils.phone.PhoneUtils;
import mobi.qishui.sodautils.ui.SodBaseAct;

public class MainActivity extends SodBaseAct {
    private PhoneUtils phoneUtils = new PhoneUtils(this);

    public MainActivity() {
        phoneUtils.watch(this, Event.REQUEST_PERMISSIONS_RESULT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        Button btn = (Button) findViewById(R.id.btn_call);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneUtils.directCall(MainActivity.this, MainActivity.this, "18668240271");
            }
        });
    }


}
