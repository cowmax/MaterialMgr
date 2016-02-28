/**
 * 显示图片
 */
function imageUrl()	{
		//接收照片的名称
		var imgName=$("#imgName").val();

		//接收照片的路径
		var imgUrl=$("#imgUlr").val();

		//得到完整的路径
		var imgWholeUrl=imgUrl+imgName+'.jpg';
		
		//给照片div赋值
		$("#imgrefresh").attr("src", imgWholeUrl)
		

}
	
/**
 * 下载原图
 */
function downloadImg(){
	//获取图片的名称
	var imgName=$("#imgName").val();
	
	//接收照片的路径
	var imgUrl=$("#imgUlr").val();

	//得到完整的路径
	var imgWholeUrl=imgUrl+imgName+'.jpg';
	
	window.location = 'imgmakeImg.action?imgWholeUrl='+ imgWholeUrl;
}

/**
 * 弹出照片类型
 */
function addPhotoType(){
	
	$("#photoTypeWin").window('open');
}

/**
 * 弹出照片颜色
 */
function addPhotoColor(){
	//获取图片的名称
	var imgName=$("#imgName").val();
	
	//接收照片的路径
	var imgUrl=$("#imgUlr").val();

	//得到完整的路径
	var imgWholeUrl=imgUrl+imgName+'.jpg';
	
	//给photoColorWin  DIV赋值
	//var str = 'imgtransmitUrl.action?imgWholeUrl=' + imgWholeUrl;
	
	//$('#photoColorWin').window('refresh', str);
	
	 $("#imgeColor").attr("src", imgWholeUrl);
	
	 //打开弹出框
	 $("#photoColorWin").window('open');
	//$('#photoColorWin').dialog('refresh', str);
	
}

	/**
	 * 保存照片颜色
	 */
	function saveColor(){
		
		//获取照片颜色
		var imageColor=$("#color").val();
		
		//赋值给select
		// $("#photoColor").attr("value", imageColor);
		 $('#photoColor').combobox('setValues', [imageColor]);
		 
		//关闭弹出框
		 $('#photoColorWin').window('close'); 
		
	}


	
	/**
	 * 返回
	 */
	function goBack(){
	
		//关闭弹出框
		 $('#photoColorWin').window('close');
	
	}

	/**
	 * 返回
	 */
	function goBackImage(){
		//关闭弹出框
		 $('#imageWin').window('close');
	}
	
	/**
	 * 关于验证
	 */
	$.extend($.fn.validatebox.defaults.rules, {
	   //验证面料价格
		validateImgSize: {
			validator: function (value) {
				// if(value=='') return true;    // 为空时不验证
				return /^[0-9]*[1-9][0-9]*\.{0,1}[0-9]{0,2}$/.test(value);
		        },
		        message: '请输入数字'
	    } 

	});

	
