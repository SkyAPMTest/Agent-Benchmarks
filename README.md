# Agent-Benchmarks
Everyone, who wants to use skywalking, has concerns about the performances of our auto-instrument agent. The applications, insides this repository, will be used performance tests.

## Baseline
The baseline is running the benchmarks **without** skywalking agent. And provide these:
* CPU: Percent of the running machine, we prefer to do this in physical machine.
* TPS: Transaction per secondes.
* Response Time. Unit: millisecond

## Running with agent
The same metrics are collected with the baseline.

## Agent Cost
Agent Cost = `Running with agent` - `Baseline`


## Benchmarks
### Benchmark-1
This is a very common Spring-based system, includes Spring Boot, Spring MVC, simulate-redis-client, simulate-mysql-client.

Please noted: we used the **simulate-*-client** to simulate the client lib, instead the real client library, in order to avoid effections of server-side and network performance. 

e.g. The network and redis server config are effecting the client performance, but this is our testing purpose.

#### Test Result
1. Baseline diagram

2. Running with agent diagram

3. Agent Cost diagram
