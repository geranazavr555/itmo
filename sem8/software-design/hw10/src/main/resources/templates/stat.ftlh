<#import "common.ftlh" as common>

<@common.page "Statistics">
    <h1>Days to counts</h1>
    <ul>
        <#list stat.dayToCount() as day, count>
            <li>${day}: ${count}</li>
        </#list>
    </ul>
    <h1>User to avg duration</h1>
    <ul>
        <#list stat.userToAvgDuration() as user, duration>
            <li>${user}: ${duration}</li>
        </#list>
    </ul>
</@common.page>