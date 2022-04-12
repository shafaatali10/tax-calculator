# Tax Calculator

## Assumptions

- The annual salary for employee is between 0 and 2147483647 (Integer.MAX)
- The month in the request corresponds to the exact running month. E.g. 1 represents January, 4 represents April and so on..
- There are 5 taxable slabs as provided in the requirements document

## Technical Implementation

- A simple Rule Engine is created to achieve the functionality (com.shafaat.seisma.taxcalc.rules.RuleEngine)
- Rules are maintained in In-Memory DB (h2) to make it dynamic
- All rules are loaded during the start of the application
- No other domain classes are persisted in DB

## Running application locally

- Run App.java and it runs on port 8080

End Point:
```
/api/payslip/monthly
```

Sample Request (Accepts List of Employee):
```
[
    {
        "firstName": "David",
        "lastName": "Rudd",
        "annualSalary": 60050,
        "paymentMonth": 1,
        "superRate": 0.09
    }
]
```


## Build

```
mvn clean install
```

> Note: The application is configured with CI/CD using Github Actions. 
> The application is deployed to AWS ECS. 
> Contact me for more information.