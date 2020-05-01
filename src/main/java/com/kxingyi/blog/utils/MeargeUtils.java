package com.kxingyi.blog.utils;

import org.apache.commons.collections4.map.HashedMap;

import java.util.*;

/**
 * @author kxingyi
 */
public class MeargeUtils {
	/**
	 *@QualifiedClassName:com.kxingyi.blog.utils.MeargeUtils
	 *@ClassName:mergeList
	 *@Description:连接两个结果集（List<Map<String,Object>>）,参数list1相当于主表，参数list2为副表，fieldName为两个结果集要通过那个字段进行连接
	 *@Author:byliu
	 *@Date:2020/4/24 16:19
	 *@Paramter:[list1, list2, fieldName]
	 *@Return:java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	*/
	public static List<Map<String, Object>> mergeList(List<Map<String, Object>> list1,List<Map<String, Object>> list2,String fieldName) {
		List<Map<String, Object>> res = new ArrayList<>();
		Map<String, Object> newMap1 = new HashMap<>();
		Map<String, Object> newMap2 = new HashMap<>();
		for (Map<String, Object> map : list1) {
			newMap1.put((String) map.get(fieldName), map);
		}
		for (Map<String, Object> map : list2) {
			newMap2.put((String) map.get(fieldName), map);
		}
		
		Set<String> keySet = newMap1.keySet();
		for (String key : keySet) {
			Map<String, Object> map1 = (Map<String, Object>) newMap1.get(key);
			Map<String, Object> map2 = (Map<String, Object>) newMap2.get(key);
			if(newMap2.get(key) != null) {
				map1.putAll(map2);
			}
			res.add(map1);
		}
		return res;
	}  
	
	public static void main(String[] args) {
		List<Map<String, Object>> list1 = new LinkedList<Map<String, Object>>();
		List<Map<String, Object>> list2 = new LinkedList<Map<String, Object>>();
		for (int i = 0; i < 30; i++) {
			Map<String, Object> map1 = new HashedMap<>();
			map1.put("id", i+"");
			map1.put("name", "刘"+i);
			list1.add(map1);
		}
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map1 = new HashedMap<>();
			map1.put("id", i+"");
			map1.put("tel", "000000"+i);
			list2.add(map1);
		}
		List<Map<String, Object>> mearge = mergeList(list1, list2, "id");
		for (Map<String, Object> map : mearge) {
			System.out.println(map);
		}
	}

}
