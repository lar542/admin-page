(function ($) {

    var maxBtnSize = 10;              // 검색 하단 최대 범위
    var indexBtn = [];               // 인덱스 버튼

    // 페이징 처리 데이터
    var pagination = {
        total_pages : 0,            // 전체 페이지수
        total_elements : 0,         // 전체 데이터수
        current_page :  0,          // 현재 페이지수
        current_elements : 0        // 현재 데이터수
    };


    // 페이지 정보
    var showPage = new Vue({
        el : '#showPage',
        data : {
        	currentPage: {},
    		totalPages : {}
        }
    });

    // 데이터 리스트
    var itemList = new Vue({
        el : '#itemList',
        data : {
            itemList : {}
        },
        methods: {
        	itemClick: function(id){
        		getItem(id);
        	}
        }
    });

    // 페이지 버튼 리스트
    var pageBtnList = new Vue({
        el : '#pageBtn',
        data : {
            btnList : {}
        },
        methods: {
            indexClick: function (id) {
                searchStart(id-1)
            },
            previousClick:function () {
                if(pagination.current_page !== 0){
                    searchStart(pagination.current_page-1);
                }
            },
            nextClick:function () {
                if(pagination.current_page !== pagination.total_pages-1){
                    searchStart(pagination.current_page+1);
                }
            }
        },
        mounted:function () {
            // 제일 처음 랜더링 후 색상 처리
            setTimeout(function () {
                $('li[btn_id]').removeClass( "active" );
                $('li[btn_id='+(pagination.current_page+1)+']').addClass( "active" );
            },50)
        }
    });


    $('#search').click(function () {
        searchStart(0)
    });

    $(document).ready(function () {
        searchStart(0)
    });
    
    function searchStart(index) {
        console.log("call index : "+index);
        $.get("/api/category?page="+index, function (response) {

            /* 데이터 셋팅 */
            // 페이징 처리 데이터
            indexBtn = [];
            pagination = response.pagination;


            //전체 페이지
            showPage.currentPage = pagination.current_page+1;
            showPage.totalPages = pagination.total_pages;


            // 검색 데이터
            itemList.itemList = response.data;


            // 이전버튼
            if(pagination.current_page === 0){
                $('#previousBtn').addClass("disabled")
            }else{
                $('#previousBtn').removeClass("disabled")
            }


            // 다음버튼
            if(pagination.current_page === pagination.total_pages-1){
                $('#nextBtn').addClass("disabled")
            }else{
                $('#nextBtn').removeClass("disabled")
            }

            // 페이징 버튼 처리
            var temp = Math.floor(pagination.current_page / maxBtnSize);
            for(var i = 1; i <= maxBtnSize; i++){
                var value = i+(temp*maxBtnSize);

                if(value <= pagination.total_pages){
                    indexBtn.push(value)
                }
            }

            // 페이지 버튼 셋팅
            pageBtnList.btnList = indexBtn;

            // 색상처리
            setTimeout(function () {
                $('li[btn_id]').removeClass( "active" );
                $('li[btn_id='+(pagination.current_page+1)+']').addClass( "active" );
            },50)
        });
    }
    
    function getItem(id){
    	$.get("/api/category/"+id, function(res){
    		if(res['result_code'] != 'OK'){
    			alert('해당 카테고리를 찾을 수 없습니다');
    			return false;
    		}
    		var data = res.data;
    		$('#category_id').val(data.id);
    		$('#category_type').val(data.type);
    		$('#category_title').val(data.title);
    		
    		//버튼 수정
    		$('#createBtn').hide();
    		$('#btnGroup').show();
    	});
    }
    
})(jQuery);

function create(){
	var arr = $('#categoryForm').serializeArray();
	var reqBody = {
		"data":{}
	};
	for(var i = 0; i < arr.length; i++){
		if(arr[i]['name'] == "id"){
			continue;
		}
		var tmp = arr[i]['value'];
		if(tmp.length < 1){
			alert('값을 입력해주세요');
			$('#category_' + arr[i]['name']).focus();
			return false;
		}
		reqBody["data"][arr[i]['name']] = tmp;
	}
	$.ajax({
		url: '/api/category',
		method: 'post',
		contentType: 'application/json',
		data: JSON.stringify(reqBody),
		success: function(res){
			if(res['result_code'] == "OK"){
				alert('등록되었습니다');
				location.reload();
			}
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(jqXHR + '\n' + textStatus + '\n' + errorThrown);
		}
	});
}

function updateBtn(){
	if(confirm("수정하시겠습니까?") == false){
		return;
	}
	var arr = $('#categoryForm').serializeArray();
	var reqBody = {
		"data":{}
	};
	for(var i = 0; i < arr.length; i++){
		var tmp = arr[i]['value'];
		if(tmp.length < 1){
			alert('값을 입력해주세요');
			$('#category_' + arr[i]['name']).focus();
			return false;
		}
		reqBody["data"][arr[i]['name']] = tmp;
	}
	$.ajax({
		url: '/api/category',
		method:	'put',
		contentType: 'application/json',
		data: JSON.stringify(reqBody),
		success: function(res){
			if(res['result_code'] == "OK"){
				alert('수정되었습니다');
				location.reload();
			}
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(jqXHR + '\n' + textStatus + '\n' + errorThrown);
		}
	});
}

function deleteBtn(){
	if(confirm("삭제하시겠습니까?") == false){
		return;
	}
	var id = $('#category_id').val();
	$.ajax({
		url: '/api/category/'+id,
		method: 'delete',
		success: function(res){
			if(res['result_code'] == "OK"){
				alert('삭제되었습니다');
				location.reload();
			}
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(jqXHR + '\n' + textStatus + '\n' + errorThrown);
		}
	});
}

function cancelBtn(){
	$('#category_id').val('');
	$('#category_type').val('');
	$('#category_title').val('');
	$('#createBtn').show();
	$('#btnGroup').hide();
}