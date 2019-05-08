# Exam

All of the requirements R1 through R5 is completed.

## Usage

From root without running tests:

`mvn clean install -DskipTests`

From root with running tests:

`mvn install`

To run only tests:
 
`mvn clean verify`

To run project:

Go to frontend-module and run 'LocalApplicationRunner'

## Notes

Due to some issues with running the application using H2 I had to extend H2Dialect from Hibernate to make sure it works.

## Extras

- Filtering can filter based on multiple genres
- Changing password

