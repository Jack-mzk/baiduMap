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
		
		// ·�߷�����������
		RoutePlanSearch routePlanSearch = RoutePlanSearch.newInstance();
		
		WalkingRoutePlanOption option = new WalkingRoutePlanOption();
		
		
		PlanNode fromNode = PlanNode.withLocation(zzitcast);
		option.from(fromNode);
		
		PlanNode toNode = PlanNode.withLocation(new LatLng(sl_lat, sl_lng));
		option.to(toNode);
		
		// ��ѯ·��
		routePlanSearch.walkingSearch(option);
		
		routePlanSearch.setOnGetRoutePlanResultListener(listener);
		
	}

	private OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
		
		@Override
		// ���������·��ʱ������
		public void onGetWalkingRouteResult(WalkingRouteResult result) {
			
			if(result.error ==SearchResult.ERRORNO.NO_ERROR){
				// ��һ����Ӧ�� overlay ��ʾ����
				
				WalkingRouteOverlay walkOverLay = new WalkingRouteOverlay(baiduMap);
				
				// ��÷��ؽ���еĵ�һ��·��
				WalkingRouteLine walkingRouteLine = result.getRouteLines().get(0);
				
				walkOverLay.setData(walkingRouteLine); // ��������
				
				walkOverLay.addToMap();
				
				walkOverLay.zoomToSpan();
				
			}else{
				Toast.makeText(ctx, "û����Ӧ��·��", 1).show();
			}
			
			
			
		}
		
		@Override
		// ����ù���·��ʱ������
		public void onGetTransitRouteResult(TransitRouteResult result) {
		}
		
		@Override
		// ����üݳ�·��ʱ������
		public void onGetDrivingRouteResult(DrivingRouteResult result) {
			
		}
	};
}
