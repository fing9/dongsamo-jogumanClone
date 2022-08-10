//mainImg 화면 전환 script
var index = 0;   //이미지에 접근하는 인덱스
window.onload = function () {
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
    x[index - 1].style.display = "block";  //해당 인덱스는 block으로 (보이게)
    setTimeout(slideShow, 3000);   //함수를 3초마다 호출

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
//signupSub select 형식 알기
$(function(){
    $('.sign-up-sub-btn').click(function(){
        var yeartext = $("select[name=selectYear] option:selected").text();
        var monthtext = $("select[name=selectMonth] option:selected").text();
        var daytext = $("select[name=selectDay] option:selected").text();

        console.log(yeartext, monthtext, daytext);
        console.log(typeof(yeartext), typeof(monthtext),typeof(daytext));
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
//order 결제 정보 radio btn
$(function(){
    var radiochk = $("input[name=orderPayTypeRadio]:checked").val();
    if(radiochk == 'cacao'){
        $("input[name=orderPayTypeRadio]").prop('checked', false);
    }else{

    }

});
