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
	}
]