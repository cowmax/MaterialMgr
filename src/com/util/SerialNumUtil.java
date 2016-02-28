package com.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.bean.BMaterialBrand;
import com.bean.ExtraBrand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SerialNumUtil {

	public static void main(String[] args) {
		List<ExtraBrand> list = new ArrayList<ExtraBrand>();
		
		Gson gson = new Gson();
		list = gson.fromJson("[{\'brand\':\'A\',\'brandName\':\'Amii\'},{\'brand\':\'R\',\'brandName\':\'Redifine\'}]",
							new TypeToken<List<ExtraBrand>>(){}.getType());
		System.out.println(list.size());
		
//		Gson gson = new Gson();

//		String json = "{\'brand\':\'A\',\'brandName\':\'Amii\'}";
//		ExtraBrand brand = gson.fromJson(json, ExtraBrand.class);
	}
}
