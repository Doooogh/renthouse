$(function () {
    var location1=$("#address").val();
    console.log(location1);
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(116.331398,39.897445);
    map.centerAndZoom(point,12);
    // 创建地址解析器实例
    var myGeo = new BMap.Geocoder();
    // 将地址解析结果显示在地图上,并调整地图视野
    myGeo.getPoint(location1, function(point){
        if (point) {
            map.centerAndZoom(point, 16);
            map.addOverlay(new BMap.Marker(point));
            map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
            var local = new BMap.LocalSearch(map, {
                renderOptions:{map: map, autoViewport:true}
            });
        }else{
            alert("您选择地址没有解析到结果!");
        }
    }, "北京市");
});