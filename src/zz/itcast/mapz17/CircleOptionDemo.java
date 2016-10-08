package zz.itcast.mapz17;

import android.os.Bundle;

import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Stroke;


/**
 * Բ�θ����� 
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
		// 1- ���� Բ�θ�����  
		CircleOptions cricelOpt = new CircleOptions();

		// ����Բ��
		cricelOpt.center(zzitcast);
		
		// ���ð뾶
		cricelOpt.radius(30); // �뾶 30 ��
		// �����ɫ
		cricelOpt.fillColor(0x88ff0000); // ��һ��͸���ȵĺ�ɫ
		
		Stroke stroke = new Stroke( 10,0xff0000ff); // 10������ ��ɫ�߿�
		cricelOpt.stroke(stroke);
		
		// 2- �������ͼ ,  ��Ӹ�����  
		baiduMap.addOverlay(cricelOpt);
		
	}

}
