package wang.cn.com.optimize.ui.find.calendar.dingding;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BaseDaggerActivity;
import wang.cn.com.optimize.ui.widget.calendardingding.CaledarAdapter;
import wang.cn.com.optimize.ui.widget.calendardingding.CalendarBean;
import wang.cn.com.optimize.ui.widget.calendardingding.CalendarDateView;
import wang.cn.com.optimize.ui.widget.calendardingding.CalendarUtil;
import wang.cn.com.optimize.ui.widget.calendardingding.CalendarView;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-21
 * @time: 16:43
 */
public class CalendarDingDingActivity extends BaseDaggerActivity<CalendarDingDingPresenter>
        implements CalendarDingDingContract.view {

    @BindView(R.id.calendarDateView)
    CalendarDateView mCalendarDateView;
    @BindView(R.id.list)
    ListView mList;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendar_ding_ding;
    }

    @Override
    protected void initView() {

        mCalendarDateView.setAdapter(new CaledarAdapter() {
            @Override
            public View getView(View convertView, ViewGroup parentView, CalendarBean bean) {
                TextView view;
                if (convertView == null) {
                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_calendar, null);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(px(48), px(48));
                    convertView.setLayoutParams(params);
                }

                view = (TextView) convertView.findViewById(R.id.text);

                view.setText("" + bean.day);
                if (bean.mothFlag != 0) {
                    view.setTextColor(0xff9299a1);
                } else {
                    view.setTextColor(0xffffffff);
                }

                return convertView;
            }
        });

    }

    @Override
    protected void initData() {

        mCalendarDateView.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion, CalendarBean bean) {

                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(bean.year);
                stringBuffer.append("/");
                stringBuffer.append(getDisPlayNumber(bean.moth));
                stringBuffer.append("/");
                stringBuffer.append(getDisPlayNumber(bean.day));
                mTitle.setText(stringBuffer.toString());
            }
        });

        int[] data = CalendarUtil.getYMD(new Date());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(data[0]);
        stringBuffer.append("/");
        stringBuffer.append(data[1]);
        stringBuffer.append("/");
        stringBuffer.append(data[2]);
        mTitle.setText(stringBuffer.toString());

        mList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 100;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(CalendarDingDingActivity.this).
                            inflate(android.R.layout.simple_list_item_1, null);
                }

                TextView textView = (TextView) convertView;
                textView.setText("item" + position);

                return convertView;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void setCalendarDingDingDatas() {

    }

    private String getDisPlayNumber(int num) {
        return num < 10 ? "0" + num : "" + num;
    }

    public static int px(float dipValue) {
        Resources r=Resources.getSystem();
        final float scale =r.getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
