//위시리스트
function heartClickOn(e) {
    const normal=document.querySelector("#normalHeart");
    const solid=document.querySelector('#solidHeart');
    const resultCnt=document.querySelector('#heartCnt');
    let cntNumber=resultCnt.innerText;
    solid.style.display="none";

    normal.addEventListener('click',function() {
        normal.style.display="none";
        solid.style.display="inline-block";
        cntNumber=parseInt(cntNumber)+1;
        resultCnt.innerText=cntNumber;
    })

    solid.addEventListener('click',function() {
        normal.style.display="inline-block";
        solid.style.display="none";
        cntNumber=parseInt(cntNumber);
        resultCnt.innerText=cntNumber;
    })
}

//product 수량 count
function count(type)  {
    // 결과를 표시할 element
    const resultElement1 = document.getElementById('result1');
    const resultElement2 = document.getElementById('result2');
    const resultPrice0=document.querySelector('#price0');
    const resultPrice1=document.querySelector('#price1');
    const resultPrice2=document.querySelector('#price2');

    // 현재 화면에 표시된 값
    let number1 = resultElement1.innerText;
    let number2 = resultElement2.innerText;
    let priceNumber0 = resultPrice0.innerText;
    let priceNumber1 = resultPrice1.innerText;
    let priceNumber2 = resultPrice2.innerText;

    // 더하기/빼기
    if(type === 'plus') {
        number1 = parseInt(number1) + 1;
        number2 = parseInt(number2) + 1;
        priceNumber1 = parseInt(priceNumber0)*number1;
        priceNumber2 = parseInt(priceNumber0)*number1;
    }
    else if(type === 'minus')  {
        number1 = parseInt(number1) - 1;
        number2 = parseInt(number2) - 1;
        priceNumber1 = parseInt(priceNumber1)-parseInt(priceNumber0);
        priceNumber2 = parseInt(priceNumber2)-parseInt(priceNumber0);

        if(number1<=1 && number2<=1){
            number1=1;
            number2=1;
            priceNumber1=parseInt(priceNumber0);
            priceNumber2=parseInt(priceNumber0);
        }
    }
    // 결과 출력
    resultElement1.innerText = number1;
    resultElement2.innerText = number2;
    resultPrice1.innerText = priceNumber1;
    resultPrice2.innerText = priceNumber2;
}


//product sub box
function detailInformation() {
    const detailbox=document.querySelector('.storeProductSubLi1');
    const productcommentbox=document.querySelector('.storeProductSubLi2');
    const qnabox=document.querySelector('.storeProductSubLi3');
    const productchangebox=document.querySelector('.storeProductSubLi4');
    detailbox.style.display="block";
    productcommentbox.style.display="none";
    qnabox.style.display="none";
    productchangebox.style.display="none";
}

function productcomment() {
    const detailbox=document.querySelector('.storeProductSubLi1');
    const productcommentbox=document.querySelector('.storeProductSubLi2');
    const qnabox=document.querySelector('.storeProductSubLi3');
    const productchangebox=document.querySelector('.storeProductSubLi4');
    detailbox.style.display="none";
    productcommentbox.style.display="block";
    qnabox.style.display="none";
    productchangebox.style.display="none";
}
function qna() {
    const detailbox=document.querySelector('.storeProductSubLi1');
    const productcommentbox=document.querySelector('.storeProductSubLi2');
    const qnabox=document.querySelector('.storeProductSubLi3');
    const productchangebox=document.querySelector('.storeProductSubLi4');
    detailbox.style.display="none";
    productcommentbox.style.display="none";
    qnabox.style.display="block";
    productchangebox.style.display="none";
}
function productchange() {
    const detailbox=document.querySelector('.storeProductSubLi1');
    const productcommentbox=document.querySelector('.storeProductSubLi2');
    const qnabox=document.querySelector('.storeProductSubLi3');
    const productchangebox=document.querySelector('.storeProductSubLi4');
    detailbox.style.display="none";
    productcommentbox.style.display="none";
    qnabox.style.display="none";
    productchangebox.style.display="block";
}


