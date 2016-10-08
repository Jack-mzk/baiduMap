package zz.itcast.mapz17;

import android.os.Bundle;

import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

public class DrivingRouteDemo extends BaseActivity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		searchDriver();
	}

	private void searchDriver() {
		
		// ·������
		RoutePlanSearch planSearch = RoutePlanSearch.newInstance();
		
		
		DrivingRoutePlanOption option = new DrivingRoutePlanOption();
		
		PlanNode fromNode = PlanNode.withLocation(zzitcast);
		option.from(fromNode);
		
		PlanNode toNode = PlanNode.withCityNameAndPlaceName("֣��", "������");
		option.to(toNode);
		
		// �г����ԣ�ʱ������
		option.policy(DrivingRoutePlanOption.DrivingPolicy.ECAR_TIME_FIRST);
		
		// ����·�� 
		planSearch.drivingSearch(option);
		
		planSearch.setOnGetRoutePlanResultListener(listener);
		
	}
	
	private OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
		
		@Override
		// ���������·��ʱ������
		public void onGetWalkingRouteResult(WalkingRouteResult result) {
		}
		
		@Override
		// ����ù���·��ʱ������
		public void onGetTransitRouteResult(TransitRouteResult result) {
		}
		
		@Override
		// ����üݳ�·��ʱ������
		public void onGetDrivingRouteResult(DrivingRouteResult result) {
			
			// 
			DrivingRouteOverlay overLay = new DrivingRouteOverlay(baiduMap);
			
			// ���صĽ���У����ж���·�ߣ�����Ĭ�ϵ�һ��·��
			DrivingRouteLine drivingRouteLine = result.getRouteLines().get(0);
			
			overLay.setData(drivingRouteLine);
			
			overLay.addToMap(); // �������ͼ
			
			overLay.zoomToSpan();
		}
	};
	
}
