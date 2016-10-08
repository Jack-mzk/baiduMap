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
	 * ��ǰҳ��
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
		
		option.keyword("KTV"); // �����ؼ���
		
		option.location(zzitcast); //  �������ĵ�λ��
		
		option.radius(10000); // ���ð뾶 10 ����
		
		option.pageNum(pageNum); //  ��ǰ�ڼ�ҳ
		
		
		poiSearch.searchNearby(option); //  ��ʼ���� 
		
		
		poiSearch.setOnGetPoiSearchResultListener(searchResultListener);
		
	}
	
	private OnGetPoiSearchResultListener searchResultListener = new OnGetPoiSearchResultListener() {
		
		@Override
		// ����������ʱ������
		public void onGetPoiResult(PoiResult result) {
			
			int totalPoiNum = result.getTotalPoiNum();
			int totalPageNum = result.getTotalPageNum();
			
			int currentPageNum = result.getCurrentPageNum();
			
			Toast.makeText(ctx, "�ܹ��У�"+totalPoiNum+"��,���У�"+totalPageNum+"ҳ,��ǰ�ǵ� "+currentPageNum+"ҳ", 1).show();
			
			// ��Ȥ�㸲����
			
			baiduMap.clear(); // ���֮ǰ�� poi������
			
			PoiOverlay poiOverlay = new PoiOverlay(baiduMap);
			
			poiOverlay.setData(result); // ���ý��
			
			poiOverlay.addToMap(); // �������ͼ
			
			poiOverlay.zoomToSpan(); // ���������ʵĴ�С
			
		}
		
		@Override
		// �������������ʱ������
		public void onGetPoiDetailResult(PoiDetailResult detailResult) {
			
		}
	};
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_2: // ������һҳ
			pageNum++;
			searchNearBy();
			break;

		}
		
		return super.onKeyDown(keyCode, event);
	};
	
	
	
}
