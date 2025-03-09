// lấy quyền
app.run(function($http, $rootScope) {
	$http.get(`/rest/security/authentication`).then(resp => {
		if (resp.data) {
			$auth = $rootScope.$auth = resp.data;
			$http.defaults.headers.common["Authorization"] = $auth.token;
		}
	});
})

app.config(function($routeProvider, $locationProvider) {
	$locationProvider.html5Mode(true);
	$locationProvider.hashPrefix('');

	$routeProvider
		.when('/staff/index.html', {
			templateUrl: '/staff/views/index.html',
			pageTitle: 'Dashboard'
		})
		.when('/', {
			redirectTo: '/staff/index.html'
		})
		.when('/staff/accounts', {
			templateUrl: '/staff/views/account.html',
			pageTitle: 'Quản lý tài khoản'
		})
		.when('/staff/bookings', {
			templateUrl: '/staff/views/booking.html',
			pageTitle: 'Lịch đặt sân'
		})
		.when('/staff/events', {
			templateUrl: '/staff/views/event-list.html',
			pageTitle: 'Sự kiện'
		})
		.when('/staff/fields', {
			templateUrl: '/staff/views/field-list.html',
			pageTitle: 'Sân'
		})
		.when('/staff/order-products', {
			templateUrl: '/staff/views/orderproduct.html',
			pageTitle: 'Đặt hàng'
		})
		.when('/staff/products', {
			templateUrl: '/staff/views/products-list.html',
			pageTitle: 'Sản phẩm'
		})
		.when('/staff/teams', {
			templateUrl: '/staff/views/team-list.html',
			pageTitle: 'Đội'
		})
		.when('/staff/vouchers', {
			templateUrl: '/staff/views/voucher-list.html',
			pageTitle: 'Mã khuyến mãi'
		})
		.when('/staff/category-product', {
			templateUrl: '/staff/views/category-product.html',
			pageTitle: 'Loại sản phẩm'

		})
		.when('/staff/category-sport', {
			templateUrl: '/staff/views/category-sport.html',
			pageTitle: 'Loại thể thao'
		})
		.when('/staff/contacts', {
			templateUrl: '/staff/views/contact.html',
			pageTitle: 'Liên hệ'
		})
		.when('/staff/reportBooking', {
			templateUrl: '/staff/views/reportBooking.html',
			pageTitle: 'Báo cáo thống kê đặt sân'
		})
		.when('/staff/reportOrder', {
			templateUrl: '/staff/views/reportOrder.html',
			pageTitle: 'Report Order'
		})
		.when('/staff/reportAll', {
			templateUrl: '/staff/views/reportAll.html',
			pageTitle: 'Report All'
		})
		.when("/staff/unauthorized", {
			templateUrl: "/staff/views/error-500.html",
			pageTitle: 'Unauthorized'
		})


}).run(function($rootScope, $route) {
	$rootScope.$on('$routeChangeSuccess', function() {
		$rootScope.pageTitle = $route.current.pageTitle;
		$rootScope.version = Math.random().toString().substr(2, 8);
	});
});

