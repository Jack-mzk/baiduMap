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
	 * ��ǰҳ
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
		
		option.city("֣��"); // ��������
		
		option.keyword("KTV");
		
		option.pageNum(pageNum);
		
		// ��ʼ���� 
		poiSearch.searchInCity(option);
		
		// ���������ļ���
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
			
			KTVPoiOverLay poiOverlay = new KTVPoiOverLay(baiduMap);
			poiOverlay.setData(result); // ���ý��
			poiOverlay.addToMap(); // �������ͼ
			poiOverlay.zoomToSpan(); // ���������ʵĴ�С
			
			// ����poi ����¼�����
			baiduMap.setOnMarkerClickListener(poiOverlay);
			
		}
		
		@Override
		// ���POI����������ʱ������
		public void onGetPoiDetailResult(PoiDetailResult detailResult) {
			
//			if(SearchResult.ERRORNO.NO_ERROR == detailResult.error){// û�д�
				
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
		 * �����ĳ�� poi ��Ȥ��ʱ������
		 */
		public boolean onPoiClick(int index) {
			
			System.out.println("index::"+index);
			
			// ��õ����POI ��Ϣ
			PoiInfo poiInfo = getPoiResult().getAllPoi().get(index);
			// ��ѯ����
			
			PoiDetailSearchOption detailOption = new PoiDetailSearchOption();
			detailOption.poiUid(poiInfo.uid); // ���� Ψһ���к�
			// ��������
			poiSearch.searchPoiDetail(detailOption);
			return true;
		}
		
		
	}
	
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_2: // ������һҳ
			pageNum++;
			searchInCity();
			break;

		}
		
		return super.onKeyDown(keyCode, event);
	};
	
	
	
}