//pagination 코드 (Jquery 강의 69)
$(function () {
    var rowsPerPage = 7, //한 페이지에 나오는 list 수
        rows = $('#myTable tbody tr'), //table 에서 값 받아오기
        rowsCount = rows.length, //table 에서 받아온 값 개수
        pageCount = Math.ceil(rowsCount / rowsPerPage),
        numbers = $('#numbers');

    /* 페이지네이션 li생성 반복문*/
    for (var i = 1; i <= pageCount; i++) {
        numbers.append('<li><a href="#">' + i + '</a></li>');
    }
    ;
    numbers.find('li:first-child a').addClass('active');

    //rowsPerPage에 저장된 수만큼 보여주기
    function displayRows(idx) {
        var start = (idx - 1) * rowsPerPage,
            end = start + rowsPerPage;

        rows.hide();
        rows.slice(start, end).show();
    };
    displayRows(1);
    //숫자 버튼을 누르면 해당하는 번호 list 보여주기
    numbers.find('li a').click(function (e) {
        e.preventDefault();
        numbers.find('li a').removeClass('active');
        $(this).addClass('active');
        var index = $(this).text();
        displayRows(index);
    });
})
//radio button 체크하고 조회 버튼 누르면 백에 id값 보내기
$(function () {
    $('#noticeSerchButton, #noticeDeleteButton, #qnaSerchButton, #qnaDeleteButton').click(function () {
        var rowData = new Array();
        var $checkbox = $("input[name=inlineRadioOptions]:checked").val();
        console.log($checkbox);
        console.log(typeof ($checkbox));
    });
});

//signup 페이지 모두 동의 버튼 , 동의 안하면 안넘어가게
$(function () {
    var agree = $("input[name=agree]");
    var agree_all = $("input[name=agree_all]");
    var $submitBtn = $(".signUpBtnSubmit");

    agree_all.click(function () {
        if ($(this).is(":checked")) {
            agree.prop("checked", true);
            $submitBtn.css({
                opacity: 1,
            }).prop("disabled", false);
        } else {
            agree.prop("checked", false);
            $submitBtn.css({
                opacity: 0.6,
            }).prop("disabled", true);
        }
    })
    agree.click(function () {
        var total = agree.length;
        var checked = $("input[name=agree]:checked").length;

        if (total != checked) {
            agree_all.prop("checked", false);
            $submitBtn.css({
                opacity: 0.6,
            }).prop("disabled", true);
        } else {
            agree_all.prop("checked", true);
            $submitBtn.css({
                opacity: 1,
            }).prop("disabled", false);
        }
    });
});
// signupSub select 번호 자동 추가
$(function(){
    const year = $('#selectYear');
    const month = $('#selectMonth');
    const day = $('#selectDay');

    for (var i = 1900; i <= 2022; i++) {
        year.append('<option value="i">'+i+'</option>');
    }
    for (var i = 1; i <= 12; i++) {
        month.append('<option value="i">'+i+'</option>');
    }
    for (var i = 1; i <= 31; i++) {
        day.append('<option value="i">'+i+'</option>');
    }
});
//order 주문자,배송정보 변경 버튼
$(function (){
    var changeBtn = $('#orderDeliveryChange');
    var fixBtn = $('#orderUserFixBtn');

    fixBtn.click(function (){
        $('.orderUserInfoDefault').css({display: 'none'});
        $('.orderUserInfoChange').css({});
        fixBtn.css({display: 'none'});
    });
    changeBtn.click(function(){
        $('.orderProductDeliveryDefault').css({display: 'none'});
        $('.orderProductDeliveryChange').css({display: 'block'});
        changeBtn.css({display: 'none'});
    });
    $('.orderSelectBtn').click(function(){
        $('.orderProductDeliveryDefault').css({display: 'none'});
        $('.orderProductDeliveryTab').css({display: 'block'});
        $('.orderProductDeliveryTabNew').css({display: 'none'});
    })
    $('.orderNewBtn').click(function (){
        $('.orderProductDeliveryDefault').css({display: 'none'});
        $('.orderProductDeliveryTab').css({display: 'none'});
        $('.orderProductDeliveryTabNew').css({display: 'block'});
    })
});
//order 총금액 계산
$(function (){
    var productFee = $('.orderProductFee p:nth-child(2)').text().slice(1);
    var delivaryFee = $('.orderProductDelFee p:nth-child(2)').text().slice(2);

    function addComma(value){
        value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        return value;
    }
    function minusComma(value){
        value = value.replace(/[^\d]+/g, "");
        return value;
    }

    productFee = minusComma(productFee);
    delivaryFee = minusComma(delivaryFee);

    var totalFee = parseInt(productFee) + parseInt(delivaryFee);
    totalFee = String(totalFee);
    totalFee = addComma(totalFee);

    $('.orderSummaryTotal').append('<p>₩' + totalFee +'</p>');
});

$(function () {
    $('#adminFixBtn').click(function () {
        var $checkbox = $("input[name=inlineRadioOptions]:checked").val();
        var dataId = {
            id: $checkbox
        };

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            beforeSend: function(xhr){
                xhr.setRequestHeader(header,token);
            },
            url: "/admin/update",
            type: "GET",
            data: dataId,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
            success: function (data) {
                console.log('success');
                console.log(data);
                $("#id").val(data.id);
                $("#nameUpdate").val(data.name);
                $("#priceUpdate").val(data.price);
                $("#categoryUpdate").val(data.category);
                $("#descriptionUpdate").val(data.description);
                $("#amountUpdate").val(data.amount);
                $("#imagesUpdate").val(data.images);
            }
        });
    });
});

