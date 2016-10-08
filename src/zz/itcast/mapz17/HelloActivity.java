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

public class HelloActivity extends Activity {

	// 显示地图的那个 view 
	private MapView mapView;
	
	// 获得百度地图
	private BaiduMap baiduMap;

	protected Context ctx;


//	郑州传智：34.798413, 113.551107,（长椿路11号国家大学科技园2#孵化楼2楼218室 ）（纬经度）
//	升龙又一城：34.813292 , 113.552154 （科学大道与长椿路交汇处 ）
	
	/**
	 * 郑州传智所在地
	 */
	private LatLng zzitcast = new LatLng(34.798413, 113.551107);
	
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
		
		
		// 注册 KEY 验证失败的 广播 
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR); // key 错误时的 广播
		filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR); // key 错误时的 广播
//		registerReceiver(keyReceiver, filter); // MainActivity 中已经注册了 
		
		
		// 初始化设置 
		// 设置中心点 
		MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(zzitcast);
		baiduMap.setMapStatus(mapStatusUpdate);
		
		// 设置缩放级别
//		zoom - 缩放级别 [3, 19]
		mapStatusUpdate = MapStatusUpdateFactory.zoomTo(16); // 缩放至 16 
		baiduMap.setMapStatus(mapStatusUpdate);
		
	}

	private BroadcastReceiver keyReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)){
				// KEY  验证错误 
				Toast.makeText(ctx, "KEY 验证错误 ", 1).show();
			}else if(intent.getAction().equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)){
				// 网络错误 
				Toast.makeText(ctx, "网络错误 ", 1).show();
			}
			
		}
	};;
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		mapView.onResume();
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		MapStatusUpdate mapStatusUpdate;
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_1: // 放大
			
			mapStatusUpdate = MapStatusUpdateFactory.zoomIn();
			baiduMap.setMapStatus(mapStatusUpdate);
			
			break;
		case KeyEvent.KEYCODE_2: // 缩小
			mapStatusUpdate = MapStatusUpdateFactory.zoomOut(); 
			baiduMap.setMapStatus(mapStatusUpdate);
			
			break;
		case KeyEvent.KEYCODE_3: // 旋转
			
			float rotate = baiduMap.getMapStatus().rotate; // 获得当前的角度
			
			MapStatus build = new MapStatus.Builder().rotate(rotate+30).build();
			mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(build);
			
			baiduMap.setMapStatus(mapStatusUpdate);
			
			
			break;
		case KeyEvent.KEYCODE_4: // 俯视
			float overlook = baiduMap.getMapStatus().overlook; // 当前府视角度
			 MapStatus build2 = new MapStatus.Builder().overlook(overlook-15).build();
			 mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(build2);
			baiduMap.setMapStatus(mapStatusUpdate);
			
			break;
		case KeyEvent.KEYCODE_5: // 回到初始点
			
			MapStatus build3 = new MapStatus.Builder().target(zzitcast).build();
			mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(build3);
			baiduMap.setMapStatus(mapStatusUpdate);
			
			break;
		}
		return super.onKeyDown(keyCode, event);
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
