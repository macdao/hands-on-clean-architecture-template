import static org.springframework.cloud.contract.spec.Contract.make

[make {
    name "pay an order"
    request {
        method POST()
        url "/orders/order-id-1/pay"
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
    }
    response {
        status CONFLICT()
    }
}]