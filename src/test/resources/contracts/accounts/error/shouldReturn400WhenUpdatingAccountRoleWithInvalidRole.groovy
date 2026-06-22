package contracts.accounts.error

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: ""

    request {
        method('PATCH')
        url('/api/v1/accounts/99/role')

        headers {
            contentType(applicationJson())
        }

        body([
                "role": ""
        ])
    }

    response {
        status(400)

        headers {
            contentType(applicationJson())
        }

        body([
                "timestamp": anyDateTime(),
                "status": 400,
                "error": "Bad Request",
                "message": "Ошибка валидации параметров запроса",
                "fieldErrors": [
                    "request": "Роль не должна быть пустой"
                ]
        ])
    }
}