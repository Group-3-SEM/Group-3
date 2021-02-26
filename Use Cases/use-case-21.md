# USE CASE: 12 Produce a Report on the top (N) Populated Capital Cities in the Given Continent.

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *Employee* I want *to produce a report on the top (N) populated Capital Cities in the continent where N is provided by the organisation* so that *I can provide this information to the organisation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the continent. Database contains current data on the world.

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
2. Employee extracts a list of the top (N) populated capital cities in the given continent.
3. Employee provides report to organisation.

## EXTENSIONS

1. Continent doesn't exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
