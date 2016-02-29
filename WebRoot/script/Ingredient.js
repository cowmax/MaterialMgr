
$.extend($.fn.validatebox.defaults.rules, {
   //验证数值
	snumber: {
        validator: function (value) {
            return /^0*\.\d+$/.test(value);
        },
        message: '只能输入0-1之间的数'
    }
});


    /**
	*保存成分
	*/
	function addBMIngredient(){
		//$("#BMprecent").textbox("isValid")
		
		// 判断用户输入值是否合法
		
		if (!$("#saveBMIngred").form('validate')){
			
			
			}else{
			var mtlId=$("#IngredMtlId").val();
			
			$.ajax({
				url : 'ingraddIngDt.action',
				type : "POST",
				data : $("#saveBMIngred").serialize(),
				dataType : 'json',
				success : function(data) {
					
					var trgUrl = 'ingrshowIngredient?mtlId=' + mtlId;
					$('#ingredientWin').window('refresh', trgUrl); 
				}
			});
			
			}
		
	}

  	//弹出添加成分
	function addIngredient(){
		// 在 window 的 onLoad 事件表示页面 DOM 已经加载完成
		$('#ingredientWin').window({onLoad:function(){
			// 把用户输入的数值规范化为 : 小于 1 的 2 位小数
			$('#BMprecent').textbox({
				  onChange: function(value){
				    var v = parseFloat(value);
				    if (!isNaN(v)){
					    while(v > 1) v = v/10;
					    while(v < 10) v = v*10;
					    
					    v = Math.round(v)/100;
					    
					    $('#BMprecent').textbox('initValue', v); // 注意：不能使用 setValue，以免循环触发 onChange 事件
				    }
				  }
				});
		}});
		
		$("#ingredientWin").window('open');

	}
	
	
	/**
	 *删除成分 
	 */
	function delingredient(id,ingredientName){
	    var mtlId=$("#IngredMtlId").val();
		var msg = "确定要删除 ["+ingredientName+"] 成分吗？";
			if (confirm(msg) == true) {
				$.ajax({
					type:'POST',
					url:'ingrdelIngdt.action',
					data:{
						'id':id
					},
					dataType : 'json',
					success : function(data){
						if(data){
							$.messager.show({
								msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">删除成分成功！</div></div>',
								timeout : 800,
								showSpeed : 200,
								showType : 'show',
								style : {
									right : '',
									top : '',
									bottom : ''
								}
							});
							var trgUrl = 'ingrshowIngredient?mtlId=' + mtlId;
						    $('#ingredientWin').window('refresh', trgUrl); 
						}
					}
				});
				return true;
			} else {
				return false;
			}
		

	}
	
	/**
	 * 关闭窗口显示
	 */
	function closeIngredientWin(){
		
		$.ajax({
			type:'POST',
			url:'ingrmaterialShow.action',
			data:{
			
			},
			dataType : 'json',
			success : function(data){
	        
	        	$("#ingredients").textbox('setValue',data);//赋值
			
			    $("#ingredientWin").window('close');
			}
		
		});
//		
//		   alert(ingredientList);
//		  jQuery.each(ingredientList,function(i,item) {
//            var b=item.ingredientName;
//        	var a=item.ingredientName+','+item.precent;
//           $("#ingredients").textbox('setValue',a);//赋值
//        });  
//	    $("#ingredientWin").window('close');
	}
