/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$('.navbar1 a').click(function (e) {
    e.preventDefault();
    var getElement=$(this).attr('href');

    var getOffset=$(getElement).offset().top;
    $('html,body').animate({
        scrollTop: getOffset-50
    },300);
});
$("#navbar1").on("activate.bs.scrollspy", function(e){
    var element = $(".nav li.active > a");
    var get0=element.offset().left;
    $(element).parent().parent().animate({
        scrollLeft: get0
    },300);

});
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

$.ajax({
    type: 'GET',
    url: '/ListLevelSugar',
    success: function (data) {
       listLevelSugar=jQuery.parseJSON(data);
    }
});
$('.d-menu-grid').click(function(){
   var id=$(this).children('p').text();
   
   $.ajax({
       type: 'GET',
       url: '/Productdetails?id='+id,
       dataType: 'json',
       success: function (data) {
          
           var html=`<div class="modal-content">
                    <div class="modal-nav1">
                        <div class="container">
                            <nav class="navbar navbar-blue row  justify-content-between align-items-center">
                                <div class="navbar-left col-auto">
                                    <span class="navbar-link navbar-link-txt text-left svg-md">
                                        <i class="fas fa-times"></i>
                                    </span>
                                </div>
                                <div class="navbar-title text-center col text-truncate">Add item</div>
                                <div class="navbar-right col-auto">
                                    <span class="navbar-link navbar-link-txt text-left">
                                        
                                    </span>
                                </div>
                            </nav>
                        </div>
                    </div>
                    <div class="modal-header shadow-sm">
                        <div class="d-menu-item row no-gutters">
                            <div class="col-auto">
                                <div class="menu-item-img">
                                    <img src="/images/`+data.images+`" class="img-fluid" alt="">
                                </div>
                            </div>
                            <div class="col">
                                <div style="padding: 0 0 0 10px;">
                                    <h4 class="menu-item-name text-truncate">`+data.name+`</h4>
                                    <div class="menu-item-price">
                                        <span>`+number_format(data.price,0)+` đ</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="menu-item-number">
                            <div class="row no-gutters justify-content-end align-items-center">
                                <div class="col-auto text-center">
                                    <span class="menu-item-minus">
                                        <i class="fas fa-minus"></i>
                                    </span>
                                </div>
                                <div class="col-auto text-center">
                                     <span id="quantity" class="val-input">
                                        1
                                    </span>
                                </div>
                                <div class="col-auto text-center">
                                    <span class="menu-item-add">
                                        <i class="fas fa-plus"></i>
                                    </span>
                                </div>
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
                            <input onclick="tinhtien(`+data.price+`)" type="radio" name="size"  checked="checked" value="0">
                            <p hidden>false</p>  
                            <span class="checkmark"></span>
                            <div style="float:right;">0đ</div>
                          </label>
                          <label style="border-bottom: 1px solid whitesmoke; width: 100%;" class="customcheck">Size lớn
                            <input onclick="tinhtien(`+data.price+`)"  type="radio" name="size" value="8000">
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
    data.toppings.forEach(function myfunction(item) {
        html=html+ `<label style="border-bottom: 1px solid whitesmoke; width: 100%;" class="customcheck">`+item.name
                                    +`<input onclick="tinhtien(`+data.price+`)" type="checkbox" name="topping" value="`+item.price+`">
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
                $('.navbar-left').click(function(){
                    $('#overlay').css('height','0');
                    $('#warpper').css('position','relative');
                });
                $('#overlay .menu-item-add').click( function () {
                    var i= Number($("#quantity").text())+1;
                    
                    $("#quantity").text(i);
                    tinhtien(data.price);
                });
                $('#overlay .menu-item-minus').click(function () {
                    var i= Number($("#quantity").text())-1;
                    if(i>0){
                        $("#quantity").text(i);
                    }
                    tinhtien(data.price);
                });
                    
       }
    });
    document.getElementById("overlay").style.height = "100%";
    $('body').css('overflow','hidden');
    
    
});
function tinhtien(price){
    var moneysize= Number($('input[name=size]:checked').val());
    var moneytopping=0;
    $('input[name="topping"]:checked').each(function() {
         moneytopping=moneytopping+Number(this.value);
      });
    var money1product=(moneysize+moneytopping+Number(price))*Number($('#quantity').text());
    document.getElementById("tongtien").innerHTML = money1product.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'đ';
}
function addtocart(productId) {
    if(login==true){
        var mucduong=$('input[name=mucduong]:checked').siblings('p').text();

        var topping=new Array();
        $('input[name="topping"]:checked').each(function() {
            topping.push($(this).siblings('p').text());
        });
        var size=$('input[name=size]:checked').siblings('p').text();
        var quantity=Number($('#quantity').text());
        var idProduct=productId;
        var itemcart = {
            idProduct:idProduct,
            idcart:idcart,
            quantity:quantity,
            size:size,
            topping:topping.toString(),
            mucduong:mucduong
        };
        $('.slcart').text(Number($('#mainsl').text())+quantity);
        $('#overlay').css('height','0');
        $('body').css('overflow','visible');

        $.ajax({
            url: "/addtocart",
            method: "POST",
            data: itemcart,
            dataType: 'text',
            success: function(data){
                console.log(data);
            }
        });
    }else{
       loginface(); 
    }
};