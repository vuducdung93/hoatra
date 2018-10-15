/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$('.modal-footer').click(function(){
    if(user==null){
        
    }else{
        //console.log(user);
        document.getElementById('checkout').style.left = '0';$('body').css('overflow','hidden');
        $.ajax({
            type: 'GET',
            url: '/checkout',
            dataType: 'json',
            success: function(data){
                var cart=data.cart;
                var location= ` <div class="location-name">`+user.name+`</div>
                                <div class="location-address" style="border-bottom: 1px solid #e7ecef;">
                                    <span><i class="fas fa-address-card"></i></span>
                                    <input type="text" value="`+cart.address+`" placeholder="Please enter address...">
                                </div>
                                <div class="location-address">
                                    <span><i class="fa fa-phone"></i></span>
                                    <input type="number" value="`+cart.phone+`" placeholder="Please enter phone number...">
                                </div>`;
                if(cart.notes!=""){
                    $('#noteCart').text(cart.notes);
                    $('#noteCart').css('color','black');
                }
                var cartitems=data.cartItems;
                console.log(cartitems);
                var html="";
                cartitems.every(function(element,index){
                   var a="";
                   if(element.size == true){
                       a+="size lon";
                   }else{
                     a+=  "size nho";
                   }
                   if(element.list !=null){
                        element.list.forEach(function(itemt){
                            a+=", "+itemt.name;
                        });
                    }
                   switch (element.mucduong) {
                        case 1:
                            a+=", 100% đường";
                            break;
                        case 2:
                             a+=", 70% đường";
                            break;
                        case 3:
                             a+=", 30% đường";
                            break;
                        case 4:
                             a+=", 0% đường";
                            break;
                    }
                    
                   var item=`<div class="o-menu-item">
                                <span class="o-menu-number">`+element.quantity+`</span>
                                <div class="o-menu-name">
                                    `+element.nameP+` &nbsp;
                                    <span class="menu-item-note">[`+a+`]</span>
                                </div>
                                <span class="o-menu-price">`+number_format(element.total*element.quantity,0)+`đ</span>
                            </div>`;
                
                    html+=item;
                    if(index>0){
                        return false;
                    }else{
                         return true;
                    }
                    
                    
                });
                if(cartitems.length>2){
                    var c=0;
                    for(i=2;i<cartitems.length;i++){
                        c+=cartitems[i].quantity;
                    }
                    console.log(c);
                    var dis="";
                    if(c==1){
                        dis=c+" item more ...";
                    }else{
                        dis=c+" items more ...";
                    }
                    html+=`<div class="o-menu-item">
                                    <span class="modal-button text-primary">
                                        <div>`+dis+`</div>
                                    </span>
                                </div>`;
                }
                document.getElementById("location-U").innerHTML=location;
                document.getElementById("cartitemsCheckout").innerHTML=html;
            }
        });
    }
});

