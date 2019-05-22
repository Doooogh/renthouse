var prefix = "/house/house"
var limit=10;
var offset=0;
var page1=1;
var which="";
var title="";
var largeAreas="";
var priceStart=0;
var priceEnd=0;
var orientation="";
var houseType="";
var map={};
map['limit']=limit;
map['offset']=offset;
map['which']=which;
map['status']=0;




$(function () {
    load(map);
    listen();
    findByTerm();

});
function listen() {
    $("#findByText").click(function () {
        title=$("#findByTitle").val();
        map['title']=title;
        largeAreas=$("#findByTitle").val();
        map['largeAreas']=largeAreas;
        map['which']="findByTitle";
        load(map);
    });

    $(".large_area").click(function () {
        $('.large_area').not(this).removeClass("clickStyle").addClass("notClickStyle");
        $(this).removeClass("notClickStyle").addClass("clickStyle");
        // $("#no_astrict").css({color:"#00ae66"});
        largeAreas=$(this).text();
        if(largeAreas=="不限"){
            largeAreas="";
        }
        map['largeAreas']=largeAreas;
        map['which']="findByMore";
        load(map);

    });
    $(".price_range").click(function () {
        $('.price_range').not(this).removeClass("clickStyle").addClass("notClickStyle");
        $(this).removeClass("notClickStyle").addClass("clickStyle");
        priceStart=$(this).attr("price_start");
        priceEnd=$(this).attr("price_end");
        map['priceStart']=priceStart;
        map['priceEnd']=priceEnd;
        map['which']="findByMore";
        load(map);
    });
    $("#findByPrice").click(function () {
        priceStart=$("#price_start").val();
        priceEnd=$("#price_end").val();
        map['priceStart']=priceStart;
        map['priceEnd']=priceEnd;
        map['which']="findByMore";
        load(map);
    });
    $(".houseType").click(function () {
        $('.houseType').not(this).removeClass("clickStyle").addClass("notClickStyle");
        $(this).removeClass("notClickStyle").addClass("clickStyle");
        houseType=$(this).text();
        if(houseType==="一居"){
            houseType="1";
        }else if(houseType=="两居"){
            houseType="2"
        }else if(houseType=="三居"){
            houseType="3"
        }else if(houseType=="四居+"){
            houseType="4"
        }
        map['houseType']=houseType;
        map['which']="findByMore";
        load(map);
    });

    $(".orientation").click(function () {
        $('.orientation').not(this).removeClass("clickStyle").addClass("notClickStyle");
        $(this).removeClass("notClickStyle").addClass("clickStyle");
        orientation=$(this).text();
        map['orientation']=orientation;
        map['which']="findByMore";
        load(map);
    });
    $("#clearUrl").click(function () {

        $('.orientation').not(this).removeClass("clickStyle").addClass("notClickStyle");
        $('.houseType').not(this).removeClass("clickStyle").addClass("notClickStyle");
        $('.price_range').not(this).removeClass("clickStyle").addClass("notClickStyle");
        $('.large_area').not(this).removeClass("clickStyle").addClass("notClickStyle");
        $("#no_astrict").removeClass("notClickStyle").addClass("clickStyle");

        $(".bySelected a").removeClass("addBorder").addClass("removeBorder");
        $("#synthesize a").removeClass("removeBorder").addClass("addBorder");


        map={};
        map['limit']=10;
        map['offset']=0;
        map['status']=0;
        console.log(map.limit);
        load(map);
    });


}
function load(map) {
    console.log(map);
     var m=JSON.stringify(map);
     console.log(m);
    $.ajax({
        type: "post",//请求方式
        url: "/house/house/houseShow",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        data:JSON.stringify(map),
        contentType:'application/json;charset=utf-8',
        success:function (data) {
            console.log(data.rows);
            dataUtils(data);
            pageUtils(data);
             $('body').animate( {scrollTop: 0}, 1);


        },
        error:function () {
            alert("服务器错误");
        }

    });
}

