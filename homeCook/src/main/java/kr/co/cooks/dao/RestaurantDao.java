package kr.co.cooks.dao;

import java.util.List;

import kr.co.cooks.vo.RestaurantFileListVO;
import kr.co.cooks.vo.RestaurantFileVO;
import kr.co.cooks.vo.RestaurantVO;

public interface RestaurantDao {
	public List<RestaurantFileListVO> getRestaurant();
	public void addRestaurant(RestaurantVO restaurantVO);
	public void addFileUpload(RestaurantFileVO restaurantFileVO);
	public RestaurantVO rNumcheck(String r_Num);
	public void restaurantDelete(String r_Num);
	
	//음식 유형별 식당 리스트
	public List<RestaurantFileListVO> restaurantTypeList(String r_food_type);

}
