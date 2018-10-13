/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



        //$('#categories').css('display','none');  //wait load content  
        window.fbAsyncInit = function() {
            FB.init({
              appId            : '480433499099624',
              autoLogAppEvents : true,
              xfbml            : true,
              version          : 'v3.1'
            });
          };

          (function(d, s, id){
             var js, fjs = d.getElementsByTagName(s)[0];
             if (d.getElementById(id)) {return;}
             js = d.createElement(s); js.id = id;
             js.src = "https://connect.facebook.net/en_US/sdk.js";
             fjs.parentNode.insertBefore(js, fjs);
           }(document, 'script', 'facebook-jssdk'));
           var person= {userID:"",name: "",accessToken: "", picture: "",email: "" };
           function loginface(){
               FB.login(function (response){
                    if(response.status=="connected"){
                        person.accessToken=response.authResponse.accessToken;
                        person.userID=response.authResponse.userID;
                        FB.api('/me?fields=id,name,first_name,last_name,email,picture.type(large)',function (userData){
                            person.name = userData.name;
                            person.email = userData.email;
                            person.picture = userData.picture.data.url;
                            
                            $.ajax({
                                url: "/login",
                                method: "POST",
                                data: person,
                                dataType: 'text',
                                success: function(res){
                                    var data=jQuery.parseJSON(res);
                                    idcart=data.id;
                                    login=true;
                                    $('#login').css('display','none');
                                    document.getElementById("hi").innerHTML=`wellcome,`+data.name;
                                    $('#hi').css('display','block');
                                    document.getElementById("slCart").innerHTML =data.count;
                                    sessionStorage.setItem('user', "true");
                                    
                                }
                            });
                        });
                    }
                },{scope: 'public_profile, email'});
           }
               
            /*<![CDATA[*/
            
            var user = /*[[${session.USER}]]*/ 'default';
            /*]]>*/
            
            var login;
            var idcart;
            
            if(user == null && sessionStorage.getItem('user')==null){// when not login
                $('#login').css('display','block');
                $('#hi').css('display','none');
                login=false;
            
                
            }else{  // when logined
                $('#login').css('display','none');
                $('#hi').css('display','block');
                
                loadlai();
            
            }
            function loadlai(){
                $.ajax({
                    type: 'GET',
                    headers: {Accept: "application/json; charset=utf-8", "Content-Type": "application/json; charset=utf-8"},
                    dataType: 'json',
                    cache: false,
                    url: '/getCart',
                    success: function (res) {
                        //var data=jQuery.parseJSON(res);
                        
                                console.log(res);
                                login=true;
                                idcart=res.id;
                                $('#login').css('display','none');
                                document.getElementById("hi").innerHTML=`wellcome,`+res.name;
                                $('#hi').css('display','block');
                                document.getElementById("slCart").innerHTML =res.count;
                    }
                });
            }
        
            $('#login').click(function(){
                loginface();
            });
            
            var itemProduct;
            var listLevelSugar;
            
            //chinh lai phan thanh dieu huong
            var offset = 50;
            
            function dieuhuong(a){         
                $('.navbar1').click(function (e) {
                    
                    e.preventDefault();
                    $($(a).attr('href'))[0].scrollIntoView();
                    window.scrollBy(0, -offset); 
                    $(a).parent().addClass('active');
                    $(a).parent().siblings().removeClass('active');
                    
                });
                
            };
            //end chinh lai phan thanh dieu huong
            function addtocart() {
                if(login==true){
                    var mucduong=$('input[name=mucduong]:checked').siblings('p').text();

                    var topping=new Array();
                    $('input[name="topping"]:checked').each(function() {
                        topping.push($(this).siblings('p').text());
                    });
                    var size=$('input[name=size]:checked').siblings('p').text();
                    var quantity=$('#quantity').text();
                    var idProduct=itemProduct.productId;
                    var itemcart = {
                        idProduct:idProduct,
                        idcart:idcart,
                        quantity:quantity,
                        size:size,
                        topping:topping.toString(),
                        mucduong:mucduong
                    };
                    document.getElementById("slCart").innerHTML = Number($('#slCart').text())+1;
                    document.getElementById("overlay").style.display = "none";

                    $.ajax({
                        url: "/addtocart",
                        method: "POST",
                        data: itemcart,
                        dataType: 'text',
                        success: function(){
                            console.log('ok');
                        }
                    });
                }else{
                   loginface(); 
                }
            };
            $(window).load(function () {
                $.ajax({
                    type: 'GET',
                    url: '/ListCategories',
                        success: function (data) {
                            var ListCategories=jQuery.parseJSON(data);
                            ListCategories.forEach(function myfunction(item) {
                                var html1=`<li style="text-align: center;width:25%; float:left;"><a onclick="dieuhuong(this)" href="#section`+item.id+`">`+item.name+`</a></li>`;
                                var html2=`<div id="section`+item.id+`" class="container-fluid"></br>
                                            <div style="font-size:15px;font-weight:bold;padding:10px;text-transform:uppercase;background-color: whitesmoke; width:100%;">`+item.name+`</div>
                                            <table id="tasection`+item.id+`" style="width:100%">

                                            </table>
                                        </div>`;
                                document.getElementById("menucategorios").innerHTML += html1;        
                                //document.getElementById("categories").innerHTML += html2;
                                ;
                            });
                            /*$.ajax({
                                type: 'GET',
                                url: '/ListProduct',
                                success: function (data) {
                                    listProduct=jQuery.parseJSON(data);
                                    listProduct.forEach(function myfunction(item) {
                                        var html=`  <tr style="width:100%">
                                                        <td style="width:20%"><img src="/images/`+item.images+`" alt="Smiley face" width="100%"></td>
                                                        <td style="width:60%; padding-left: 10px;">
                                                            <h4>`+item.name+`</h4>`+item.price+

                                                        `</td>
                                                        <td style="width:60%;"><button class="btnCart btn btn-info btn-circle" ><i class="fa fa-plus" aria-hidden="true"></i><input type="hidden" value=`+item.id+`></button></td>
                                                    </tr></br>`;

                                        document.getElementById("tasection"+item.cId).innerHTML += html;

                                    });
                                }
                            });*/
                        
                        //$('#loadding').css('display','none');
                        //$('#categories').css('display','block');
                        
                    }
                        
                        
                        
                }); 

                /*$.ajax({
                    type: 'GET',
                    url: '/ListLevelSugar',
                    success: function (data) {
                       listLevelSugar=jQuery.parseJSON(data);
                       
                    }
                });*/
            });
            
            
            
            $(document).on('click', '.btnCart', function () {
                
                var id=$(this).children("input").val();
                console.log(id);
                $.ajax({
                    type: 'GET',
                    url: '/Productdetails?id='+id,
                    success: function (dt) {
                        var data=jQuery.parseJSON(dt);
                        itemProduct=data;
                        var toppings=data.toppings;
                        console.log(data);
                        var html=`<div class="modal-content">
                                    
                                    <div class="col-md-12 col-xs-12 col-sm-12" style=" background-color: whitesmoke;margin-top: 8px;">
                                        
                                        <img style="width:20%; float: left;" src="images/`+data.images+`" alt="" />
                                        <div style="padding-left: 5px;padding-top: 5px;width: 30%; float: left;">
                                            <p style="font-size:20px; font-weight:bold; color:black;">`+data.name+`</p>
                                            <p>`+data.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+`đ</p>
                                        </div>
                                        <div style="float: right; width: 40%;">
                                            <a id="close" style="font-size: 40px; float:right;top:0px; right:0px;">&times;</a>
                                            <div style="float: right; width:100%;">
                                                
                                                <button id="plus"  style="border-radius:5px;" type="button" class="btn btn-info">+</button>
                                                <button disabled id="quantity" class="btn btn-default" />1</button>
                                                <button id="minus"  style="border-radius:5px;" type="button" class="btn btn-danger">-</button>
                                            </div>
                                            
                                        </div>
                                    </div>

                                    <!-- /chon size -->
                                    <div class="col-md-12 col-xs-12 col-sm-12" style="background-color: whitesmoke;">    
                                        <h4 style="text-transform: capitalize; width: 70%; float:left;">Chọn size</h4>
                                        <h4 style="float: right;text-transform: capitalize; font-size: 20px;padding-left: 0; background-color: #777777;color: white; ">Bắt buộc</h4>
                                    </div>
                                    <div  class="col-md-12 col-xs-12 col-sm-12" style="padding-top: 5px;">
                                        <label style="border-bottom: 1px solid whitesmoke; width: 100%;" class="customcheck">Size nhỏ
                                            <input onclick="tinhtien()" type="radio" name="size"  checked="checked" value="0">
                                            <p hidden>false</p>  
                                            <span class="checkmark"></span>
                                            <div style="float:right;">0đ</div>
                                          </label>
                                          <label style="border-bottom: 1px solid whitesmoke; width: 100%;" class="customcheck">Size lớn
                                            <input onclick="tinhtien()"  type="radio" name="size" value="8000">
                                            <p hidden>true</p>
                                            <span class="checkmark"></span>
                                            <div style="float:right;">8,000đ</div>
                                          </label>
                                    </div>
                                    <!-- /end chon size -->

                                    <!-- /chon topping -->
                                    <div class="col-md-12 col-xs-12 col-sm-12" style="background-color: whitesmoke;">    
                                        <h4 style="text-transform: capitalize; width: 100%; float:left;">Chọn topping</h4>
                                    </div>
                                    <div style="overflow-y: scroll;" class="col-md-12 col-xs-12 col-sm-12">`;
                                    //hien thi topping
                    toppings.forEach(function myfunction(item) {
                        html=html+ `<label style="border-bottom: 1px solid whitesmoke; width: 100%;" class="customcheck">`+item.name
                                                    +`<input onclick="tinhtien()" type="checkbox" name="topping" value="`+item.price+`">
                                                     <p hidden>`+item.id+`</p>
                                                    <span class="checkmark"></span>
                                                    <div style="float:right;">`+item.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+`đ</div>
                                                </label>`;
                    });
                     //end thi topping
                     html=html+ `</div>

                                    <!-- /end chon topping -->
                                    <!-- / chon muc duong -->
                                    <div class="col-md-12 col-xs-12 col-sm-12" style="background-color: whitesmoke;">    
                                        <h4 style="text-transform: capitalize; width: 70%; float:left;">Chọn mức đường</h4>
                                        <h4 style="float: right;text-transform: capitalize; font-size: 20px;padding-left: 0; background-color: #777777;color: white; ">Bắt buộc</h4>
                                    </div>
                                    <div class="col-md-12 col-xs-12 col-sm-12">`;
                                listLevelSugar.forEach(function myfunction(item) {
                                    if(item.id==1){
                                     html=html+   `<label style="border-bottom: 1px solid whitesmoke; width: 100%;" class="customcheck">`+item.name
                                            +`<input type="radio" name="mucduong" checked="checked" >
                                              <p hidden>`+item.id+`</p>
                                            <span class="checkmark"></span>
                                            <div style="float:right;">0đ</div>
                                        </label>`;
                                    }else{
                                        html=html+   `<label style="border-bottom: 1px solid whitesmoke; width: 100%;" class="customcheck">`+item.name
                                            +`<input type="radio" name="mucduong" >
                                            <p hidden>`+item.id+`</p>
                                            <span class="checkmark"></span>
                                            <div style="float:right;">0đ</div>
                                        </label>`;
                                    } 
                                });
                                   html=html+ `</div>
                                    <!-- /end chon muc duong -->
                                    <!-- /them vao gio -->
                                    <div onclick="addtocart(`+data.productId+`)" class="col-md-12 col-xs-12 col-sm-12" style="background-color: #286090;">    
                                        <h4 style="text-transform: capitalize; width: 70%; float:left; color: white;">+ Thêm vào giỏ</h4>
                                        <h4 id="tongtien" style="float: right;text-transform: capitalize; font-size: 20px;padding-left: 0;color: white; ">`+data.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+`đ</h4>
                                    </div>
                                </div>`;
                                document.getElementById("overlay").innerHTML = html;
                    }
                });
                
                
                document.getElementById("overlay").style.display = "block";
                $('body').css('overflow','hidden');
                
            });
            $(document).on('click', '#close', function () {
                document.getElementById("overlay").style.display = "none";
                $('body').css('overflow','visible');
            });
            $(document).on('click', '#plus', function () {
                var i= Number($("#quantity").text())+1;
                $("#quantity").text(i);
                tinhtien();
            });
            $(document).on('click', '#minus', function () {
                var i= Number($("#quantity").text())-1;
                if(i>0){
                    $("#quantity").text(i);
                }
                tinhtien();
            });
           function tinhtien(){
               var moneysize= Number($('input[name=size]:checked').val());
               var moneytopping=0;
               $('input[name="topping"]:checked').each(function() {
                    moneytopping=moneytopping+Number(this.value);
                 });
               var money1product=(moneysize+moneytopping+Number(itemProduct.price))*Number($('#quantity').text());
               document.getElementById("tongtien").innerHTML = money1product.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'đ';
           }
           
           
            var image = document.getElementById('test');
                image.onload = function() {
                  var engine = new RainyDay({
                    image: this,
                    gravityAngle: Math.PI / 5

                  });
                  engine.trail = engine.TRAIL_SMUDGE;
                  engine.rain([ [3, 2, 2] ], 100);
                  engine.rain(
                    [
                        [1, 0, 20], 
                        [3, 3, 1],
                        [1, 2, 19]
                    ],                       
                    100);
                 // engine.rain([ [0, 2, 200], [3, 3, 1] ], 100);
                };
                image.crossOrigin = 'anonymous';
                image.src = '/images/banner.jpg';
                
            $('.item-note').click(function(){
                var a=$(this);
                $('#error-message-modal').css('display','block');
                $('.dialog-input').focus();
                if($(a).text()=='Note...'){
                    $('.dialog-input').val('');
                }else{
                    $('.dialog-input').val($(a).text());
                }
                $('.dialog-cancel').click(function(){
                    $('#error-message-modal').css('display','none');
                });
                $('.dialog-done').click(function(){ 
                    var value=$('.dialog-input').val().trim();
                    if(value != ""){
                        $(a).text(value);
                        $(a).css('color','black');
                    }else{
                        $(a).text('Note...');
                        $(a).css('color','#bbb');
                    }
                    $('#error-message-modal').css('display','none');
                    a=null;
                });
                
            });
            
            $('.modal-footer').click(function(){
                document.getElementById('checkout').style.left = '0';$('body').css('overflow','hidden');
            });
            
            
        $('.navbar1 a').click(function (e) {
    e.preventDefault();
    var getElement=$(this).attr('href');

    var getOffset=$(getElement).offset().top;
    $('html,body').animate({
        scrollTop: getOffset-50
    },300);




    //$($(a).attr('href'))[0].scrollIntoView({behavior: "smooth", block: "start"});
    //console.log("ok");



    //$(a).parent().addClass('active');
    //$(a).parent().siblings().removeClass('active');
});
