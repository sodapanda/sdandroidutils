package mobi.qishui.sodautils.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.trello.navi.Event;
import com.trello.navi.Listener;
import com.trello.navi.NaviComponent;
import com.trello.navi.internal.NaviEmitter;

/**
 * Created by wangxiao on 2017/4/20.
 */

public class SodBaseAct extends Activity implements NaviComponent {
    private NaviEmitter emitter = NaviEmitter.createActivityEmitter();

    @Override
    public boolean handlesEvents(Event... events) {
        return emitter.handlesEvents(events);
    }

    @Override
    public <T> void addListener(Event<T> event, Listener<T> listener) {
        emitter.addListener(event, listener);
    }

    @Override
    public <T> void removeListener(Listener<T> listener) {
        emitter.removeListener(listener);
    }

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emitter.onCreate(savedInstanceState);
    }

    @Override
    @CallSuper
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        emitter.onCreate(savedInstanceState, persistentState);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        emitter.onStart();
    }

    @Override
    @CallSuper
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        emitter.onPostCreate(savedInstanceState);
    }

    @Override
    @CallSuper
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        emitter.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        emitter.onResume();
    }

    @Override
    @CallSuper
    protected void onPause() {
        emitter.onPause();
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        emitter.onStop();
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        emitter.onDestroy();
        super.onDestroy();
    }

    @Override
    @CallSuper
    protected void onRestart() {
        super.onRestart();
        emitter.onRestart();
    }

    @Override
    @CallSuper
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        emitter.onSaveInstanceState(outState);
    }

    @Override
    @CallSuper
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        emitter.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    @CallSuper
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        emitter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    @CallSuper
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        emitter.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    @CallSuper
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        emitter.onNewIntent(intent);
    }

    @Override
    @CallSuper
    public void onBackPressed() {
        super.onBackPressed();
        emitter.onBackPressed();
    }

    @Override
    @CallSuper
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        emitter.onAttachedToWindow();
    }

    @Override
    @CallSuper
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        emitter.onDetachedFromWindow();
    }

    @Override
    @CallSuper
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        emitter.onConfigurationChanged(newConfig);
    }

    @Override
    @CallSuper
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        emitter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    @CallSuper
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        emitter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
