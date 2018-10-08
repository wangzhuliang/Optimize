package wang.cn.com.optimize.dagger.module;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import wang.cn.com.optimize.dagger.module.activitys.CalendarActivityModule;
import wang.cn.com.optimize.dagger.module.activitys.CalendarDingDingActivityModule;
import wang.cn.com.optimize.dagger.module.activitys.CircleFriendsModule;
import wang.cn.com.optimize.dagger.module.activitys.EchelonActivityModule;
import wang.cn.com.optimize.dagger.module.activitys.GalleryActivityModule;
import wang.cn.com.optimize.dagger.module.activitys.HomeDetailActivityModule;
import wang.cn.com.optimize.dagger.module.activitys.TanTanActivityModule;
import wang.cn.com.optimize.dagger.module.activitys.ViewPagerLayoutManagerActivityModule;
import wang.cn.com.optimize.dagger.scope.PerActivity;
import wang.cn.com.optimize.ui.find.calendar.CalendarActivity;
import wang.cn.com.optimize.ui.find.calendar.dingding.CalendarDingDingActivity;
import wang.cn.com.optimize.ui.find.circlefriends.CircleFriendsActivity;
import wang.cn.com.optimize.ui.home.effect.echelon.EchelonActivity;
import wang.cn.com.optimize.ui.home.effect.gallery.GalleryActivity;
import wang.cn.com.optimize.ui.home.effect.tantan.TanTanActivity;
import wang.cn.com.optimize.ui.home.effect.viewpager.ViewPagerLayoutManagerActivity;
import wang.cn.com.optimize.ui.home.homedetail.HomeDetailActivity;

/**
 * @author woong
 */
@Module()
public abstract class AllActivitysModule {
    @PerActivity
    @ContributesAndroidInjector(modules = HomeDetailActivityModule.class)
    abstract HomeDetailActivity contributeTopicDetailActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = EchelonActivityModule.class)
    abstract EchelonActivity contributeEchelonActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = GalleryActivityModule.class)
    abstract GalleryActivity contributeGalleryActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = TanTanActivityModule.class)
    abstract TanTanActivity contributeTanTanActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = ViewPagerLayoutManagerActivityModule.class)
    abstract ViewPagerLayoutManagerActivity contributeViewPagerLayoutManagerInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = CircleFriendsModule.class)
    abstract CircleFriendsActivity contributeCircleFriendsActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = CalendarActivityModule.class)
    abstract CalendarActivity contributCalendarActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = CalendarDingDingActivityModule.class)
    abstract CalendarDingDingActivity contributCalendarDingDingActivityInjector();
}
