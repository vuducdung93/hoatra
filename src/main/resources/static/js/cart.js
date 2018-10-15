/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('#cart').click(function(){
    if(user==null){
        
    }else{
        
        document.getElementById('cartDetail').style.left = '0';$('body').css('overflow','hidden');
        $.ajax({
            type: 'GET',
            url: '/viewcart',
            dataType: 'json',
            success: function(data){
            var userinfo=   `<img class="img-circle" src="`+user.picture+`"/>
                                <div class="u-info">
                                    &nbsp; &nbsp; `+user.name+`
                                </div>
                                <div class="moneyright">
                                    <strong id="cartsl" class="slcart"></strong> items- <strong class="totalinvoice"></strong> &nbsp;
                            </div>`;
            document.getElementById("userInfo").innerHTML=userinfo;
                var html="";
                var slcart=0;
                data.forEach(function(item){
                    
                    slcart+=item.quantity;
                    var size="";
                    if(item.size){
                        size="Size lớn, ";
                    }else{
                        size="Size nhỏ, ";
                    }
                    var topping="";
                    if(item.list !=null){
                        item.list.forEach(function(item){
                            topping+=item.name+", ";
                        });
                    }
                    var mucduong="";
                    switch (item.mucduong) {
                        case 1:
                            mucduong="100% đường";
                            break;
                        case 2:
                             mucduong="70% đường";
                            break;
                        case 3:
                             mucduong="30% đường";
                            break;
                        case 4:
                             mucduong="0% đường";
                            break;
                    }
                    var price=number_format(item.total, 0)+' đ';
                    var price_total=number_format(item.total*item.quantity, 0)+' đ';
                    html +=`<div class="menu-item">
                                <div class="lines"></div>
                                <div class="menu-item-content">

                                    <div class="item-main">
                                        <div class="item-name">
                                            <strong>`+item.nameP+`</strong> [`+size+topping+mucduong+`]
                                        </div>
                                        <div class="item-price">
                                            `+price+` x `+item.quantity+` = `+price_total+`
                                        </div>
                                        <div class="priceH" hidden>`+item.total+`</div>
                                        <div class="pricetotalH" hidden>`+item.total*item.quantity+`</div>
                                        <span class="item-note">Note...</span>
                                    </div>
                                    <div class="changeIcon">
                                        <div class="itemId" hidden>`+item.id+`</div>
                                        <span class="menu-item-minus">
                                            <i class="fas fa-minus"></i>
                                        </span>
                                        <span class="val-input">
                                            `+item.quantity+`
                                        </span>
                                        <span class="menu-item-add">
                                            <i class="fas fa-plus"></i>
                                        </span>
                                        <span class="clear">

                                        </span>
                                    </div>
                                    <div class="clear"></div>
                                </div>

                            </div>`;
                    document.getElementById("CartItems").innerHTML=html;
                    $('.slcart').text(slcart);
                    recalculateCart();
                    
                });
                
                //event when click item note
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

                });//end------------
                
                
                //event button click plus or minus
                $('.menu-item-minus').click(function(){
                    var valinput=$(this).siblings('.val-input');
                    if(Number($(valinput).text())>1){
                        $(valinput).text(Number($(valinput).text())-1);
                        updateQuantity(this,$(valinput).text());
                        recalculateCart();
                        ajaxUpdateItem(this,valinput);
                    }else{
                        removeItem(this);
                        ajaxRemoveItem(this);
                    }
                    $('.slcart').text(Number($('#cartsl').text())-1);
                });
                $('.menu-item-add').click(function(){
                    var valinput=$(this).siblings('.val-input');
                    $(valinput).text(Number($(valinput).text())+1);
                    $('.slcart').text(Number($('#cartsl').text())+1);
                    updateQuantity(this,$(valinput).text());
                    recalculateCart();
                    ajaxUpdateItem(this,valinput);
                }); 
                //end-----------
                
            }
            
        });
    }
});
function ajaxUpdateItem(element,valinput){
    $.ajax({
            url: "/updateItem",
            method: "POST",
            data: {itemId: $(element).siblings('.itemId').text(), value: $(valinput).text()},
            dataType: 'text',
            success: function(){}
    });
}
function ajaxRemoveItem(element){
    $.ajax({
            url: "/removeitem",
            method: "POST",
            data: {itemId: $(element).siblings('.itemId').text()},
            dataType: 'text',
            success: function(){}
    });
}
function ajaxResetCart(){
    
}

function updateQuantity(quantityButton,quantity){
    var productRow = $(quantityButton).parent().siblings('.item-main');
    var itemprice = $(productRow).children('.item-price');
    var price=Number($(productRow).children('.priceH').text());
    var priceTotal = price*Number(quantity);
    
    $(itemprice).text(number_format(price,0)+' đ x '+quantity+' = '+number_format(priceTotal, 0)+' đ');
    $(productRow).children('.pricetotalH').text(priceTotal);
}
function recalculateCart(){
    var subtotal=0;
    $('.pricetotalH').each(function (){
        subtotal+=Number($(this).text());
    });
    $('.totalinvoice').text(number_format(subtotal,0)+ ' đ');
}
function removeItem(quantityButton){
    var productRow=$(quantityButton).parent().parent().parent();
    productRow.slideUp(300, function() {
        productRow.remove();
        recalculateCart();
    });
}

$('.modal-reset').click(function(){
    $('#modal-alert').css('display','block');
    $('.dialog-No').click(function(){
        $('#modal-alert').css('display','none');
    });
    $('.dialog-Reset').click(function(){
        $('#modal-alert').css('display','none');
        $('.itemId').each(function(){
            $.ajax({
                url: "/removeitem",
                method: "POST",
                data: {itemId: $(this).text()},
                dataType: 'text',
                success: function(){
                    console.log("remove all");
                }
            });
        });
    });
});


function number_format(number, decimals, decPoint, thousandsSep) {
    decimals = Math.abs(decimals) || 0;
    number = parseFloat(number);

    if (!decPoint || !thousandsSep) {
        decPoint = '.';
        thousandsSep = ',';
    }

    var roundedNumber = Math.round(Math.abs(number) * ('1e' + decimals)) + '';
    var numbersString = decimals ? (roundedNumber.slice(0, decimals * -1) || 0) : roundedNumber;
    var decimalsString = decimals ? roundedNumber.slice(decimals * -1) : '';
    var formattedNumber = "";

    while (numbersString.length > 3) {
        formattedNumber += thousandsSep + numbersString.slice(-3)
        numbersString = numbersString.slice(0, -3);
    }

    if (decimals && decimalsString.length === 1) {
        while (decimalsString.length < decimals) {
            decimalsString = decimalsString + decimalsString;
        }
    }

    return (number < 0 ? '-' : '') + numbersString + formattedNumber + (decimalsString ? (decPoint + decimalsString) : '');
}



