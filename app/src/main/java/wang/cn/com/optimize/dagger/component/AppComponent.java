package wang.cn.com.optimize.dagger.component;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import wang.cn.com.optimize.App;
import wang.cn.com.optimize.dagger.module.AllActivitysModule;
import wang.cn.com.optimize.dagger.module.AllFragmentsModule;

/**
 * @author woong
 */
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AllFragmentsModule.class,
        AllActivitysModule.class
})
public interface AppComponent {
    void inject(App app);
}
