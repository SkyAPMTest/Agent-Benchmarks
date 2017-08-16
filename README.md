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
This is a very common Spring-based system, includes Spring Boot, Spring MVC, simulate-redis-client, HikariCP connection pool(with simulate-mysql-client).

**Please notice**: we used the **simulate-*-client** to simulate the client lib, instead the real client library, in order to avoid effections of server-side and network performance. 

e.g. The network and config of mysql/redis server are effecting the client performance, but this is our testing purpose.

#### Test Result
We used jmeter to run performance test in a physical machine, which has 4 Intel(R) Core(TM) i5-4460  CPU @ 3.20GH, 16G memory, and the OS is CentOS Linux release 7.2.1511 (Core).

* High tps application

We simulated 500 users to access the application, with 10ms thought time. Because our application is so fast, the result is about 4000 transactions per second during the test.

![Metrics data](https://github.com/sky-walking/page-resources/blob/master/3.2/performance/contrast_graph.png)



