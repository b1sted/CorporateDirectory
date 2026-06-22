package contracts.auth

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть Not Found при попытке обратиться к ручке с отсутствующим или протухшим токеном"

    request {
        method('GET')
        url('/dummy-auth/401')
    }

    response {
        status(404)

        headers {
            contentType(applicationJson())
        }

        body([
                "timestamp": anyDateTime(),
                "status": 404,
                "error": "Not Found",
                "path": "/dummy-auth/401"
        ])
    }
}