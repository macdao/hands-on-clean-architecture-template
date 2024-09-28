import static org.springframework.cloud.contract.spec.Contract.make

[
	make {
		name "place a new order"
		request {
			method POST()
			url "/orders"
			headers {
				header authorization(), 'user-token'
				contentType('application/json')
			}
			body(
					price: 100.0
					)
		}
		response {
			status CREATED()
		}
	}
]