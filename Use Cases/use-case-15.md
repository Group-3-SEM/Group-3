# USE CASE: 15 Produce a Report on the top (N) Populated Cities in the Given Country.

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *Employee* I want *to produce a report on the top (N) populated cities in the country where N is provided by the organisation* so that *I can provide this information to the organisation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the country. Database contains current data on the world.

### Success End Condition

A report is available for the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

Employee.

### Trigger

A request for a report is sent to the employee.

## MAIN SUCCESS SCENARIO

1. Organisation requests the population report
2. Employee extracts a list of the top (N) populated cities in the given country.
3. Employee provides report to organisation.

## EXTENSIONS

1. Country doesn't exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
