	/**
	 *打开修改密码窗口
	 */
	function showChooseWin(){
		$("#productList").window('open');
	}
	
	/**
	 * 根据制定页面跳转
	 */
	function bp_reload() {
		var offset = document.getElementById("bp_offset").value;
		var idx = (offset == null) ? 0 : parseInt(offset) - 1;
			
		var trgUrl = 'productgetAllProduct.action?offset=' + idx;
		$('#productList').window('refresh', trgUrl); 
	}

	/**
	 * 根据条件查询
	 */
	function bp_query() {
		var productCd=$("#bp_productCd").val();
		var brad = $("#bp_brad").combobox("getValue");
		var spno=$("#bp_spno").val();
		var jhdt = $("#bp_jhdt").datetimebox('getValue');
		var xjdt = $("#bp_xjdt").datetimebox('getValue');
		
		var trgUrl = 'productgetAllProduct.action?spno='+ spno+'&jhdt='+ jhdt+'&productCd='+ productCd
					+'&xjdt='+ xjdt+'&brad='+ brad;
					
		$('#productList').window('refresh', trgUrl); 
	}
	
	
	/**
	* 翻到给定偏移量的页面
	*/
	function bp_turnPage(offset){
	
		var productCd=$("#bp_productCd").val();
		var brad = $("#bp_brad").combobox("getValue");
		var spno=$("#bp_spno").val();
		var jhdt = $("#bp_jhdt").datetimebox('getValue');
		var xjdt = $("#bp_xjdt").datetimebox('getValue');
		
		var trgUrl = 'productgetAllProduct.action?spno='+ spno+'&jhdt='+ jhdt+'&productCd='+ productCd
					+'&xjdt='+ xjdt+'&brad='+ brad+ '&offset=' + offset;
					
		$('#productList').window('refresh', trgUrl); 
	}
	
	/**
	 * 选择产品信息
	 * @param index
	 * @param value
	 */
	function chooseBProductP(index,value){ 
		var productCd = value['productCode'].trim();
		
		$("#productList").window('close');
		$("#relateProductCode").textbox('setValue',productCd);//赋值
	}
	
	/**
	 * 添加关联产品
	 * @param mtlId
	 */
	function addRelateProduct(mtlId){
		var relpduCode = $("#relateProductCode").val();
		var memo = $("#memo").val();
		
		$.ajax({
			type : 'POST',
			url : 'relpdusaveRelateProduct.action',
			data : {
				'mtlId':mtlId,
				'relpduCode':relpduCode,
				'memo':memo
			},
			dataType : 'json',
			success : function(data) {
				if(data){
					var refUrl = 'productgetAllProduct.action?mtlId='+mtlId;
					$('#productList').window('refresh', refUrl);
					var trgUrl = 'relpdugetRelateProductS?mtlId='+mtlId;
					$('#getRelateProductList').window('refresh', trgUrl); 
				}
			}
	
		});
	}
	
	/**
	 * 删除面料相关产品信息
	 */
	function sureDelRelpdu(productCode,mrpId,mtlId){
		var msg = "确定要删除 ["+productCode+"] 产品吗？";
		if (confirm(msg) == true) {
			$.ajax({
				type : 'POST',
				url : 'relpdudelRelateProduct',
				data : {
					'delmrpId':mrpId
				},
				dataType : 'json',
				success : function(data) {
					if(data){
						var refUrl = 'productgetAllProduct.action?mtlId='+mtlId;
						$('#productList').window('refresh', refUrl);
						var trgUrl = 'relpdugetRelateProductS?mtlId='+mtlId;
						$('#getRelateProductList').window('refresh', trgUrl); 
					}
				}
		
			});
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 关闭关联产品窗口显示
	 */
	function closeRelpdusWin(){
		
		$.ajax({
			type:'POST',
			url:'relpducloseRelpdusWin.action',
			dataType : 'json',
			success : function(data){
	        	$("#relateProducts").textbox('setValue',data);//赋值
			
			    $("#getRelateProductList").window('close');
			}
		
		});
	}

	