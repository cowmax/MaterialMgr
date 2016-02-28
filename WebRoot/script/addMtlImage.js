  
   	//弹出添加照片
	function addImage(){
		$("#imageWin").window('open');
		$("#imageWin").window('refresh');
	}
	
	 /**
	 * 根据制定页面跳转照片类型
	 */
	function reload() {
		var offset = document.getElementById("offset").value;
		var idx = (offset == null) ? 0 : parseInt(offset) - 1;
		
		//刷新页面
	    var trgUrl  = 'itypeshowImageType.action?offset=' + idx;	
	    $('#photoTypeWin').window('refresh', trgUrl);
	}
	
	
	/**
	* 翻到给定偏移量的页面照片类型
	*/
	function turnPage(offset){
	
	//刷新页面
	var trgUrl = 'itypeshowImageType.action?offset=' + offset;	
	$('#photoTypeWin').window('refresh', trgUrl);
	}

	/**
	*删除照片类型
	*/
	function delImageType(imgType,imgTypeName){
		
		var msg = "确定要删除 ["+imgTypeName+"] 吗？";
		
		if((confirm(msg) == true)){
		
			$.ajax({
				type : 'POST',
				url : 'itypedelPhoto.action',
				data : {
					'imgType' : imgType
				},
				dataType : 'json',
				success : function(data) {
						if (data == true) {
							$.messager.show({
								msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">活动类型已被引用，不允许删除！</div></div>',
								timeout : 800,
								showSpeed : 200,
								showType : 'show',
								style : {
									left: $('.datagrid').position().left + $('.datagrid').width()/2 - 150,
									top : $('.datagrid').position().top + $('.datagrid').height()/2 - 80
								}
							});

							//刷新页面
							var trgUrl = 'itypeshowImageType.action';
							$('#photoTypeWin').window('refresh', trgUrl);
					}else{
						return false;
					}
				}
				});
			}
	}
	
	/**
	 *添加照片类型
	 */
	function addImageType() {
		$.ajax({
			url : 'itypeaddPhoto.action',
			type : "POST",
				data : $('#saveImageType').serialize(),
					success : function(data) {
						if (data) {
							$.messager.show({
									msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">照片类型新增成功！</div></div>',
									timeout : 800,
									showSpeed : 200,
									showType : 'show',
									style : {
										left : $('.datagrid').position().left+ $('.datagrid').width()/ 2 - 150,
										top : $('.datagrid').position().top+ $('.datagrid').height()/ 2 - 80
										}
									});

							//刷新页面
							var trgUrl = 'itypeshowImageType.action';
							$('#photoTypeWin').window('refresh', trgUrl);
						} 
					}
				});

	}

	
