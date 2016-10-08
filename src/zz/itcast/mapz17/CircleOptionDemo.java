package zz.itcast.mapz17;

import android.os.Bundle;

import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Stroke;


/**
 * 圆形覆盖物 
 * @author Administrator
 *
 */
public class CircleOptionDemo extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		drawCircle();
	}

	private void drawCircle() {
		// 1- 创建 圆形覆盖物  
		CircleOptions cricelOpt = new CircleOptions();

		// 设置圆心
		cricelOpt.center(zzitcast);
		
		// 设置半径
		cricelOpt.radius(30); // 半径 30 米
		// 填充颜色
		cricelOpt.fillColor(0x88ff0000); // 有一定透明度的红色
		
		Stroke stroke = new Stroke( 10,0xff0000ff); // 10个象素 兰色边框
		cricelOpt.stroke(stroke);
		
		// 2- 添加至地图 ,  添加覆盖物  
		baiduMap.addOverlay(cricelOpt);
		
	}

}
