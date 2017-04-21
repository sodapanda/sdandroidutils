package mobi.qishui.sodautils.lifecycle;

import com.trello.navi.NaviComponent;

import rx.Subscription;

/**
 * Created by wangxiao on 2017/4/21.
 * <p>
 * Impl of this interface should be registered by client Activities
 */

public interface LifeCycleWatcher {
    Subscription watch(NaviComponent component);
}
