//依赖bootstrapValidator插件。
var Validation = Validation ? Validation : {};

Validation.enable = function(formSelector, otherFields) {
	var extendField,
		commFields = {
    	"mapVo['customerName']" : {
    		validators : { 
    			notEmpty : { 
    				message : "客户名称不能为空"
    			}
    		}
    	},
    	"mapVo['identityCardNumber']" : {
    		validators : {
    			notEmpty : { 
    				message : "身份证号码不能为空"
    			}
//  			,
//  			regexp : {
//  				message : "身份证号码格式不正确",
//  				regexp : /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/
//  			}
    		}
    	},
    	"mapVo['phoneNumber']" : {
    		validators : {
    			notEmpty : { 
    				message : "电话号码不能为空"
    			}
    		}
    	}
    };
    if(otherFields) {
    	for(var prop in otherFields) {
    		commFields[prop] = otherFields[prop];
    	}
    }
    extendField = commFields;
	$(formSelector).bootstrapValidator({
		message: 'This value is not valid',
	    feedbackIcons: {
	        valid: 'fa fa-check',
		    invalid: 'fa fa-times',
		    validating: 'fa fa-refresh'
	    },
	    submitButtons : "button[type = 'submit']" ,
	    fields : extendField
	});
};
