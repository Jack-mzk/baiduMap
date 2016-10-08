package zz.itcast.mapz17;

import android.os.Bundle;

import com.baidu.mapapi.map.TextOptions;

public class TextOptionsDemo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		drawText();
		
	}

	private void drawText() {
		// 创建文字覆盖物
		TextOptions textOpt = new TextOptions();
		
		// 位置
		textOpt.position(zzitcast);
		
		// 内容
		textOpt.text("郑州传智播客");
		
		textOpt.fontColor(0xffff0000); // 颜色 
		
		textOpt.bgColor(0x88aaaaaa); // 背景色
		
		textOpt.fontSize(26); // 文字大小
		
		textOpt.rotate(30); // 旋转 30 度
		
		// 添加覆盖物
		baiduMap.addOverlay(textOpt);
		
	}
}
