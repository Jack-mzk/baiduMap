package zz.itcast.mapz17;

import android.os.Bundle;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.hellomap.R;

public class LocationDemo extends BaseActivity {

	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();

	
	/**
	 * 百度地图 坐标查询：
	 * http://api.map.baidu.com/lbsapi/getpoint/index.html
	 * 
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数

		initLocation();
		
		// 开始定位 
		mLocationClient.start();
		
	}

	/**
	 * 初始化定位参数
	 */
	private void initLocation() {
		
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
		
		int span = 0;
		option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
		
		option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
		option.setOpenGps(true);// 可选，默认false,设置是否使用gps
		
//		option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		
		option.setIgnoreKillProcess(false);// 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
		option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
		
		mLocationClient.setLocOption(option);
	}
	
	
	/**
	 * 获得位置时的 回调对象
	 * @author Administrator
	 *
	 */
	public class MyLocationListener implements BDLocationListener {
		 
        @Override
        // 获得位置时调用 
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            
            Toast.makeText(LocationDemo.this, sb.toString(), 1).show();
            
            // 停止定位
            mLocationClient.stop();
            
            // 添加标签覆盖物，显示位置
            
            MarkerOptions markerOpt = new MarkerOptions();
            
            markerOpt.title("我的位置");
            
            BitmapDescriptor bitmapDesc = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
			markerOpt.icon(bitmapDesc);
            
			markerOpt.position(new LatLng(location.getLatitude(), location.getLongitude()));
            
            // 添加至地图
			baiduMap.addOverlay(markerOpt);
            
        }
	}

}
