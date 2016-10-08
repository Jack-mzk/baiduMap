package zz.itcast.mapz17;

import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;

public class WalkingRoutDemo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		searchWalk();
	}

	private void searchWalk() {
		
		// 路线方案搜索中心
		RoutePlanSearch routePlanSearch = RoutePlanSearch.newInstance();
		
		WalkingRoutePlanOption option = new WalkingRoutePlanOption();
		
		
		PlanNode fromNode = PlanNode.withLocation(zzitcast);
		option.from(fromNode);
		
		PlanNode toNode = PlanNode.withLocation(new LatLng(sl_lat, sl_lng));
		option.to(toNode);
		
		// 查询路线
		routePlanSearch.walkingSearch(option);
		
		routePlanSearch.setOnGetRoutePlanResultListener(listener);
		
	}

	private OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
		
		@Override
		// 当获得行走路径时，调用
		public void onGetWalkingRouteResult(WalkingRouteResult result) {
			
			if(result.error ==SearchResult.ERRORNO.NO_ERROR){
				// 找一个相应的 overlay 显示数据
				
				WalkingRouteOverlay walkOverLay = new WalkingRouteOverlay(baiduMap);
				
				// 获得返回结果中的第一条路线
				WalkingRouteLine walkingRouteLine = result.getRouteLines().get(0);
				
				walkOverLay.setData(walkingRouteLine); // 设置数据
				
				walkOverLay.addToMap();
				
				walkOverLay.zoomToSpan();
				
			}else{
				Toast.makeText(ctx, "没有相应的路线", 1).show();
			}
			
			
			
		}
		
		@Override
		// 当获得公交路线时，调用
		public void onGetTransitRouteResult(TransitRouteResult result) {
		}
		
		@Override
		// 当获得驾车路线时，调用
		public void onGetDrivingRouteResult(DrivingRouteResult result) {
			
		}
	};
}
