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
		
		// 路线搜索
		RoutePlanSearch planSearch = RoutePlanSearch.newInstance();
		
		
		DrivingRoutePlanOption option = new DrivingRoutePlanOption();
		
		PlanNode fromNode = PlanNode.withLocation(zzitcast);
		option.from(fromNode);
		
		PlanNode toNode = PlanNode.withCityNameAndPlaceName("郑州", "二七塔");
		option.to(toNode);
		
		// 行车策略，时间优先
		option.policy(DrivingRoutePlanOption.DrivingPolicy.ECAR_TIME_FIRST);
		
		// 搜索路线 
		planSearch.drivingSearch(option);
		
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
		}
		
		@Override
		// 当获得驾车路线时，调用
		public void onGetDrivingRouteResult(DrivingRouteResult result) {
			
			// 
			DrivingRouteOverlay overLay = new DrivingRouteOverlay(baiduMap);
			
			// 返回的结果中，会有多条路线，我们默认第一条路线
			DrivingRouteLine drivingRouteLine = result.getRouteLines().get(0);
			
			overLay.setData(drivingRouteLine);
			
			overLay.addToMap(); // 添加至地图
			
			overLay.zoomToSpan();
		}
	};
	
}