$(function () {
    $('#adminDeleteBtn').click(function () {
        var $checkbox = $("input[name=inlineRadioOptions]:checked").val();
        var dataId = {
            id: $checkbox
        };

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            beforeSend: function(xhr){
                xhr.setRequestHeader(header,token);
            },
            url: "/admin/delete",
            type: "POST",
            data: dataId,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
            success: function (data) {
                console.log('success');
                location.reload();
            }
        });
    });
});

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) { xhr.setRequestHeader(header, token); });

//basket checkbox function
$(function () {
    var check = $("input[name=check]");
    var check_all = $("input[name=check_all]");

    check_all.click(function () {
        if ($(this).is(":checked")) {
            check.prop("checked", true);
        } else {
            check.prop("checked", false);
        }
    })
    check.click(function () {
        var total = check.length;
        var checked = $("input[name=check]:checked").length;

        if (total != checked) {
            check_all.prop("checked", false);
        } else {
            check_all.prop("checked", true);
        }
    });
});

//basket checkbox 선택되면 해당 상품 개수 가져오기
$(function() {
    var check_all = $("input[name=check_all]");
    var check = $('input[name=check]'); //box 객체 가져오기
    var productCnt = $('#productCnt').text();
    var resultProductCnt = parseInt(productCnt);

    check_all.click(function () {
        if ($(this).is(":checked")) {
            $('#productCnt').empty();
            $('#productCnt').append(check.length);
            resultProductCnt=check.length;
        } else {
            resultProductCnt=parseInt(productCnt);
            $('#productCnt').empty();
            $('#productCnt').append(resultProductCnt);
        }
    })

    check.click(function() {
        if ($(this).is(":checked")) {
            resultProductCnt++;
            $('#productCnt').empty();
        } else {
            resultProductCnt--;
            $('#productCnt').empty();
        }
        if(resultProductCnt<0){
            resultProductCnt=parseInt(productCnt);
        }
        $('#productCnt').append(resultProductCnt);
    })
})

//basket 위시리스트 5개만 보이게
$(document).ready( function() {
    let basketWish=$('.basketCol');
    let basketCnt=basketWish.length;
    if(basketCnt>5){
        $('.basketCol:nth-of-type(n+6)').css({display: 'none'});
    }
});

//basket card img 어둡게
$(document).ready(function(){
    $('.basketCard img').hover(function() {
        $(this).css({filter: "brightness(50%)"});
    }, function(){
        $(this).css({filter: "brightness(100%)"});
    });
});

//총 주문금액 테이블 변경
$(function() {
    var check = $('input[name=check]'); //box 객체 가져오기
    var check_all = $("input[name=check_all]");
    var delivery=$('#basketDeliveryPrice').text(); //table에서 배송비 가져오기
    var productPrice=$('#basketTableProductPrice').text(); //table에서 상품 가격 가져오기
    var resultDelivery=$('#basketDelivery'); //여기에 배송비 넣기
    var resultProductPrice=$('#basketPrice'); //여기에 가격 넣기
    var total = $('#basketTotal'); //총 주문금액 넣기

    function addComma(value){
        value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        return value;
    }
    function minusComma(value){
        value = value.replace(/[^\d]+/g, "");
        return value;
    }

    delivery=minusComma(delivery);
    productPrice=minusComma(productPrice);

    var totalPrice=parseInt(delivery)+parseInt(productPrice); //총 주문금액
    totalPrice=String(totalPrice);
    totalPrice=addComma(totalPrice);

    check_all.click(function () {
        if ($(this).is(":checked")) {
            resultDelivery.empty();
            resultProductPrice.empty();
            total.empty();
            total.append(totalPrice);
            resultProductPrice.append(addComma(productPrice));
            resultDelivery.append(addComma(delivery));
        } else {
            resultDelivery.empty();
            resultProductPrice.empty();
            total.empty();
            resultProductPrice.append(0);
            resultDelivery.append(0);
            total.append(0);
        }
    })

    check.click(function() {
        if ($(this).is(":checked")) {
            resultDelivery.empty();
            resultProductPrice.empty();
            total.empty();
            total.append(totalPrice);
            resultProductPrice.append(addComma(productPrice));
            resultDelivery.append(addComma(delivery));
        } else {
            resultDelivery.empty();
            resultProductPrice.empty();
            total.empty();
            resultProductPrice.append(0);
            resultDelivery.append(0);
            total.append(0);
        }
    })
})