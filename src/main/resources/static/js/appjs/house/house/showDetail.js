$(function () {
viewPagers();
listen();
isHas();
});

function viewPagers() {
    layui.use('carousel', function(){
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1'
            ,width: '95%' //设置容器宽度
            ,arrow: 'hover' //始终显示箭头
            ,anim: 'default' //切换动画方式
            ,height:'95%',
            interval:'3000'
        });
    });
}
function listen() {
    $("#createOrder").click(function () {
        var houseId=$("#houseId").val();
        createOrder(houseId);
    });
}

function isHas() {
    var elevator=$(".elevator").attr("elevator");
    var tv=$(".tv").attr("tv");
    var fridge=$(".fridge").attr("fridge");
    var airConditioner=$(".airConditioner").attr("airConditioner");
    var broadBand=$(".broadBand").attr("broadBand");
    var wardrobe=$(".wardrobe").attr("wardrobe");
    if(elevator==0){
        $(".elevator").css("text-decoration","line-through");
    }
    if(tv==0){
        $(".tv").css("text-decoration","line-through");
    }
    if(fridge==0){
        $(".fridge").css("text-decoration","line-through");
    }
    if(airConditioner==0){
        $(".airConditioner").css("text-decoration","line-through");
    }
    if(broadBand==0){
        $(".broadBand").css("text-decoration","line-through");
    }
    if(wardrobe==0){
        $(".wardrobe").css("text-decoration","line-through");
    }
    console.log(elevator);
    console.log(tv);
    console.log(fridge);
    console.log(airConditioner);
    console.log(broadBand);
    console.log(wardrobe);

}
function createOrder(id) {
    var houseTitle='新订单';
    layui.use('element', function(){
        var $ = layui.jquery
            ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
        element=parent.window.e;
        var tid='newTab'+new Date().getTime();
        //触发事件
        var active = {
            tabAdd: function(){
                //新增一个Tab项
                element.tabAdd('xbs_tab', {
                    title: houseTitle //用于演示
                    ,content:"<iframe src='" + "/order/order/newOrder/"+id+"' ></iframe>"
                    ,id: tid //实际使用一般是规定好的id，这里以时间戳模拟下
                });

            }
            ,tabDelete: function(othis){
                //删除指定Tab项
                element.tabDelete('xbs_tab', tid); //删除：“商品管理”


                othis.addClass('layui-btn-disabled');
            }
            ,tabChange: function(){
                //切换到指定Tab项
                element.tabChange('xbs_tab',tid); //切换到：用户管理
            }
        };


        active.tabAdd();
        active.tabChange();


        //Hash地址的定位
        var layid = location.hash.replace(/^#test=/, '');
        element.tabChange('test', layid);

        element.on('tab(test)', function(elem){
            location.hash = 'test='+ $(this).attr('lay-id');
        });

    });
}