function dataUtils(data){
    $("#content__list").empty();
    var rows=data.rows;
    var total=data.total;
    var id="";
    var title="";
    var img="";
    var largeAreas="";
    var smallAreas="";
    var squareMeter="";
    var orientation="";
    var houseType="";
    var pubdate="";
    var price="";
    var tags=null;
    $("#housesNum").text(total);
    $.each(rows,function (i,val) {
        // console.log(val);
        id=val.id;
        title=val.title;
        img=val.img;
        largeAreas=val.largeAreas;
        smallAreas=val.smallAreas;
        squareMeter=val.squareMeter;
        orientation=val.orientation;
        houseType=val.houseType;
        pubdate=val.pubdate;
        price=val.price;
        tags=val.tags;
        var str = "";
        console.log(rows.length+"-------------========-----------------");
        $.each(tags,function (i,val) {
            str+="<i>"+val.description+"</i>\n";

        })

        $("#content__list").append(" <div class=\"content__list--item\">\n" +
            ""+
            "                        <a class=\"content__list--item--aside aimg\"   onclick=\"showDetail("+id+")\"><img alt=\""+title+"\"  src=\""+"/img/"+img+"\"  house_id=\""+id+"\" class=\"lazyloaded img_a\" ></a>\n" +
            "                        <div class=\"content__list--item--main\">\n" +
            "                            <p class=\"content__list--item--title twoline\" onclick=\"showDetail("+id+")\">\n" +
            "                                <a   class=\"atitle\" house_id=\""+id+"\" >\n" +
            "                                    "+title+"\n" +
            "                                </a>\n" +
            "                            </p>\n" +
            "                            <p class=\"content__list--item--des\">\n" +
            "                                <a  href=\"#\" >"+largeAreas+"</a>-<a href=\"#\"   >"+smallAreas+"</a>\n" +
            "                                <i>/</i>\n" +
            "                                <span>"+squareMeter+"㎡"+"</span>\n" +
            "                                <i>/</i><span >"+orientation+"</span> <i>/</i>\n" +
            "                                <span >"+houseType+"</span>\n" +
            "                            </p>\n" +
            "                            <p class=\"content__list--item--brand oneline\">\n" +
            "                                寻家网                </p>\n" +
            "                            <p><img src=\"/css/home/img/time.png\" style=\"padding-bottom: 5px;margin-right: 5px\"/><span >"+pubdate+"</span></p>\n" +
            "                            <!--<p class=\"content__list--item--tim oneline\" style=\"color: rgba(16,40,50,.3)\"></p>-->\n" +
            "                            <p class=\"content__list--item--bottom oneline\" >\n" +str+
            "                            </p>\n" +
            "                            <span class=\"content__list--item-price\"><em >"+price+"</em> 元/月</span>\n" +
            "                        </div>\n" +
            "                    </div>");


        console.log("--------------------");
    });
}
function pageUtils(data){
    layui.use('laypage', function() {
        var laypage = layui.laypage;
        //完整功能
        laypage.render({
            elem: 'page'
            , count: data.total,
             curr:page1
            , layout: ['count', 'prev', 'page', 'next', 'refresh', 'skip']
            , jump: function (obj,first) {
                if(!first){
                    page1=obj.curr;
                    console.log(obj);
                    var curPage=obj.curr;
                    limit=obj.limit;
                    offset=limit*(curPage-1);
                    map['limit']=limit;
                    map['offset']=offset;
                    map['status']=0;
                    console.log("-----------------limit------------"+limit);
                    console.log("--------------------offset--------------"+offset);
                    load(map);
                }

            }
        });
    });

}

function findByTerm() {

    $("#synthesize").click(function () {
        $('.bySelected a').not(this).removeClass("addBorder").addClass("removeBorder");
        $("#synthesize a").removeClass("removeBorder").addClass("addBorder");
        map={};
        map['limit']=10;
        map['offset']=0;
        load(map);

    });
    $("#newest").click(function () {

        $('.bySelected a').not(this).removeClass("addBorder").addClass("removeBorder");
        $("#newest a").removeClass("removeBorder").addClass("addBorder");

        map={};
        map['sort']="createtime";
        map['asc']="desc";
        map['limit']=10;
        map['offset']=0;
        load(map);

    });
    $("#byPrice").click(function () {

        $('.bySelected a').not(this).removeClass("addBorder").addClass("removeBorder");
        $("#byPrice a").removeClass("removeBorder").addClass("addBorder");
        map={};
        map['sort']="price";
        map['asc']="desc";
        map['limit']=10;
        map['offset']=0;
        load(map);
    });

    $("#byArea").click(function () {
        $('.bySelected a').not(this).removeClass("addBorder").addClass("removeBorder");
        $("#byArea a").removeClass("removeBorder").addClass("addBorder");
        map={};
        map['sort']="square_meter";
        map['asc']="desc";
        map['limit']=10;
        map['offset']=0;
        load(map);
    });
}

function showDetail(id) {
    var houseTitle='房屋详情';
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
                    ,content:"<iframe src='" + prefix + "/showDetail/"+id+"' ></iframe>"
                    ,id: tid //实际使用一般是规定好的id，这里以时间戳模拟下
                });

            }
            ,tabDelete: function(othis){
                //删除指定Tab项
                element.tabDelete('xbs_tab', '44'); //删除：“商品管理”


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
