# app-monitor

app-monitor是应用监控的一部分，为应用提供统一的方式与外部的基础系统对接，例如：nagios，这样应用可以专注于业务处理，而不用去关心如何与外部系统的交互。这里需要注意，app-monitor是对监控手段的一种补充，将应用需要对外展示的数据与外部的监控系统进行关联，另外，JVM的监控也是应用监控的一部分，但是这部分的监控因为JVM提供RMI的方式可以从外部进行监控，所以不放在这个区域。

# 用法

参考 https://github.com/leo27lijiang/app-monitor/wiki
