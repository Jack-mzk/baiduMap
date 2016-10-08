package zz.itcast.mapz17;

import com.baidu.mapapi.map.BaiduMap;

import android.os.Bundle;
import android.view.KeyEvent;

public class LayerDemo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_1: // 地图原图
			
			baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			
			break;
		case KeyEvent.KEYCODE_2: // 卫星图
			baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
			
			break;
		case KeyEvent.KEYCODE_3: // 交通状况图
			
			// 切换交通图
			baiduMap.setTrafficEnabled(!baiduMap.isTrafficEnabled());
			
			break;
		default:
			break;
		}
		
		
		return super.onKeyDown(keyCode, event);
	}
	
}
