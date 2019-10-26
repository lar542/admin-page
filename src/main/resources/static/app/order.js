(function($){
	
	//회원 정보
	var userInfo = new Vue({
		el : "#userInfo",
		data : {
			id : {},
			account : {},
			email : {},
			phoneNumber : {},
			status : {},
			registered_at : {},
			unregistered_at : {}
		}
	});
	
	//주문내역
	var orderList = new Vue({
		el : "#orderList",
		data : {
			orderList: {}
		},
		methods: {
			orderItemClick : function(items){
				itemsView(items);
			}
		}
	});
	
	//상품 목록
	var itemList = new Vue({
		el : "#itemList",
		data : {
			itemList : {}
		}
	});
	
	$(document).ready(function(){
		orderInfo(userId);
	});
	
	function orderInfo(id){
		$.get("/api/user/"+id+"/orderInfo", function(res){
			
			//회원 정보
			var user = res.data.user_api_response;
			userInfo.id = user.id;
			userInfo.account = user.account;
			userInfo.email = user.email;
			userInfo.phoneNumber = user.phone_number;
			userInfo.status = user.status;
			userInfo.registered_at = user.registered_at;
			userInfo.unregistered_at = user.unregistered_at;
			
			//주문내역
			orderList.orderList = user.order_group_api_response_list;
		});
	}
	
	function itemsView(items){
		itemList.itemList = items;
	}
})(jQuery);