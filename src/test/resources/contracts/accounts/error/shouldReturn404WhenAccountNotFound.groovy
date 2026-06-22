package contracts.accounts.error

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть Not Found при попытке получить информацию о несуществующей учётной записи"

    request {
        method('GET')
        url('/api/v1/accounts/99')
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
                "message": "Пользователь c ID 99 не найден"
        ])
    }
}