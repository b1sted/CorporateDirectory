package contracts.accounts.success

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть ничего после успешного удаления аккаунта"

    request {
        method('DELETE')
        url('/api/v1/accounts/1')
    }

    response {
        status(204)
    }
}