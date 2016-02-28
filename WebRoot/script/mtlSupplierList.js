   	/**
	 * 根据制定页面跳转
	 */
	function ms_reload() {
		var offset = document.getElementById("offset").value;
		var idx = (offset == null) ? 0 : parseInt(offset) - 1;
			
		var trgUrl = 'supplierloadAllSupperList?offset=' + idx;
		$('#editSupplier').window('refresh', trgUrl); 
	}
	
	/**
	 * 根据条件查询
	 */
	function ms_query() {
		var qmtlCode = $("#qmtlCode").val();
		var qmtlName = $("#qmtlName").val();
		var qsupplierName = $("#qsupplierName").combobox("getValue");
		var qminColorCount = $("#qminColorCount").val();
		var qmaxColorCount = $("#qmaxColorCount").val();
		
		var trgUrl = 'supplierloadAllSupperList.action?qmtlCode='+ qmtlCode
					+'&qmtlName='+ qmtlName+'&qsupplierName='+ qsupplierName
					+'&qminColorCount='+ qminColorCount+'&qmaxColorCount='+ qmaxColorCount;
		$('#editSupplier').window('refresh', trgUrl); 
	}
	
	/**
	* 翻到给定偏移量的页面
	*/
	function ms_turnPage(offset){
		var qmtlCode = $("#qmtlCode").val();
		var qmtlName = $("#qmtlName").val();
		var qsupplierName = $("#qsupplierName").combobox("getValue");
		var qminColorCount = $("#qminColorCount").val();
		var qmaxColorCount = $("#qmaxColorCount").val();
		
		var trgUrl = 'supplierloadAllSupperList.action?qmtlCode='+ qmtlCode
					+'&qmtlName='+ qmtlName+'&qsupplierName='+ qsupplierName
					+'&qminColorCount='+ qminColorCount+'&qmaxColorCount='+ qmaxColorCount
					+ '&offset=' + offset;			
		$('#editSupplier').window('refresh', trgUrl); 
	}
	
	/**
	 * 显示添加供应商页面
	 */
	function showAddSupplier(){
		$("#addSupplier").window('open');
	}
	
	/**
	 * 添加供应商信息
	 */
	function addSupplier(){
		var suid = $("#supplier").combobox("getValue");
		var suna = $("#supplier").combobox("getText");
		var mtsName = $("#mtsName").val();
		var mtsColorCount = $("#mtsColorCount").val();
		var mtsCode = $("#mtsCode").val();
		
		$.ajax({
			type : 'POST',
			url : 'supplieraddSupplier.action',
			data : {
				'suid':suid,
				'suna':suna,
				'mtsName':mtsName,
				'mtsColorCount':mtsColorCount,
				'mtsCode':mtsCode
			},
			dataType : 'json',
			success : function(data) {
				if(data){
					$('#addSupplier').window('close');
					$("#addSupplier").window('refresh','suinaddSupOperation');
					var trgUrl = 'supplierloadAllSupperList.action?mtlIdCode='+data.mtlId;
					$('#editSupplier').window('refresh', trgUrl); 
				}
			}
		});
	}
	
	/**
	 * 确认删除
	 * @returns {Boolean}
	 */
	function sureDelSup(mtsName,mtsId) {
		var msg = "确定要删除 ["+mtsName+"] 供应商吗？";
		if (confirm(msg) == true) {
			$.ajax({
				type:'POST',
				url:'supplierdelSupplier',
				data:{
					'qmtsId':mtsId
				},
				dataType : 'json',
				success : function(data){
					if(data){
						$.messager.show({
							msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">删除供应商信息成功！</div></div>',
							timeout : 800,
							showSpeed : 200,
							showType : 'show',
							style : {
								right : '',
								top : '',
								bottom : ''
							}
						});
						var trgUrl = 'supplierloadAllSupperList.action?mtlIdCode='+data.mtlId;
						$('#editSupplier').window('refresh', trgUrl); 
					}
				}
			});
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 显示修改供应商页面
	 */
	function showMergerSupplier(mtsId){
		$("#mergerSupplier").attr("href", "suppliereditSupplier?qmtsId="+mtsId);
		$("#mergerSupplier").window('open');
	}
	
	/**
	 * 修改供应商信息
	 */
	function mergerSupplier(){
		$.ajax({
			url : 'suppliermergerSupplier.action',
			type : "POST",
			data : $("#supplierSavefrorm").serialize(),
			dataType : 'json',
			success : function(data) {
				$("#mergerSupplier").window('close');
				var trgUrl = 'supplierloadAllSupperList.action?mtlIdCode='+data.mtlId;
				$('#editSupplier').window('refresh', trgUrl); 
			}
		})

	}
	
	/**
	 *关闭添加供应方信息窗口
	 */
	function closeEditSupplier(){  
		$("#editSupplier").window('close');
	}

