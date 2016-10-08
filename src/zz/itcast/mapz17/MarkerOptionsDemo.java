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
		
		// 标签 覆盖物
		drawMarker();
		
		clickMarkerListener();
		
	}

	View popView ;
	
	private TextView popTitle;
	
	private void clickMarkerListener() {
		// 准备气泡的view 
		
		popView = View.inflate(ctx, R.layout.pop, null);
		// 将气泡添加至 地图
		popTitle = (TextView) popView.findViewById(R.id.title);
		
		MapViewLayoutParams layoutParams = new MapViewLayoutParams.Builder()
			.height(MapViewLayoutParams.WRAP_CONTENT)
			.width(MapViewLayoutParams.WRAP_CONTENT)
			.layoutMode(MapViewLayoutParams.ELayoutMode.mapMode)
			.position(zzitcast)
			.build();
		
		// 添加至百度地图
		mapView.addView(popView, layoutParams);
		
		popView.setVisibility(View.GONE);
		
		// 设置标签的点击事件监听
		baiduMap.setOnMarkerClickListener(this);
	}

	private void drawMarker() {
		
		// 创建标签覆盖物 
		MarkerOptions markerOpt = new MarkerOptions();
		
		markerOpt.position(zzitcast);
		markerOpt.title("郑州传智");
//		markerOpt.rotate(0); // 旋转角度
		
		BitmapDescriptor bitmapDesCriptor = BitmapDescriptorFactory.fromResource(R.drawable.eat_icon);
		markerOpt.icon(bitmapDesCriptor);
		
		// 添加覆盖物
		baiduMap.addOverlay(markerOpt);
		
		
		
		MarkerOptions markerOpt2 = new MarkerOptions();
		markerOpt2.position(new LatLng(zzitcast_lat + 0.001 , zzitcast_lng));
		markerOpt2.title("郑州传智北边");
//		markerOpt.rotate(0); // 旋转角度
		
		BitmapDescriptor bitmap1 = BitmapDescriptorFactory.fromResource(R.drawable.eat_icon);
		BitmapDescriptor bitmap2 = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
		ArrayList<BitmapDescriptor> bitmas = new ArrayList<BitmapDescriptor>();
		bitmas.add(bitmap1);
		bitmas.add(bitmap2);
		
		markerOpt2.icons(bitmas);// 添加多个图标
		markerOpt2.period(20); // 值越大，动画越慢
		
		// 添加覆盖物
		baiduMap.addOverlay(markerOpt2);
		
		
		// 东边的
		// 创建标签覆盖物 
		MarkerOptions markerOpt3 = new MarkerOptions();
		
		markerOpt3.position(new LatLng(zzitcast_lat, zzitcast_lng+0.001));
		
		markerOpt3.title("郑州传智东边");
//		markerOpt.rotate(0); // 旋转角度
		
		BitmapDescriptor bitmapDesCriptor3 = BitmapDescriptorFactory.fromResource(R.drawable.eat_icon);
		markerOpt3.icon(bitmapDesCriptor3);
		
		// 添加覆盖物
		baiduMap.addOverlay(markerOpt3);
		
	}

	@Override
	// 当点击标签时
	// 参数是，点击的标签 
	public boolean onMarkerClick(Marker marker) {
		// 获得标签的标题文字
		String title = marker.getTitle();
		popTitle.setText(title);
		
		// 获得位置 
		LatLng position = marker.getPosition();
		
		
		MapViewLayoutParams layoutParams = new MapViewLayoutParams.Builder()
		.height(MapViewLayoutParams.WRAP_CONTENT)
		.width(MapViewLayoutParams.WRAP_CONTENT)
		.layoutMode(MapViewLayoutParams.ELayoutMode.mapMode)
		.position(position)
		.build();
		
		popView.setVisibility(View.VISIBLE);
		
		mapView.updateViewLayout(popView, layoutParams); // 更新view  
		
		return true;
	}
	
	
	
	
}
