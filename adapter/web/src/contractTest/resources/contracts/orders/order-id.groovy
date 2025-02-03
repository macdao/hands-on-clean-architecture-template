import java.time.Instant

import static org.springframework.cloud.contract.spec.Contract.make

[
	make {
		name "get order"
		request {
			method GET()
			url "/api/orders/order-id-1"
			headers {
				header authorization(), 'user-token'
			}
		}
		response {
			status OK()
			body(
					id: "order-id-1",
					buyerId: "user-id-1",
					productId: "product-id-1",
					quantity: 10,
					status: "CREATED",
					price: 100.0,
					createdDate: "2025-02-03T15:00:00Z",
					lastModifiedDate:"2025-02-03T15:00:00Z"
					)
		}
	},
	make {
		name "get order but not found"
		request {
			method GET()
			url "/api/orders/order-id-2"
			headers {
				header authorization(), 'user-token'
			}
		}
		response {
			status NOT_FOUND()
			headers {
				contentType('application/problem+json')
			}
			body(
					type:"about:blank",
					title: "OrderNotFoundException",
					status: 404,
					detail: "Order not found",
					instance:"/api/orders/order-id-2"
					)
		}
	}
]