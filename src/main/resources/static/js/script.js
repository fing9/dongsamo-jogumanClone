//mainImg 화면 전환 script
let index = 0;   //이미지에 접근하는 인덱스
window.onload = function(){
    slideShow();
}

function slideShow() {
    let i;
    let x = document.getElementsByClassName("slide1");  //slide1에 대한 dom 참조

    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";   //처음에 전부 display를 none으로 지정 (안보이게)
    }
    index++;
    if (index > x.length) {
        index = 1;  //인덱스가 초과되면 1로 변경
    }
    x[index-1].style.display = "block";  //해당 인덱스는 block으로 (보이게)
    setTimeout(slideShow, 3000);   //함수를 3초마다 호출

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
// window.onload = function(){
//
// }
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

//siema slider 코드 (Jquery 강의 38)
$(function(){
    function onInitCallback() {
        console.log('Siema initialised bro :)');
    }
    function onChangeCallback() {
        console.log(`The index of current slide is: ${this.currentSlide}`);
    }
    var timer

    const mySiema = new Siema({
        selector:'.slider',
        onInit: onInitCallback,
        onChange: onChangeCallback,
        loop: true,
        duration: 800,
    });
    function autoSlide(){
        timer = setInterval(function(){
            mySiema.next();
        }, 4000);
    }
    autoSlide();
    $('.slider').mouseover(function(){
        clearInterval(timer);
    }).mouseout(function(){
        autoSlide();
    });

    $('.prev').click(function(){
        mySiema.prev();
    });
    $('.next').click(function(){
        mySiema.next();
    });

});

//pagination 코드 (Jquery 강의 69)
$(function(){
    var rowsPerPage = 7,
        rows = $('#myTable tbody tr'),
        rowsCount = rows.length,
        pageCount = Math.ceil(rowsCount/rowsPerPage),
        numbers = $('#numbers');

    /* 페이지네이션 li생성 반복문*/
    for(var i = 1; i<=pageCount; i++) {
        numbers.append('<li><a href="#">' + i +'</a></li>');
    };
    numbers.find('li:first-child a').addClass('active');

    //페이지네이션 함수
    function displayRows(idx){
        var start = (idx - 1) * rowsPerPage,
            end = start + rowsPerPage;

            rows.hide();
            rows.slice(start, end).show();
    };
    displayRows(1);

    numbers.find('li a').click(function(e){
        e.preventDefault();
        numbers.find('li a').removeClass('active');
        $(this).addClass('active');
        var index = $(this).text();
        displayRows(index);
    });
});
