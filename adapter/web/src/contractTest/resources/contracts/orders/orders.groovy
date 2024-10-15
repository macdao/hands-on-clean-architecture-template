import static org.springframework.cloud.contract.spec.Contract.make

[
	make {
		name "place a new order"
		request {
			method POST()
			url "/api/orders"
			headers {
				header authorization(), 'user-token'
				contentType('application/json')
			}
			body(
					productId: "product-id-1",
					quantity: 1,
					price: 100.0
					)
		}
		response {
			status CREATED()
		}
	},
	make {
		name "place an invalid order"
		request {
			method POST()
			url "/api/orders"
			headers {
				header authorization(), 'user-token'
				contentType('application/json')
			}
			body(
					productId: "product-id-1",
					quantity: 1,
					price: null
					)
		}
		response {
			status BAD_REQUEST()
			headers {
				contentType('application/problem+json')
			}
			body(
					type:"about:blank",
					title: "ConstraintViolationException",
					status: 400,
					detail: "Invalid order"
					)
		}
	},
	make {
		name "place an order with invalid price digits"
		request {
			method POST()
			url "/api/orders"
			headers {
				header authorization(), 'user-token'
				contentType('application/json')
			}
			body(
					price: 1000000.001  // 7 integer digits and 3 fraction digits
					)
		}
		response {
			status BAD_REQUEST()
			headers {
				contentType('application/problem+json')
			}
			body(
					type: "about:blank",
					title: "Bad Request",
					status: 400,
					detail: "Invalid request content.",
					"instance":"/api/orders"
					)
		}
	},
	make {
		name "place an order with empty body"
		request {
			method POST()
			url "/api/orders"
			headers {
				header authorization(), 'user-token'
				contentType('application/json')
			}
		}
		response {
			status BAD_REQUEST()
			headers {
				contentType('application/problem+json')
			}
			body(
					type: "about:blank",
					title: "Bad Request",
					status: 400,
					detail: "Failed to read request",
					"instance":"/api/orders"
					)
		}
	}
]