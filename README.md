# Agent-Benchmarks
Everyone, who wants to use skywalking, has concerns about our auto-instrument agent. The applications, insides this repository, will be used performance tests.

## Baseline
The baseline is running the benchmarks **without** skywalking agent. And provide these:
* CPU: Percent of the running machine, we prefer to do this in physical machine.
* TPS: Transaction per secondes.
* Response Time. Unit: millisecond

## Running with agent
The same metrics are collected with the baseline.

## Agent Cost
Agent Cost = `Running with agent` - `Baseline`
