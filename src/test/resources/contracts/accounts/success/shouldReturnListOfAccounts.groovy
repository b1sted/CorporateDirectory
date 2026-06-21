package contracts.accounts.success

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть список всех учётных записей пользователей, которые находятся в базе данных"

    request {
        method('GET')
        url('/api/v1/accounts')
    }

    response {
        status(200)

        headers {
            contentType(applicationJson())
        }

        body([
                [
                        [
                            "id": 1,
                            "username": "administrator",
                            "role": "ROLE_ADMIN",
                            "createdAt": "2026-06-20T01:40:14"
                        ],
                        [
                            "id": 2,
                            "username": "basted",
                            "role": "ROLE_USER",
                            "createdAt": "2026-06-20T01:50:51"
                        ]
                ]
        ])
    }
}