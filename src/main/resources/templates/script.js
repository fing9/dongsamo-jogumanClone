// $(document).ready(function(){
//     listing();
// });
// //로딩후 바로실행
// function listing() {
//     $('#subImg').empty() //먼저 만들어두었던 이미지 카드 제거
//     $.ajax({
//         type:"GET",
//         // url="", //img api
//         data:{},
//         success:function (response) {
//             let rows=response['images'] //img name
//             for(let i=0;i<rows.length;i++) {
//                 let image=rows[i]['image']
//
//                 let temp_html=`<div class="col">
//                                     <div class="card">
//                                         <img src="${image}" class="card-img-top" alt="...">
//                                     </div>
//                                 </div>`
//
//                 $('#subImg').append(temp_html) //이미지 카드 붙이기
//             }
//         }
//     })
// }

//mainImg 화면 전환 script
var index = 0;   //이미지에 접근하는 인덱스
window.onload = function(){
    slideShow();
};

function slideShow() {
    var i;
    var x = document.getElementsByClassName("slide1");//slide1에 대한 dom 참조
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

//Header 불러오는 코드
window.addEventListener('load', function() {
    var allElements = document.getElementsByTagName('*');
    Array.prototype.forEach.call(allElements, function(el) {
        var includePath = el.dataset.includePath;
        if (includePath) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    el.outerHTML = this.responseText;
                }
            };
            xhttp.open('GET', includePath, true);
            xhttp.send();
        }
    });
});
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