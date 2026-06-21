package contracts.accounts.success

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть информацию о свежезарегистрированной учётной записи пользователя"

    request {
        method('POST')
        url('/api/v1/accounts')

        headers {
            contentType(applicationJson())
        }

        body([
                "username": "administrator",
                "password": "dm89jULndiFNt5zaZL0Ub4tu5IqTDEoH",
                "role": "ROLE_ADMIN"
        ])
    }

    response {
        status(201)

        headers {
            contentType(applicationJson())
        }

        body([
                "id": 1,
                "username": "administrator",
                "role": "ROLE_ADMIN",
                "createdAt": "2026-06-20T01:40:14"
        ])
    }
}