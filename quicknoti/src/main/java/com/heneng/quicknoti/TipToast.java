package com.heneng.quicknoti;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Toast封装类
 * @author peter
 *
 */
public class TipToast {
	static TipToast instance;
	Context context;
	TipToastCallBack callBack;

	public static final int GRAVITY_TOP = 1;
	public static final int GRAVITY_BOTTOM = 2;
	public static final int GRAVITY_CENTER = 3;

	public static final int TIME_SHORT = Toast.LENGTH_SHORT;
	public static final int TIME_LONG = Toast.LENGTH_LONG;


	public static TipToast newInstance(Context context,TipToastCallBack tipToastCallBack){
		if(instance == null){
			instance = new TipToast(context, tipToastCallBack);
		}
		return instance;
	}

	public static TipToast getInstance(){
		return instance;
	}

	private TipToast(Context context, TipToastCallBack tipToastCallBack){
		this.context = context;
		callBack = tipToastCallBack;
	}

	/**
	 * 默认Toast
	 * @param tag      标签
	 * @param args     消息
	 */
	public void showToast(Object tag,Object... args)
	{
		showToast(TipToast.GRAVITY_BOTTOM, tag, args);
	}

	/**
	 * 线程中的Toast
	 * 	//  TipToast.showToast(getApplicationContext(),handler,(String) msg.obj);
	 * @param handler
	 * @param tag
	 * @param args
	 */
	public void showToast(final Handler handler, final Object tag, final Object... args)
	{

		// 新启动一个子线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 使用post方式加到主线程的消息队列中
				handler.post(new Runnable() {
					@Override
					public void run() {
						showToast(TipToast.GRAVITY_BOTTOM, tag, args);
					}
				});
			}
		}).start();

	}

	/**
	 * 设置位置的Toast
	 * @param state
	 * @param tag
	 * @param args
	 */
	public void showToast( int state, Object tag, Object... args) {

		showToast(0, state, tag, args);
	}

	/**
	 * 自定义视图的Toast
	 * @param resourceId
	 * @param state
	 * @param tag
	 * @param args
	 */
	public void showToast( int resourceId, int state, Object tag, Object... args) {

		String info = "";
		for(int i = 0; i < args.length - 1; i++){
			info += args[i] + ",";
		}
		info += args[args.length - 1] ;

		if(callBack != null){
			callBack.onToast(tag, info);
		}

		Toast mToast = null;

		if(resourceId != 0){ // 自定义的UI
			LayoutInflater inflater = LayoutInflater.from(context);
			View toastView = inflater.inflate(resourceId, null);
			toastView.setBackgroundColor(Color.TRANSPARENT);
			mToast = new Toast(context);
			mToast.setView(toastView);
		}
		else{ // 默认UI
			mToast = Toast.makeText(context, info, Toast.LENGTH_SHORT);
		}


		if(state == TipToast.GRAVITY_TOP)
		{
			mToast.setGravity(Gravity.BOTTOM|Gravity.TOP, 0, 100);
		}
		else if(state == TipToast.GRAVITY_BOTTOM)
		{
			mToast.setGravity(Gravity.BOTTOM|Gravity.BOTTOM, 0, 100);
		}
		else if (state == TipToast.GRAVITY_CENTER)
		{
			mToast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 100);
		}

		mToast.show();
	}




	public interface TipToastCallBack{
		void onToast(Object tag, String msg);
	}


}

