package contracts.employees.error

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Должен вернуть 409 Conflict при попытке замены текущих данных сотрудника с " +
            "электронной почтой, занятой другим сотрудником")

    request {
        method('PUT')
        url('/api/v1/employees/1')

        headers {
            contentType(applicationJson())
        }

        body([
                "fullName": "Ruth Hoeger",
                "email": "Ruth.Hoeger@basted.ru",
                "department": "Marketing",
                "position": "Regional Infrastructure Director"
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
                "message": "Пользователь с таким адресом электронной почты уже существует в базе данных"
        ])
    }
}