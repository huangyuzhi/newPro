//依赖Comm、bootstrap-select、jQuery、LineInfo。
var Transformer = Transformer ? Transformer : {};
Transformer.contextPath = "";
Transformer.remoteDataType = "json";

+function ($, map) {
    var transFormerComboDatas = [],
        events = {},
        currentTransformers = [],
        timeoutId;
    /**
     * 直接加载故障变压器
     */
    (function () {
        var that = arguments.callee;
        Comm.getRemoteData({
            // url: Transformer.contextPath + "transformerInfo/findFaultTransformer.do",
            url: Transformer.contextPath + "json/transformers.json",
            data: {},
            dataType: Transformer.remoteDataType
        }, function (transformerdatas) {	//维护缓存的变压器对象
            var normalTransformer = [];
            $.each(currentTransformers, function (k, transformer) {
                if (transformer.blAttr.isFault) {
                    transformer.destory();
                } else {
                    normalTransformer.push(transformer);
                }
            });
            currentTransformers = normalTransformer;
            Transformer.generateTrans(transformerdatas);
            $.each(currentTransformers, function (k, transformer) {
                transformer.show();
            });
            timeoutId = window.setTimeout(that, 10 * 1000);
        });
    })();

    Transformer.transformerCombo = function (options) {
        Comm.getRemoteData({
            url: Transformer.contextPath + "transformerInfo/getDataWithJson.do",
            data: {
                selectField: "id as id, transformerName as transformerName, bdCoorsX as bdCoorsX, bdCoorsY as bdCoorsY, belongLine as belongLine ",
                sort: true,
                sortedBy: "id"
            },
            dataType: Transformer.remoteDataType
        }, function (transformers) {
            //为下拉框设定第一个文本
            /*transformers.json.unshift({
                transformerName : "变压器",
                id : 0
            });*/
            transFormerComboDatas = transformers;
            options.datas = transformers;
            Comm.fillSelect(options);
            $(options.select).selectpicker("refresh");
        });
    };

    /**
     * 根据变压器数据生成变压器
     */
    Transformer.generateTrans = function(transDataArr) {
        $.each(transDataArr, function(index, transData) {
            currentTransformers.push(new Transfor(transData));
        });
    };

    /**
     * 变压器对象
     */
    function Transfor(transdata) {
        this.blAttr = transdata;
        this.marker = Comm.generateMarkerByIcon(new BMap.Point(transdata.bdCoorsX, transdata.bdCoorsY), transformerIcon);
        this.infoWindow = transformerInfoWindow(transdata);
        //this.timeoutId;
        var trans = this;
        this.marker.addEventListener("click", function() {
            this.openInfoWindow(trans.infoWindow);
        });
        $.each(events, function(eventName, eventHandler) {
            trans.marker.addEventListener(eventName, function() {
                eventHandler.call(trans);
            });
        });
        if(trans.blAttr.isFault) {
            trans.faultStyle();
        }
    };

    /**
     * 清除所有变压器
     */
    Transformer.clearCurrentTrans = function() {
        $.each(currentTransformers, function(key, transFormer) {
            if(!transFormer.blAttr.isFault) {
                transFormer.destory();
            }
        });
        currentTransformers = [];
    };


}($, map);