# 探针性能揭秘
[English](README.md)

很多[skywalking](https://github.com/OpenSkywalking/skywalking)探针的使用者，都会对我们的自动探针性能存在顾虑。
在这个仓库中，我们新建了多个应用程序，用于模拟不同的应用程序，来检测我们探针的性能。

下面所有的测试，将基于**skywalking 3.2 java探针**。我们使用jmeter进行压力测试，测试应用程序部署在一台物理机上（注：Collector和jmeter不在此机器上）。
这台物理机配置如下：4 Intel(R) Core(TM) i5-4460  CPU @ 3.20GH, 16G memory。操作系统版本为CentOS Linux release 7.2.1511 (Core)。

每个性能测试包含以下指标内容：
## 基线
基线是测试应用在不安装探针的情况下进行压力测试得到的基准指标值。指标值包括：
* CPU: CPU总体百分比，注：如4核CPU此百分比为基于400%计算而来。
* TPS: 每秒事务数
* Response Time. 响应时间，单位：毫秒

## 带agent压测结果
提供和基线相同的指标输出。

## Agent性能消耗
Agent性能消耗 = `带agent压测结果` - `基线`


## 测试用例
### 用例-1
* [程序源码](https://github.com/SkywalkingTest/Agent-Benchmarks/tree/master/Benchmark-1/example)

这里一个常见的基于Spring的应用程序，他包含Spring Boot, Spring MVC，模拟的redis客户端，HikariCP连接池（匹配模拟的mysql客户端）。
监控这个应用程序，每个事务，探针会抓取5个span(1 Tomcat, 1 SpringMVC, 2 Jedis, 1 Mysql)。

**请注意**：我们这里提到了模拟客户端，之所以我们不使用真正的客户端，是因为，如果这样，服务端性能和网络的波动，会造成应用程序的tps不稳定，从而影响测试结果。如：mysql或redis服务器的配置，网络交换机性能，都会影响客户端性能，而这不是我们的测试目的。所以，我们要避免这种干扰因素。

**这是一个近乎不可能的高流量应用**

我们模拟500并发用户，设置思考时间为10ms。而我们将应用性能设计的十分优秀，每秒能满足5000tps。

![Metrics data](https://skyapmtest.github.io/page-resources/3.2/performance-results/benchmark-1/contrast_graph.png)

大家可以看到，探针进行监控时，针对一个负荷在200%以上的应用，只提高了10%的CPU负荷。并且我们**不需要开启任何采样策略**（注：当然skywalking是支持采样的），所以我们使用每秒要将超过5000个trace segment收集并发送到collector上。显然，skywalking探针拥有极高的性能。如大家所知，在一个x86服务器上的单应用实例，不太可能拥有如此之高的吞吐能力，除非，他内部是直接访问类似redis这样的高速缓存。即使如此，探针对tps和响应时间，也不会造成任何影响。

也就是说，在超高吞吐能力的服务器上，使用探针进行监控，也只是会消耗多一点点CPU，并不会影响应用性能。

老实说，一个单实例的应用，正常的tps都在100到1200之间。据我所知，即使是中国强大的电信和电商系统，单实例处理能力，也不过如此。所以，你真的**不必担心**探针的性能问题。

[查看benchmark-1详细测试数据](Benchmark-1)

我详细，到此，应该可以打消各位对于性能的顾虑。但是如果你依然好奇，skywalking的探针性能极限在哪里？你可以继续阅读下面的测试用例。

### 用例-2
* [程序源码](https://github.com/SkywalkingTest/Agent-Benchmarks/tree/master/Benchmark-2/example)

这个应用和用例-1类似，但是我们做了一些调整，让他更像真实的应用。如我所说，用例-1只是一种证明极限的方法（后面还有更变态的用例☺）。这次，我们模拟300并发用户，将tps稳定在1000，当然，这也是很高的吞吐量了。CPU消耗会比之前明显降低，仅仅消耗**6%**。

![Metrics data](https://skyapmtest.github.io/page-resources/3.2/performance-results/benchmark-2/contrast_graph.png)

通过上图，你可以看到，探针对TPS和响应时间，依然没有影响

[查看benchmark-2详细测试数据](Benchmark-2)

## 偏执狂用例
我为什么称这些用例是`偏执狂`或`疯狂`用例？首先，当[@ascrutae](https://github.com/ascrutae)希望增加这些用例时，我认为他疯了... 这些用例会模拟一些更加不可能出现在生产环境中的应用场景。在每一个用例中，我会具体详细解释，为什么。你只需要记住，这只是极限测试，你真的不太可能这么使用追踪系统。😜

### 用例-3
这里第一个**疯狂**的用例。背景如下：一个5000tps的应用，主机CPU消耗已经在80%以上，但是你依然想追踪他，虽然据我所知，没有人或者公司会允许负荷这么高的服务器来作为生产环境。

但是，我们依然做了这个测试。谢天谢地，探针“幸运的”通过了测试。当然实际上，当然是得益于优良的探针内核设计😎，和运气无关😄。结果如下：

![Metrics data](https://skyapmtest.github.io/page-resources/3.2/performance-results/benchmark-3/contrast_graph.png)

[查看benchmark-3详细测试数据](Benchmark-3)


### 用例-4
这个用例很难说，是真实还是偏执。[@ascrutae](https://github.com/ascrutae)说，一个事务是包含5个span太少了，真实的系统可能更多。所以他决定要将一个事务包含的span提高到20个，包含1 SpringMVC, 2 Jedis, 7 Annotation Trace and 10 Mysql，同时tps要保持在1300（即：高于绝大多数的应用水平）。一个事务使用**@Trace**标注追踪7个本地方法，同时包含10次数据库访问，这肯定超过绝大多数系统的使用场景，但是ascrutae坚持要做这个测试，那么，让我们看看结果吧：


![Metrics data](https://skyapmtest.github.io/page-resources/3.2/performance-results/benchmark-4/contrast_graph.png)

[查看benchmark-4详细测试数据](Benchmark-4)


**如此之多的疯狂的测试场景，一次次的证明，skywalking探针提供的性能，远远超过你的应用系统的吞吐量。所以，请不要担心，在这个分布式横行的年代，让你的系统被监控起来吧。**

## 社区反馈的测试报告

1. [3.2.6 探针+后端性能测试报告](https://github.com/apache/incubator-skywalking/issues/1596#issuecomment-418946050) from [hepyu](https://github.com/hepyu)
