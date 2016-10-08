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

	// ��ʾ��ͼ���Ǹ� view 
	private MapView mapView;
	
	// ��ðٶȵ�ͼ
	private BaiduMap baiduMap;

	protected Context ctx;


//	֣�ݴ��ǣ�34.798413, 113.551107,������·11�Ź��Ҵ�ѧ�Ƽ�԰2#����¥2¥218�� ����γ���ȣ�
//	������һ�ǣ�34.813292 , 113.552154 ����ѧ����볤��·���㴦 ��
	
	/**
	 * ֣�ݴ������ڵ�
	 */
	private LatLng zzitcast = new LatLng(34.798413, 113.551107);
	
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
		
		
		// ע�� KEY ��֤ʧ�ܵ� �㲥 
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR); // key ����ʱ�� �㲥
		filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR); // key ����ʱ�� �㲥
//		registerReceiver(keyReceiver, filter); // MainActivity ���Ѿ�ע���� 
		
		
		// ��ʼ������ 
		// �������ĵ� 
		MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(zzitcast);
		baiduMap.setMapStatus(mapStatusUpdate);
		
		// �������ż���
//		zoom - ���ż��� [3, 19]
		mapStatusUpdate = MapStatusUpdateFactory.zoomTo(16); // ������ 16 
		baiduMap.setMapStatus(mapStatusUpdate);
		
	}

	private BroadcastReceiver keyReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)){
				// KEY  ��֤���� 
				Toast.makeText(ctx, "KEY ��֤���� ", 1).show();
			}else if(intent.getAction().equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)){
				// ������� 
				Toast.makeText(ctx, "������� ", 1).show();
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
		case KeyEvent.KEYCODE_1: // �Ŵ�
			
			mapStatusUpdate = MapStatusUpdateFactory.zoomIn();
			baiduMap.setMapStatus(mapStatusUpdate);
			
			break;
		case KeyEvent.KEYCODE_2: // ��С
			mapStatusUpdate = MapStatusUpdateFactory.zoomOut(); 
			baiduMap.setMapStatus(mapStatusUpdate);
			
			break;
		case KeyEvent.KEYCODE_3: // ��ת
			
			float rotate = baiduMap.getMapStatus().rotate; // ��õ�ǰ�ĽǶ�
			
			MapStatus build = new MapStatus.Builder().rotate(rotate+30).build();
			mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(build);
			
			baiduMap.setMapStatus(mapStatusUpdate);
			
			
			break;
		case KeyEvent.KEYCODE_4: // ����
			float overlook = baiduMap.getMapStatus().overlook; // ��ǰ���ӽǶ�
			 MapStatus build2 = new MapStatus.Builder().overlook(overlook-15).build();
			 mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(build2);
			baiduMap.setMapStatus(mapStatusUpdate);
			
			break;
		case KeyEvent.KEYCODE_5: // �ص���ʼ��
			
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
