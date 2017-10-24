<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"
 session="true"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <base href="<%=path%>/" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>过户申请</title>
</head>
<link rel="stylesheet" href="css/font-awesome-4.4.0/css/font-awesome.min.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-xs-12">
                <img src="img/logo@2x.png" alt="logo" class="img-responsive center-block" />
            </div>
        </div>
    </div>
</nav>
<div class="text-center">
    <h3>过户申请</h3>
</div>
<div class="col-lg-12">
    <div class="jumbotron">
        <div class="container">
            <p style="font-size: 14px;">您需要提供资料：过户双方身份证复印件、电费结清依据（当月电费发票）、过户协议、房产证复印件（土地使用证明）。</p>
        </div>
    </div>
</div>
<div class="col-lg-12">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">
                	个人信息填写
            </h3>
        </div>
        <div class="panel-body">
            <form id="ghsqForm" class="form-horizontal" method="post" action="<%=path %>/powerBusinessInfo/add.do">
                <input type="hidden" name="mapVo['businessType']" value="ghsq"/>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">原户主姓名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" placeholder="请输入原户主姓名"  name="mapVo['customerName']">
                    </div>
                </div>
                 <div class="form-group">
                    <label for="accountNumber" class="col-sm-2 control-label">过户户号</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="accountNumber" placeholder="请输入过户户号" name="mapVo['accountNumber']">
                    </div>
                </div>
                <div class="form-group">
                    <label for="Reason" class="col-sm-2 control-label">过户原因</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="Reason" placeholder="请输入过户原因" name="mapVo['transferReason']">
                    </div>
                </div>
                <div class="form-group">
                    <label for="NewAccountnumber" class="col-sm-2 control-label">新户主姓名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="NewAccountnumber" placeholder="请输入新户主姓名" name="mapVo['newCustomerName']">
                    </div>
                </div>
                <div class="form-group">
                    <label for="address" class="col-sm-2 control-label">用电地址</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="address" placeholder="请输入用电地址"  name="mapVo['electricityAddress']">
                    </div>
                </div>
                <div class="form-group">
                    <label for="IDcard" class="col-sm-2 control-label">原户主身份证</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="IDcard" placeholder="请输入您的原户主身份证号码" name="mapVo['identityCardNumber']">
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label">原户主电话</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="phone" placeholder="请输入您的原户主电话号码" name="mapVo['phoneNumber']">

                    </div>
                </div>
                <div class="form-group">
                    <label for="NewIDcard" class="col-sm-2 control-label">新户主身份证</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="NewIDcard" placeholder="请输入您的新户主身份证号码" name="mapVo['newIdentityCardNumber']">
                    </div>
                </div>
                <div class="form-group">
                    <label for="newphone" class="col-sm-2 control-label">新户主电话</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="newphone" placeholder="请输入您的新户主电话号码" name="mapVo['newPhone']">
                    </div>
                </div>
                <div class="text-center">
                    <p>
                        <button form="ghsqForm" type="submit" class="btn btn-primary">
                            <span><i class="fa fa-check-square-o"></i> 提交</span>
                        </button>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<script type="text/javascript" src="js/PowerBusValidation.js"></script>
<script type="text/javascript">
Validation.enable("#ghsqForm", {
	"mapVo['accountNumber']" : {
		validators : { 
			notEmpty : { 
				message : "户号不能为空"
			}
		}
	},
	"mapVo['newCustomerName']" : {
		validators : { 
			notEmpty : { 
				message : "新户主姓名不能为空"
			}
		}
	},
	"mapVo['newIdentityCardNumber']" : {
		validators : { 
			notEmpty : { 
				message : "新户主身份证号不能为空"
			}
		}
	},
	"mapVo['newPhone']" : {
		validators : { 
			digits : { 
				message : "新户主电话格式不正确"
			}
		}
	}
	
});
</script>
</body>
</html>