package mobi.qishui.sodaandroidutil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import mobi.qishui.sodautils.phone.PhoneUtils;
import mobi.qishui.sodautils.ui.SodBaseAct;

public class MainActivity extends SodBaseAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        Demo demo = new Demo();
        demo.name = "good";
        Log.i("test", "name " + demo.name);

    }

    private void initViews() {
        Button btn = (Button) findViewById(R.id.btn_call);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PhoneUtils().directCall(MainActivity.this, MainActivity.this, "18668240271");
            }
        });
    }


}
