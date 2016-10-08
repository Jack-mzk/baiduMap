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
		
		transOption.city("郑州");
		
		PlanNode fromNode = PlanNode.withLocation(zzitcast);
		transOption.from(fromNode);
		
		PlanNode toNode = PlanNode.withCityNameAndPlaceName("郑州", "二七塔");
		transOption.to(toNode);
		
		// 最少行走
		transOption.policy(TransitRoutePlanOption.TransitPolicy.EBUS_WALK_FIRST);
		
		// 查询路线
		planSearch.transitSearch(transOption);
		
		planSearch.setOnGetRoutePlanResultListener(listener);
		
	}
	
	private OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
		
		@Override
		// 当获得行走路径时，调用
		public void onGetWalkingRouteResult(WalkingRouteResult result) {
			
		}
		
		@Override
		// 当获得公交路线时，调用
		public void onGetTransitRouteResult(TransitRouteResult result) {
			
			// 找一个 overlay 显示路线
			TransitRouteOverlay tranOverlay = new TransitRouteOverlay(baiduMap);
			
			// 获得第一条路线
			TransitRouteLine routeLine = result.getRouteLines().get(0);
			
			tranOverlay.setData(routeLine);
			
			tranOverlay.addToMap();
			
			tranOverlay.zoomToSpan();
			
		}
		
		@Override
		// 当获得驾车路线时，调用
		public void onGetDrivingRouteResult(DrivingRouteResult result) {
			
		}
	};
}
