package contracts.accounts.error

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть Bad Request при попытке установить некорректный пароль учётной записи"

    request {
        method('PATCH')
        url('/api/v1/accounts/99/password')

        headers {
            contentType(applicationJson())
        }

        body([
                "password": "        "
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
                    "request": "Пароль не должен быть пустым"
                ]
        ])
    }
}