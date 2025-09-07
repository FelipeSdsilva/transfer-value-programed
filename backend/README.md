# Transfer Programed Value API

API desenvolvida em **Java 11** utilizando **Spring Boot**, **JPA** e banco de dados em memória **H2**.  
O sistema tem como objetivo gerenciar **transferências bancárias agendadas** com cálculo de **taxas (fees)** de acordo com regras de negócio.

---

## 🚀 Tecnologias
- Java 11
- Spring Boot
- Spring Data JPA
- H2 Database
- Swagger (OpenAPI 3)
- Maven

---

## 📂 Estrutura do Projeto

```
src
└── main
├── java
│ └── com.felipesousa.transfer_programed_value
│ ├── configurations
│ │ └── SwaggerConfig # Configuração do Swagger/OpenAPI
│ ├── controllers
│ │ ├── docs # Interfaces para documentação dos endpoints
│ │ │ ├── BankTransferDoc
│ │ │ └── FeeDoc
│ │ ├── BankTransferController
│ │ └── FeeController
│ ├── dto
│ │ ├── bodies # DTOs para requisições (Request Body)
│ │ │ ├── FeeBodyDTO
│ │ │ └── TransferBodyDTO
│ │ └── response # DTOs para respostas (Response)
│ │ ├── BankTransferDTO
│ │ └── FeeDTO
│ ├── entities # Entidades JPA
│ │ ├── BankTransfer
│ │ └── Fee
│ ├── exceptions
│ │ ├── costomExceptions # Exceções personalizadas
│ │ │ ├── DateRangeInvalidException
│ │ │ └── ResourceNotFoundException
│ │ ├── ControllerExceptionHandler
│ │ ├── FieldMessage
│ │ ├── StandardError
│ │ └── ValidationError
│ ├── repositories # Interfaces JPA Repository
│ │ ├── BankTransferRepository
│ │ └── FeeRepository
│ ├── services # Serviços com regras de negócio
│ │ ├── BankTransferService
│ │ └── FeeService
│ └── TransferProgramedValueApplication # Classe principal
└── resources
└── static
```


---

## ⚙️ Configuração e Execução

### Pré-requisitos
- Java 11+
- Maven 3.6+

### Passos para rodar a aplicação
```bash
# Clonar repositório
git clone https://github.com/seu-repo/transfer-programed-value.git

# Entrar no diretório
cd transfer-programed-value

# Compilar e rodar
mvn spring-boot:run
