import static org.springframework.cloud.contract.spec.Contract.make

[
	make {
		name "pay an order"
		request {
			method POST()
			url "/orders/order-id-1/pay"
			headers {
				header authorization(), 'user-token'
			}
		}
		response {
			status OK()
		}
	},
	make {
		name "pay an order but conflict"
		request {
			method POST()
			url "/orders/order-id-2/pay"
			headers {
				header authorization(), 'user-token'
			}
		}
		response {
			status CONFLICT()
		}
	},
	make {
		name "pay an order without authorization"
		request {
			method POST()
			url "/orders/order-id-2/pay"
		}
		response {
			status FORBIDDEN()
		}
	},
	make {
		name "pay an order but encounter internal server error"
		request {
			method POST()
			url "/orders/order-id-3/pay"
			headers {
				header authorization(), 'user-token'
			}
		}
		response {
			status INTERNAL_SERVER_ERROR()
			headers {
				contentType('application/problem+json')
			}
			body(
					type: "about:blank",
					title: "RuntimeException",
					status: 500,
					detail: anyNonBlankString()
					)
		}
	}
]