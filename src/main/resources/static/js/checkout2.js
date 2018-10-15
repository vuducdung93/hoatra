/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @author vudung
 */

/* Set rates + misc */
var taxRate = 0.05;
//var shippingRate = 15.00; 
var fadeTime = 300;
var shipping=0;

/* Assign actions */

$('.product-quantity input').change( function() {
    
  if(Number($(this).val())<1){
      $(this).val('1');
  } else{
      updateQuantity(this);
      $.ajax({
        url: "/updateItem",
        method: "POST",
        data: {itemId:$(this).siblings('p').text(),value:$(this).val()},
        dataType: 'text',
        success: function(){
            console.log('ok');
        }
    });
  }
  
});
$('#btnCheckout').click(function (){
    $.ajax({
        url: "/order",
        method: "POST",
        data: {id:$(this).siblings('p').text(),fullname:$('#fname').val(),phone:$('#phone').val(),address:$('#adr').val(),notes:$('#state').val()},
        dataType: 'text',
        success: function(){
            console.log('ok');
        }
    });
});

// remove item cart
$('.product-removal button').click( function() {
    removeItem(this);
    var id=$(this).siblings('p').text();
    console.log(id);
    $.ajax({
        url: "/removeitem",
        method: "POST",
        data: {itemId:id},
        dataType: 'text',
        success: function(res){
            
        }
    });
});



/* Recalculate cart */
function recalculateCart()
{
  var subtotal = 0;
  
  /* Sum up row totals */
  $('.product').each(function () {
    subtotal += parseFloat($(this).children('.cc2').text());
  });
  
  /* Calculate totals */
  //var tax = subtotal * taxRate;
 
  if(subtotal>=49000 && subtotal<=109000){
      shipping=12000;
  }
  var total = subtotal + shipping;
  
  /* Update totals display */
  $('.totals-value').fadeOut(fadeTime, function() {
    $('#cart-subtotal').html(number_format(subtotal, 0)+' đ');
    //$('#cart-tax').html(tax);
    $('#cart-shipping').html(number_format(shipping, 0)+' đ');
    $('#cart-total').html(number_format(total, 0)+' đ');
    if(total == 0){
      $('.checkout').fadeOut(fadeTime);
    }else{
      $('.checkout').fadeIn(fadeTime);
    }
    $('.totals-value').fadeIn(fadeTime);
  });
}


/* Update quantity */
function updateQuantity(quantityInput)
{
  /* Calculate line price */
  var productRow = $(quantityInput).parent().parent();
  var price = parseFloat(productRow.children('.cc1').text());
  var quantity = $(quantityInput).val();
  var linePrice = price * quantity;
  
  /* Update line price display and recalc cart totals */
  productRow.children('.product-line-price').each(function () {
    $(this).fadeOut(fadeTime, function() {
        
      $(this).text(number_format(linePrice, 0)+' đ');
      recalculateCart();
      $(this).fadeIn(fadeTime);
    });
  });  
  productRow.children('.cc2').each(function () {
      $(this).text(linePrice);
  });
}


/* Remove item from cart */
function removeItem(removeButton)
{
  /* Remove row from DOM and recalc cart total */
  var productRow = $(removeButton).parent().parent();
  productRow.slideUp(fadeTime, function() {
    productRow.remove();
    recalculateCart();
  });
}

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