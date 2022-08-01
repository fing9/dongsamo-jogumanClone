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

// $(document).ready(function(){ //check박스 선택했을때 서버로 보내주고 조회 버튼 누르면 다시 받아와서 모달창에 띄우기
//     $("#checkBoxId").change(function(){
//         if($("#checkBoxId").is(":checked")){
//             alert("체크박스 체크했음!");
//         }else{
//             alert("체크박스 체크 해제!");
//         }
//     });
// });

const form = document.getElementById("form");
const inputFile = document.getElementById("file");

const formData = new FormData();

const handleSubmit = (event) => {
    event.preventDefault();

    for (const file of inputFile.files) {
        formData.append("files", file);
    }

    fetch("", {
        method: "post",
        body: formData,
    }).catch((error) => ("Something went wrong!", error));
};

form.addEventListener("submit", handleSubmit);


$(document).ready(function (){ //로딩되면 상품 보여주기
    show_product();
})

// $("#btnRegistration").click(function(){ //버튼 눌렀을때 상품 데이터 보내기
//     give_product();
// });

function save_product() {
    let id=$('#id').val()
    let category=$('#category').val()
    let name=$('#name').val()
    let price=$('#price').val()
    let amount=$('#amount').val()
    let images=$('#images').val()

    $.ajax({
        type:"POST",
        url:"/admin/save",
        data:{'id_give':id, 'category_give:category':category, 'name_give:':name, 'price_give':price, 'amount_give':amount, 'images_give':images},
        success:function (response) {
            window.location.reload() //새로고침
            }
    })
}

function show_product() {
    // $('#tableid').empty() //먼저 만들어두었던 상품 틀 제거
    $.ajax({
        type:"GET",
        url:"/admin",
        data:{},
        success:function (response) {
            let id=response['id']
            let category=response['category']
            let name=response['name']
            let price=response['price']
            let amount=response['amount']
            let images=response['images']
            // let registration_date=response['registration_date']
            for(let i=0;i<rows.length;i++) {
                let id=rows[i]['id']
                let category=rows[i]['category']
                let name=rows[i]['name']
                let price=rows[i]['price']
                let amount=rows[i]['amount']
                let images=rows[i]['images']
                // let registration_date=rows[i]['registration_date']

                let temp_html=`<tr>
                                    <th scope="col">${id}</th>
                                    <th scope="col">${category}</th>
                                    <th scope="col">${name}</th>
                                    <th scope="col">${price}</th>
                                    <th scope="col">${amount}</th>
                                    <th scope="col">${registration_date}</th>
                                    <th scope="col">${images}</th>
                                </tr>`

                $('#tableid').append(temp_html) //상품 등록
            }
        }
    })
}

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
