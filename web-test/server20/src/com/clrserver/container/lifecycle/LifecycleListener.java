package com.clrserver.container.lifecycle;

import com.clrserver.container.packagings.LifecycleEvent;

/**
 * Created by Administrator on 2017-04-19.
 */
public interface LifecycleListener {
    void lifecycleEvent(LifecycleEvent event);
}
