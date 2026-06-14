package contracts.employees.error

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("При попытке найти сотрудника по несуществующему " +
            "идентификатору должен возвращаться код ошибки 404 Not Found")

    request {
        method('PUT')
        url('/api/v1/employees/99')

        headers {
            contentType(applicationJson())
        }

        body([
                "fullName": "Teresa Grant",
                "email": "Everett.Beatty@gmail.com",
                "department": "Program",
                "position": "Lead Interactions Executive"
        ])
    }

    response {
        status(404)

        headers {
            contentType(applicationJson())
        }

        body([
                "timestamp": anyDateTime(),
                "status": 404,
                "error": "Not Found",
                "message": "Сотрудник c ID 99 не найден"
        ])
    }
}