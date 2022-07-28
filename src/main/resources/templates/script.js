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

$("#btnRegistration").click(function(){
    registration_product();
});

function registration_product() {
    $('#tableid').empty() //먼저 만들어두었던 상품 틀 제거
    $.ajax({
        type:"GET",
        // url="", //product api
        data:{},
        success:function (response) {
            let product_id=response['product_id']
            let product_category=response['product_category']
            let product_name=response['product_name']
            let product_price=response['product_price']
            let product_amount=response['product_amount']
            let registration_date=response['registration_date']
            for(let i=0;i<rows.length;i++) {
                let product_id=rows[i]['product_id']
                let product_category=rows[i]['product_category']
                let product_name=rows[i]['product_name']
                let product_price=rows[i]['product_price']
                let product_amount=rows[i]['product_amount']
                let registration_date=rows[i]['registration_date']

                let temp_html=`<tr>
                                    <th scope="col">${product_id}</th>
                                    <th scope="col">${product_category}</th>
                                    <th scope="col">${product_name}</th>
                                    <th scope="col">${product_price}</th>
                                    <th scope="col">${product_amount}</th>
                                    <th scope="col">${registration_date}</th>
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
