package contracts.employees.success

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Должен вернуть информацию о действующем сотруднике по его уникальному идентификатору")

    request {
        method 'GET'
        urlPath('/api/v1/employees/1')
    }

    response {
        status(200)
        headers {
            contentType(applicationJson())
        }
        body([
                "id": 1,
                "fullName": "Ruth Hoeger",
                "email": "Ruth.Hoeger@basted.ru",
                "department": "Marketing",
                "position": "Regional Infrastructure Director"
        ])
    }
}