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

	// ��ʾ��ͼ���Ǹ� view 
	protected MapView mapView;
	
	// ��ðٶȵ�ͼ
	protected BaiduMap baiduMap;

	protected Context ctx;


//	֣�ݴ��ǣ�34.798413, 113.551107,������·11�Ź��Ҵ�ѧ�Ƽ�԰2#����¥2¥218�� ����γ���ȣ�
//	������һ�ǣ�34.813292 , 113.552154 ����ѧ����볤��·���㴦 ��
	
	protected double zzitcast_lat = 34.798413;
	protected double zzitcast_lng = 113.551107;
	
	protected double sl_lat = 34.813292;
	protected double sl_lng = 113.552154;
	
	
	
	/**
	 * ֣�ݴ������ڵ�
	 */
	protected LatLng zzitcast = new LatLng(zzitcast_lat, zzitcast_lng);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ctx = this;
		
		/**
		 * ������  ��ʼ�� �Ҳ�����  getApplicationContext() ������ this 
		 */
		SDKInitializer.initialize(getApplicationContext());
		
		setContentView(R.layout.activity_main);
		
		mapView = (MapView) findViewById(R.id.mapView);
		
		baiduMap = mapView.getMap();
		
		// ��ʼ������ 
		// �������ĵ� 
		MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(zzitcast);
		baiduMap.setMapStatus(mapStatusUpdate);
		
		// �������ż���
//		zoom - ���ż��� [3, 19]
		mapStatusUpdate = MapStatusUpdateFactory.zoomTo(16); // ������ 16 
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
