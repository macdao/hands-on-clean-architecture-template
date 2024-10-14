package contracts.client.inventory

import static org.springframework.cloud.contract.spec.Contract.make

[
	make {
		name 'deduct inventory'
		request {
			method 'POST'
			url '/deduct-inventory'
			headers {
				contentType('application/json')
			}
			body(
					productId: "product-id-1",
					quantity: $(consumer(anyPositiveInt()))
					)
		}
		response {
			status OK()
		}
	},
	make {
		name 'deduct inventory failed due to conflict'
		request {
			method 'POST'
			url '/deduct-inventory'
			headers {
				contentType('application/json')
			}
			body(
					productId: "product-id-2",
					quantity: $(consumer(anyPositiveInt()))
					)
		}
		response {
			status CONFLICT()
		}
	},
	make {
		name 'deduct inventory failed'
		request {
			method 'POST'
			url '/deduct-inventory'
			headers {
				contentType('application/json')
			}
			body(
					productId: "product-id-999",
					quantity: $(consumer(anyPositiveInt()))
					)
		}
		response {
			status INTERNAL_SERVER_ERROR()
		}
	}
]