package contracts.employees.success

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Должен вернуть информацию о сотруднике после его регистрации в базе данных")
    request {
        method('POST')
        url('/api/v1/employees')
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
        status(201)
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