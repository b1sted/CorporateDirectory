package contracts.accounts.error

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть Bad Request при некорректных имени пользователи и пароле, " +
            "поданных на вход регистрационному endpoint"

    request {
        method('POST')
        url('/api/v1/accounts')

        headers {
            contentType(applicationJson())
        }

        body([
                "username": "    ",
                "password": "        ",
                "role": ""
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
                    "password": "Пароль не должен быть пустым",
                    "role": "Роль не должна быть пустой",
                    "username": "Имя пользователя не должно быть пустым"
                ]
        ])
    }
}