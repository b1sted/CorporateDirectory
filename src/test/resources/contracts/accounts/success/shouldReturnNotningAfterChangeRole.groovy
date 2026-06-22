package contracts.accounts.success

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть ничего после успешной смены роли учётной записи"

    request {
        method('PATCH')
        url('/api/v1/accounts/1/role')

        headers {
            contentType(applicationJson())
        }

        body([
                "role": "ROLE_ADMIN"
        ])
    }

    response {
        status(204)
    }
}