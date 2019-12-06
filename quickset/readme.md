
欢迎分支创造 - [QuickSet]

### 快速搭建设置页 

* 支持版本 15+
* 超小依赖,仅1k
* 无需其他权限
* 高度可扩展,仅需一行代码就能添加一项设置条
* 自带SharedPreferences,免于繁琐的重复设置

### 效果预览

> 界面显示的内容都可自定义,由于懒,就没有每个都设置了

* 效果图![demo](./doc/image/效果图.png)


###  快速使用

* 1.初始化使用
``` java {.line-numbers}
 SettingFragment settingFragment = SettingFragment.newInstance();
 changeFragment(settingFragment);
```

* 2.Activity中
``` java {.line-numbers}
private void changeFragment(Fragment fragment){
        //实例化碎片管理器对象
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        //选择fragment替换的部分
        ft.replace(R.id.cl_content,fragment);
        ft.commit();
}
```

* 3. 继承类库
``` java {.line-numbers}

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blxt.clipboardmanager.R;
import com.blxt.setinglibrary.BaseSettingFragment;
import com.blxt.setinglibrary.item.BaseSetItemView;
import com.blxt.setinglibrary.item.SetItemView;
import com.blxt.setinglibrary.item.SetItemViewSw;
import com.blxt.utils.IntentJump;

public class SettingFragment extends BaseSettingFragment implements BaseSetItemView.OnClickListenerCallBack {

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setClickListener(this);
        mViewModel = getSettingViewModel();
        initItems();
        mViewModel.setAppName(R.string.app_name);
        mViewModel.setAppVersion(R.string.app_version);
        mViewModel.setAppImage(R.mipmap.ic_launcher_round);
    }

    // 需要自己实现
    private void initItems(){

        addItem(new SetItemViewSw(getContext(), "开机自启").setTitleImage(R.mipmap.ic_launcher_round));
        addItem(new SetItemViewSw(getContext(), "固定通知").setTitleImage(R.mipmap.ic_launcher_round));
        addItem(new SetItemView(getContext(), "检查更新").setTitleImage(R.mipmap.ic_launcher_round));
        addItem(new SetItemView(getContext(), "分享").setTitleImage(R.mipmap.ic_launcher_round));
        addItem(new SetItemView(getContext(), "支持").setTitleImage(R.mipmap.ic_launcher_round));
        addItem(new SetItemView(getContext(), "GitHub开源").setTitleImage(R.mipmap.ic_launcher_round));

    }

    @Override
    public boolean onClickSetItem(View view) {
       // super
        if(view instanceof BaseSetItemView){
            String title = ((BaseSetItemView) view).getTitle();
            Log.i("点击",title);
            if(title.equals("GitHub开源")){
                IntentJump.openWeb("https://github.com/ZhangJiaLei123/ClipboardManager",view.getContext());
            }
            else if(title.equals("开机自启")){
                return false;
            }
        }
        return true;
    }

}
```

### 接口列表

* BaseSettingFragment接口  
``` java

    /**
     * 设置监听回调,需要在addItem()前使用
     * @param clickListener
     */
    void setClickListener(BaseSetItemView.OnClickListenerCallBack clickListener);

    /**
     * 添加配置项视图
     * @param view
     */
    void addItem(View view);

    /**
     * 获取ViewModel
     * @return
     */
    SettingViewModel getSettingViewModel();


```

* SettingViewModel接口  
``` java

    // 设置applogo
    void setAppImage(int resID);
    // 设置头背景
    void setAppImageBack(int resID);
    // 设置app名称
    void setAppName(int resId);
    void setAppName(String resId);
    // 设置app版本
    void setAppVersion(int resId);
    void setAppVersion(String resId);

```

* BaseSetItemView接口
``` java

// 标题
String getTitle();
// 标题图片
BaseSetItemView setTitleImage(int res);
// 提示文本
BaseSetItemView setHint(String tipText);
BaseSetItemView setHint(int tipText);
String getHintText();
// 监听
void setOnClickListener(OnClickListenerCallBack clickListener);
// 获取SharedPreferences
SharedPreferences getSharedPreferences();

```