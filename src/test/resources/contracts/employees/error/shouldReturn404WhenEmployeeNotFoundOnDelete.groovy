package contracts.employees.error

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Должен вернуть 404 Not Found при попытке удаления несуществующего в базе данных сотрудника")

    request {
        method('DELETE')
        url('/api/v1/employees/99')
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