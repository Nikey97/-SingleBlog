package cn.supspider.Utils;

import java.util.List;

import net.sf.json.JSONArray;

public class ToJsonType {
		//ListתJson������
		public JSONArray List2Json(List<?> list) {
			JSONArray json=JSONArray.fromObject(list);
			return json;
		}
}
