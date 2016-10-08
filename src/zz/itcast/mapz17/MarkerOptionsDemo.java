package zz.itcast.mapz17;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.hellomap.R;


public class MarkerOptionsDemo extends BaseActivity implements OnMarkerClickListener {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// ��ǩ ������
		drawMarker();
		
		clickMarkerListener();
		
	}

	View popView ;
	
	private TextView popTitle;
	
	private void clickMarkerListener() {
		// ׼�����ݵ�view 
		
		popView = View.inflate(ctx, R.layout.pop, null);
		// ����������� ��ͼ
		popTitle = (TextView) popView.findViewById(R.id.title);
		
		MapViewLayoutParams layoutParams = new MapViewLayoutParams.Builder()
			.height(MapViewLayoutParams.WRAP_CONTENT)
			.width(MapViewLayoutParams.WRAP_CONTENT)
			.layoutMode(MapViewLayoutParams.ELayoutMode.mapMode)
			.position(zzitcast)
			.build();
		
		// ������ٶȵ�ͼ
		mapView.addView(popView, layoutParams);
		
		popView.setVisibility(View.GONE);
		
		// ���ñ�ǩ�ĵ���¼�����
		baiduMap.setOnMarkerClickListener(this);
	}

	private void drawMarker() {
		
		// ������ǩ������ 
		MarkerOptions markerOpt = new MarkerOptions();
		
		markerOpt.position(zzitcast);
		markerOpt.title("֣�ݴ���");
//		markerOpt.rotate(0); // ��ת�Ƕ�
		
		BitmapDescriptor bitmapDesCriptor = BitmapDescriptorFactory.fromResource(R.drawable.eat_icon);
		markerOpt.icon(bitmapDesCriptor);
		
		// ��Ӹ�����
		baiduMap.addOverlay(markerOpt);
		
		
		
		MarkerOptions markerOpt2 = new MarkerOptions();
		markerOpt2.position(new LatLng(zzitcast_lat + 0.001 , zzitcast_lng));
		markerOpt2.title("֣�ݴ��Ǳ���");
//		markerOpt.rotate(0); // ��ת�Ƕ�
		
		BitmapDescriptor bitmap1 = BitmapDescriptorFactory.fromResource(R.drawable.eat_icon);
		BitmapDescriptor bitmap2 = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
		ArrayList<BitmapDescriptor> bitmas = new ArrayList<BitmapDescriptor>();
		bitmas.add(bitmap1);
		bitmas.add(bitmap2);
		
		markerOpt2.icons(bitmas);// ��Ӷ��ͼ��
		markerOpt2.period(20); // ֵԽ�󣬶���Խ��
		
		// ��Ӹ�����
		baiduMap.addOverlay(markerOpt2);
		
		
		// ���ߵ�
		// ������ǩ������ 
		MarkerOptions markerOpt3 = new MarkerOptions();
		
		markerOpt3.position(new LatLng(zzitcast_lat, zzitcast_lng+0.001));
		
		markerOpt3.title("֣�ݴ��Ƕ���");
//		markerOpt.rotate(0); // ��ת�Ƕ�
		
		BitmapDescriptor bitmapDesCriptor3 = BitmapDescriptorFactory.fromResource(R.drawable.eat_icon);
		markerOpt3.icon(bitmapDesCriptor3);
		
		// ��Ӹ�����
		baiduMap.addOverlay(markerOpt3);
		
	}

	@Override
	// �������ǩʱ
	// �����ǣ�����ı�ǩ 
	public boolean onMarkerClick(Marker marker) {
		// ��ñ�ǩ�ı�������
		String title = marker.getTitle();
		popTitle.setText(title);
		
		// ���λ�� 
		LatLng position = marker.getPosition();
		
		
		MapViewLayoutParams layoutParams = new MapViewLayoutParams.Builder()
		.height(MapViewLayoutParams.WRAP_CONTENT)
		.width(MapViewLayoutParams.WRAP_CONTENT)
		.layoutMode(MapViewLayoutParams.ELayoutMode.mapMode)
		.position(position)
		.build();
		
		popView.setVisibility(View.VISIBLE);
		
		mapView.updateViewLayout(popView, layoutParams); // ����view  
		
		return true;
	}
	
	
	
	
}
