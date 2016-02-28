
	/**
	 * 添加类型信息
	 */
	function addType(){
		var mtlType = $("#pmtlType").combobox("getValue");
		var cTypeName = $("#cTypeName").val();
		if(cTypeName==""){
			$.messager.show({
				msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">请填写类型名称！</div></div>',
				timeout : 800,
				showSpeed : 200,
				showType : 'show',
				style : {
					right : '',
					top : 200,
					bottom : ''
				}
			});
		}else{
			$.ajax({
				type : 'POST',
				url : 'materialaddType.action',
				data : {
					'pid':mtlType,
					'typeName':cTypeName
				},
				dataType : 'json',
				success : function(data) {
					if(data){
						var trgUrl = 'materialgetMtlTypeList';
						$('#getMaterialTypeList').window('refresh', trgUrl); 
					}
				}
		
			});
		}
	}
	
	/**
	 * 确认删除
	 * @returns {Boolean}
	 */
	function sureDelType(mtlTypeName,mtlType) {
		var msg = "确定要删除 ["+mtlTypeName+"] 类型吗？";
		
		if (confirm(msg) == true) {
			$.ajax({
				type : 'POST',
				url : 'materialdelType.action',
				data : {
					'delmtlType':mtlType
				},
				dataType : 'json',
				success : function(data) {
					if(data){
						var trgUrl = 'materialgetMtlTypeList';
						$('#getMaterialTypeList').window('refresh', trgUrl);
						$("#mtlType").combobox("setValue","");
						$("#materialCode").textbox('setValue',"");
					}else{
						$.messager.show({
							msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">该面料类型被引用，删除失败！</div></div>',
							timeout : 800,
							showSpeed : 200,
							showType : 'show',
							style : {
								right : '',
								top : 200,
								bottom : ''
							}
						});
					}
				}
		
			});
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 *关闭类型列表窗口
	 */
	function closeTypeListWin(){
		//重新添加option
		$.ajax({
			type:'POST',
			url:'materialaddOrDelType.action',
			dataType : 'json',
			success : function(data){
				document.getElementById("mtlType").options.length=0;//清空select里面的option
				$("#getMaterialTypeList").window('close');//关闭类型列表
				
				var json = data.jsonTypes;
		    	data = $.parseJSON(json);
		    	$("#mtlType").combobox("loadData", data);
			}
		
		});
	}
	
	