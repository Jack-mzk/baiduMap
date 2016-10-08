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
	 * �ٶȵ�ͼ �����ѯ��
	 * http://api.map.baidu.com/lbsapi/getpoint/index.html
	 * 
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mLocationClient = new LocationClient(getApplicationContext()); // ����LocationClient��
		mLocationClient.registerLocationListener(myListener); // ע���������

		initLocation();
		
		// ��ʼ��λ 
		mLocationClient.start();
		
	}

	/**
	 * ��ʼ����λ����
	 */
	private void initLocation() {
		
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// ��ѡ��Ĭ�ϸ߾��ȣ����ö�λģʽ���߾��ȣ��͹��ģ����豸
		option.setCoorType("bd09ll");// ��ѡ��Ĭ��gcj02�����÷��صĶ�λ�������ϵ
		
		int span = 0;
		option.setScanSpan(span);// ��ѡ��Ĭ��0��������λһ�Σ����÷���λ����ļ����Ҫ���ڵ���1000ms������Ч��
		
		option.setIsNeedAddress(true);// ��ѡ�������Ƿ���Ҫ��ַ��Ϣ��Ĭ�ϲ���Ҫ
		option.setOpenGps(true);// ��ѡ��Ĭ��false,�����Ƿ�ʹ��gps
		
//		option.setLocationNotify(true);// ��ѡ��Ĭ��false�������Ƿ�gps��Чʱ����1S1��Ƶ�����GPS���
		
		option.setIgnoreKillProcess(false);// ��ѡ��Ĭ��true����λSDK�ڲ���һ��SERVICE�����ŵ��˶������̣������Ƿ���stop��ʱ��ɱ��������̣�Ĭ�ϲ�ɱ��
		option.SetIgnoreCacheException(false);// ��ѡ��Ĭ��false�������Ƿ��ռ�CRASH��Ϣ��Ĭ���ռ�
		
		mLocationClient.setLocOption(option);
	}
	
	
	/**
	 * ���λ��ʱ�� �ص�����
	 * @author Administrator
	 *
	 */
	public class MyLocationListener implements BDLocationListener {
		 
        @Override
        // ���λ��ʱ���� 
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
            
            // ֹͣ��λ
            mLocationClient.stop();
            
            // ��ӱ�ǩ�������ʾλ��
            
            MarkerOptions markerOpt = new MarkerOptions();
            
            markerOpt.title("�ҵ�λ��");
            
            BitmapDescriptor bitmapDesc = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
			markerOpt.icon(bitmapDesc);
            
			markerOpt.position(new LatLng(location.getLatitude(), location.getLongitude()));
            
            // �������ͼ
			baiduMap.addOverlay(markerOpt);
            
        }
	}

}
