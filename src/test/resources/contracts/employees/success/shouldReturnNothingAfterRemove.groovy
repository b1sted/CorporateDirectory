package contracts.employees.success

import org.springframework.cloud.contract.spec.Contract

Contract.make{
    description("Должен вернуть ничего после удаления сотрудника из базы данных")

    request {
        method('DELETE')
        url('/api/v1/employees/1')
    }

    response {
        status(204)
    }
}