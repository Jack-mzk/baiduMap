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
		
		// ��Ȥ����������
		PoiSearch poiSearch = PoiSearch.newInstance();
		
		// ��Χ���� ��ѡ��
		PoiBoundSearchOption option = new PoiBoundSearchOption();
		
		// LatLngBounds �Ծ�γ�ȱ�ʾ�ķ�Χ
		
		LatLngBounds latlngBounds = new LatLngBounds.Builder()
		.include(new LatLng(zzitcast_lat + 0.01, zzitcast_lng + 0.01)) // ������
		.include(new LatLng(zzitcast_lat - 0.01 , zzitcast_lng - 0.01)) // ���Ͻ�
		.build();
		
		option.bound(latlngBounds); // ָ����Χ
		
		option.keyword("����վ"); // �����Ĺؼ���
		
		option.pageNum(0); // Ҫ��һҳ
		
		poiSearch.searchInBound(option); // ��ʼ���� 
		
		poiSearch.setOnGetPoiSearchResultListener(searchResultListener);
		
	}
	private OnGetPoiSearchResultListener searchResultListener = new OnGetPoiSearchResultListener() {
		
		@Override
		// ����������ʱ������
		public void onGetPoiResult(PoiResult result) {
			
			// ��Ȥ�㸲����
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
}
