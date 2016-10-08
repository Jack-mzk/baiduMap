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
		// �������ָ�����
		TextOptions textOpt = new TextOptions();
		
		// λ��
		textOpt.position(zzitcast);
		
		// ����
		textOpt.text("֣�ݴ��ǲ���");
		
		textOpt.fontColor(0xffff0000); // ��ɫ 
		
		textOpt.bgColor(0x88aaaaaa); // ����ɫ
		
		textOpt.fontSize(26); // ���ִ�С
		
		textOpt.rotate(30); // ��ת 30 ��
		
		// ��Ӹ�����
		baiduMap.addOverlay(textOpt);
		
	}
}
