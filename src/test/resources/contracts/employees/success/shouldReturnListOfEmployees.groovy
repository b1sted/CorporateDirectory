package contracts.employees.success

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description: "Должен вернуть список всех сотрудников, которые находятся в базе данных"

    request {
        method('GET')
        url('/api/v1/employees')
    }

    response {
        status(200)
        headers {
            contentType(applicationJson())
        }
        body([
                [
                        "id": 1,
                        "fullName": "Lucia Ernser",
                        "email": "Kayley.Borer@yahoo.com",
                        "department": "Factors",
                        "position": "Legacy Integration Director"
                ],
                [
                        "id": 2,
                        "fullName": "Teresa Grant",
                        "email": "Everett.Beatty@gmail.com",
                        "department": "Program",
                        "position": "Lead Interactions Executive"
                ],
                [
                        "id": 3,
                        "fullName": "Lloyd Rempel",
                        "email": "Mitchel.Upton@gmail.com",
                        "department": "Interactions",
                        "position": "Future Mobility Developer"
                ]
        ])
    }
}