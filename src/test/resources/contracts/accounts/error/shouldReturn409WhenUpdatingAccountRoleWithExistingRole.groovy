package contracts.accounts.error

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть Conflict, если новая роль совпадает с текущей"

    request {
        method('PATCH')
        url('/api/v1/accounts/99/role')

        headers {
            contentType(applicationJson())
        }

        body([
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
                "message": "Нельзя изменить роль: пользователь уже является ROLE_USER"
        ])
    }
}