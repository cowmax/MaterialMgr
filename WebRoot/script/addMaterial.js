
/**
 * 关于验证
 */
$.extend($.fn.validatebox.defaults.rules, {
   //验证面料价格
	validateNum: {
		validator: function (value) {
			// if(value=='') return true;    // 为空时不验证
			return /^[0-9]*[1-9][0-9]*\.{0,1}[0-9]{0,2}$/.test(value);
	        },
	        message: '请输入数字'
    } ,
  //验证数值
    shrink: {
        validator: function (value) {
            return /^0*\.\d+$/.test(value);
        },
        message: '只能输入0-1之间的数'
    },
    //正整数验证
    validateColCount: {
		validator: function (value) {
			// if(value=='') return true;    // 为空时不验证
			return /^[0-9]*[1-9][0-9]*$/.test(value);
	        },
	        message: '请输入数字'
    },
    validateNotNull:{
    	validator: function (value) {
    		// 为空时不验证
			if(value!=''){
				return true;
			}else{
				return false;
			} 
	     },
	     message: '填写完整信息'
    }
});

/**
*提交面料
*/
function  addMaterial(){
	var mtlCode = $("#materialCode").val();
	if(!mtlCode==""){ 
		if (!$("#saveMaterialForm").form('validate')){
			$.messager.show({
				msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">请正确输入！</div></div>',
				timeout : 800,
				showSpeed : 200,
				showType : 'show',
				style : {
					right : '',
					top : '-200',
					bottom : ''
				}
			});
			
		}else{
			var obj=document.getElementById('saveMaterialForm'); 
			obj.action="materialaddMaterial.action?selfMtlCode="+mtlCode;; 
			
			obj.submit(); 
		}
	}else{
		$.messager.show({
			msg : '<div style="width:100%"><div style="line-height:50px;text-align:center;">保存失败，请选择面料类型！</div></div>',
			timeout : 600,
			showSpeed : 200,
			showType : 'show',
			style : {
				right : '',
				top : 100,
				bottom : ''
			}
		});
	}
}