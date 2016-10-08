package zz.itcast.mapz17;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

public class SearchInCityDemo extends BaseActivity {

	/**
	 * 当前页
	 */
	private int pageNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		searchInCity();
		
	}

	private void searchInCity() {
		
		poiSearch = PoiSearch.newInstance();
		
		PoiCitySearchOption option = new PoiCitySearchOption();
		
		option.city("郑州"); // 搜索城市
		
		option.keyword("KTV");
		
		option.pageNum(pageNum);
		
		// 开始搜索 
		poiSearch.searchInCity(option);
		
		// 设置搜索的监听
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
			
			KTVPoiOverLay poiOverlay = new KTVPoiOverLay(baiduMap);
			poiOverlay.setData(result); // 设置结果
			poiOverlay.addToMap(); // 添加至地图
			poiOverlay.zoomToSpan(); // 缩放至合适的大小
			
			// 设置poi 点击事件监听
			baiduMap.setOnMarkerClickListener(poiOverlay);
			
		}
		
		@Override
		// 获得POI搜索的详情时，调用
		public void onGetPoiDetailResult(PoiDetailResult detailResult) {
			
//			if(SearchResult.ERRORNO.NO_ERROR == detailResult.error){// 没有错
				
				String name = detailResult.getName();
				String address = detailResult.getAddress();
				
				Toast.makeText(ctx, name+" , "+address, 1).show();
				
//			}
		}
	};
	private PoiSearch poiSearch;
	
	
	private class KTVPoiOverLay extends PoiOverlay{

		public KTVPoiOverLay(BaiduMap arg0) {
			super(arg0);
		}
		
		@Override
		/**
		 * 当点击某个 poi 兴趣点时，调用
		 */
		public boolean onPoiClick(int index) {
			
			System.out.println("index::"+index);
			
			// 获得点击的POI 信息
			PoiInfo poiInfo = getPoiResult().getAllPoi().get(index);
			// 查询详情
			
			PoiDetailSearchOption detailOption = new PoiDetailSearchOption();
			detailOption.poiUid(poiInfo.uid); // 设置 唯一序列号
			// 搜索详情
			poiSearch.searchPoiDetail(detailOption);
			return true;
		}
		
		
	}
	
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_2: // 搜索下一页
			pageNum++;
			searchInCity();
			break;

		}
		
		return super.onKeyDown(keyCode, event);
	};
	
	
	
}
