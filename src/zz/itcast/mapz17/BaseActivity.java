package zz.itcast.mapz17;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.example.hellomap.R;

public class BaseActivity extends Activity {

	// 显示地图的那个 view 
	protected MapView mapView;
	
	// 获得百度地图
	protected BaiduMap baiduMap;

	protected Context ctx;


//	郑州传智：34.798413, 113.551107,（长椿路11号国家大学科技园2#孵化楼2楼218室 ）（纬经度）
//	升龙又一城：34.813292 , 113.552154 （科学大道与长椿路交汇处 ）
	
	protected double zzitcast_lat = 34.798413;
	protected double zzitcast_lng = 113.551107;
	
	protected double sl_lat = 34.813292;
	protected double sl_lng = 113.552154;
	
	
	
	/**
	 * 郑州传智所在地
	 */
	protected LatLng zzitcast = new LatLng(zzitcast_lat, zzitcast_lng);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ctx = this;
		
		/**
		 * 必须先  初始化 且参数是  getApplicationContext() 不能是 this 
		 */
		SDKInitializer.initialize(getApplicationContext());
		
		setContentView(R.layout.activity_main);
		
		mapView = (MapView) findViewById(R.id.mapView);
		
		baiduMap = mapView.getMap();
		
		// 初始化设置 
		// 设置中心点 
		MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(zzitcast);
		baiduMap.setMapStatus(mapStatusUpdate);
		
		// 设置缩放级别
//		zoom - 缩放级别 [3, 19]
		mapStatusUpdate = MapStatusUpdateFactory.zoomTo(16); // 缩放至 16 
		baiduMap.setMapStatus(mapStatusUpdate);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		mapView.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}
	


}
