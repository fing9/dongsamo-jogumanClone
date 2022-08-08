//mainImg 화면 전환 script
var index = 0;   //이미지에 접근하는 인덱스
window.onload = function(){
    slideShow();
}

function slideShow() {
    var i;
    var x = document.getElementsByClassName("slide1");  //slide1에 대한 dom 참조

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
    var rowsPerPage = 7, //한 페이지에 나오는 list 수
        rows = $('#myTable tbody tr'), //table 에서 값 받아오기
        rowsCount = rows.length, //table 에서 받아온 값 개수
        pageCount = Math.ceil(rowsCount / rowsPerPage),
        numbers = $('#numbers');

    /* 페이지네이션 li생성 반복문*/
    for(var i = 1; i<=pageCount; i++) {
        console.log(pageCount);
        numbers.append('<li><a href="#">' + i +'</a></li>');
    };
    numbers.find('li:first-child a').addClass('active');

    //rowsPerPage에 저장된 수만큼 보여주기
    function displayRows(idx){
        var start = (idx - 1) * rowsPerPage,
            end = start + rowsPerPage;

            rows.hide();
            rows.slice(start, end).show();
    };
    displayRows(1);
    //숫자 버튼을 누르면 해당하는 번호 list 보여주기
    numbers.find('li a').click(function(e){
        e.preventDefault();
        numbers.find('li a').removeClass('active');
        $(this).addClass('active');
        var index = $(this).text();
        displayRows(index);
    });
});
