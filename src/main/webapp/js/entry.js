/*Javascript代码片段*/
/*这是jQuery插件的写法，即封装对象方法的插件类型，支持链式操作*/
(function($){
    $.fn.checkform = function(){
        /*由于可能匹配多个元素，所以要each();*/
        return this.each(function(){
            var $this = $(this);
            var $parent = $(this).parents();
            if (!$this.is(':input')){
                $this = $this.find(':input');
            }
            /*当输入框失焦时就检测输入是否为空，如果是空就添加error类名，就有了红色的阴影边框效果，不为空时添加succes类名，就有绿色的边框效果*/
            $this.blur(function(){
                if($this.is("input")){
                    if(this.value==""){
                        $this.addClass("error").removeClass("success");
                        setTimeout(function(){
                            $this.removeClass("none").removeClass("error").addClass("none");
                        },1000);
                    }else{
                        $this.removeClass("none").addClass("success").removeClass("error");
                        setTimeout(function(){
                            $this.removeClass("success").addClass("none");
                        },1000);
                    }
                }
            })
            $parent.find(".form-signin").click(function(){
                $this.blur();
                if($this.val()==""){
                    /*当输入为空时给input添加shake类名，这样就可以播放动画了*/
                    $this.addClass("shake");
                    /*一秒后移除shake类，以便以后可以多次播放*/
                    var wait = setTimeout(function(){
                        $this.removeClass("shake");
                    },1000)
                }
                return false;
            })
        })
    }
})(jQuery)
$(".form-input").checkform();