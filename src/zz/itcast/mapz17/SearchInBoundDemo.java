package zz.itcast.mapz17;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import android.os.Bundle;

public class SearchInBoundDemo extends BaseActivity {

	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		searchInBound();
	}

	private void searchInBound() {
		
		// 兴趣点搜索引擎
		PoiSearch poiSearch = PoiSearch.newInstance();
		
		// 范围搜索 的选项
		PoiBoundSearchOption option = new PoiBoundSearchOption();
		
		// LatLngBounds 以经纬度表示的范围
		
		LatLngBounds latlngBounds = new LatLngBounds.Builder()
		.include(new LatLng(zzitcast_lat + 0.01, zzitcast_lng + 0.01)) // 东北角
		.include(new LatLng(zzitcast_lat - 0.01 , zzitcast_lng - 0.01)) // 西南角
		.build();
		
		option.bound(latlngBounds); // 指定范围
		
		option.keyword("加油站"); // 搜索的关键字
		
		option.pageNum(0); // 要第一页
		
		poiSearch.searchInBound(option); // 开始搜索 
		
		poiSearch.setOnGetPoiSearchResultListener(searchResultListener);
		
	}
	private OnGetPoiSearchResultListener searchResultListener = new OnGetPoiSearchResultListener() {
		
		@Override
		// 获得搜索结果时，调用
		public void onGetPoiResult(PoiResult result) {
			
			// 兴趣点覆盖物
			PoiOverlay poiOverlay = new PoiOverlay(baiduMap);
			
			poiOverlay.setData(result); // 设置结果
			
			poiOverlay.addToMap(); // 添加至地图
			
			poiOverlay.zoomToSpan(); // 缩放至合适的大小
			
		}
		
		@Override
		// 获得搜索的详情时，调用
		public void onGetPoiDetailResult(PoiDetailResult detailResult) {
			
		}
	};
}
