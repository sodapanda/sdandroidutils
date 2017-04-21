package mobi.qishui.sodautils.lifecycle;

import com.trello.navi.NaviComponent;

import rx.Subscription;

/**
 * Created by wangxiao on 2017/4/21.
 */

public interface LifeCycleWatcher {
    <T> Subscription watch(NaviComponent component);
}
