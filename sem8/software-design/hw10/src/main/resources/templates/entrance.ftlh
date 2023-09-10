<#import "common.ftlh" as common>

<@common.page "Entrance">
    <h1>Enter</h1>
    <form action="/enter" method="post">
        <label for="enterSubscriptionId">Subscription id</label>
        <input value="${subscriptionId!}" name="subscriptionId" id="enterSubscriptionId">
        <@common.error "subscriptionId"/>
        <input type="submit" value="Enter">
    </form>
    <h1>Exit</h1>
    <form action="/exit" method="post">
        <label for="exitSubscriptionId">Subscription id</label>
        <input value="${subscriptionId!}" name="subscriptionId" id="exitSubscriptionId">
        <@common.error "subscriptionId"/>
        <input type="submit" value="Exit">
    </form>
</@common.page>