package zz.itcast.mapz17;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

public class SearchNearByDemo extends BaseActivity {

	/**
	 * 当前页数
	 */
	private int pageNum = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		searchNearBy();
		
	}

	private void searchNearBy() {
		
		PoiSearch poiSearch = PoiSearch.newInstance();
		
		
		PoiNearbySearchOption option = new PoiNearbySearchOption();
		
		option.keyword("KTV"); // 搜索关键字
		
		option.location(zzitcast); //  设置中心点位置
		
		option.radius(10000); // 设置半径 10 公里
		
		option.pageNum(pageNum); //  当前第几页
		
		
		poiSearch.searchNearby(option); //  开始搜索 
		
		
		poiSearch.setOnGetPoiSearchResultListener(searchResultListener);
		
	}
	
	private OnGetPoiSearchResultListener searchResultListener = new OnGetPoiSearchResultListener() {
		
		@Override
		// 获得搜索结果时，调用
		public void onGetPoiResult(PoiResult result) {
			
			int totalPoiNum = result.getTotalPoiNum();
			int totalPageNum = result.getTotalPageNum();
			
			int currentPageNum = result.getCurrentPageNum();
			
			Toast.makeText(ctx, "总共有："+totalPoiNum+"个,共有："+totalPageNum+"页,当前是第 "+currentPageNum+"页", 1).show();
			
			// 兴趣点覆盖物
			
			baiduMap.clear(); // 清空之前的 poi覆盖物
			
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
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_2: // 搜索下一页
			pageNum++;
			searchNearBy();
			break;

		}
		
		return super.onKeyDown(keyCode, event);
	};
	
	
	
}
