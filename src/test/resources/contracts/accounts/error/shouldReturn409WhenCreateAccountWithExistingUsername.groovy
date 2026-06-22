package contracts.accounts.error

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть Conflict при попытке зарегистрировать учётную запись с уже занятым именем пользователя"

    request {
        method('POST')
        url('/api/v1/accounts')

        headers {
            contentType(applicationJson())
        }

        body([
                "username": "basted",
                "password": "dm89jULndiFNt5zaZL0Ub4tu5IqTDEoH",
                "role": "user"
        ])
    }

    response {
        status(409)

        headers {
            contentType(applicationJson())
        }

        body([
                "timestamp": anyDateTime(),
                "status": 409,
                "error": "Conflict",
                "message": "Имя пользователя занято"
        ])
    }
}