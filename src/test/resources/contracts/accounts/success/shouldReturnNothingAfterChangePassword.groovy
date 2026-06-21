package contracts.accounts.success

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть ничего после успешной смены пароля от учётной записи"

    request {
        method('PATCH')
        url('/api/v1/accounts/1/password')

        headers {
            contentType(applicationJson())
        }

        body([
                "password": "CFQnZwOC51JSmHlV2gRMZ1BtwG8bDIQm"
        ])
    }

    response {
        status(204)
    }
}