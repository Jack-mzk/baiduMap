package zz.itcast.mapz17;

import android.os.Bundle;

import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

public class TransitRouteDemo extends BaseActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		searchTrans();
		
	}

	private void searchTrans() {
		
		RoutePlanSearch planSearch = RoutePlanSearch.newInstance();
		
		
		TransitRoutePlanOption transOption = new TransitRoutePlanOption();
		
		transOption.city("֣��");
		
		PlanNode fromNode = PlanNode.withLocation(zzitcast);
		transOption.from(fromNode);
		
		PlanNode toNode = PlanNode.withCityNameAndPlaceName("֣��", "������");
		transOption.to(toNode);
		
		// ��������
		transOption.policy(TransitRoutePlanOption.TransitPolicy.EBUS_WALK_FIRST);
		
		// ��ѯ·��
		planSearch.transitSearch(transOption);
		
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
			
			// ��һ�� overlay ��ʾ·��
			TransitRouteOverlay tranOverlay = new TransitRouteOverlay(baiduMap);
			
			// ��õ�һ��·��
			TransitRouteLine routeLine = result.getRouteLines().get(0);
			
			tranOverlay.setData(routeLine);
			
			tranOverlay.addToMap();
			
			tranOverlay.zoomToSpan();
			
		}
		
		@Override
		// ����üݳ�·��ʱ������
		public void onGetDrivingRouteResult(DrivingRouteResult result) {
			
		}
	};
}
