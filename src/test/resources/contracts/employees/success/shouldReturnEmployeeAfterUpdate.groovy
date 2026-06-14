package contracts.employees.success

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Должен вернуть информацию о сотруднике после полной замены его данных")
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