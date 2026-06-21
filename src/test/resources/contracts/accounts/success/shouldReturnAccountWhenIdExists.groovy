package contracts.accounts.success

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть информацию о учётной записи пользователя по его уникальному идентификатору"

    request {
        method('GET')
        url('/api/v1/accounts/1')
    }

    response {
        status(200)

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