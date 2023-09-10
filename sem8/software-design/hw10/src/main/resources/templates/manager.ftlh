<#import "common.ftlh" as common>

<@common.page "Manager">
    <h1>Subscription Info</h1>
    <form action="/info" method="post">
        <label for="infoSubscriptionId">Subscription id</label>
        <input name="subscriptionId" id="infoSubscriptionId">
        <input type="submit" value="Get">
    </form>

    <h1>Register user</h1>
    <form action="/register" method="post">
        <label for="userInfo">User info</label>
        <input name="userInfo" id="userInfo">
        <input type="submit" value="Register">
    </form>

    <h1>Issue subscription</h1>
    <form action="/issue" method="post">
        <label for="userId">User id</label>
        <input name="userId" id="userId">

        <label for="days">Days</label>
        <input name="days" id="days">

        <input type="submit" value="Issue">
    </form>

    <h1>Prolong subscription</h1>
    <form action="/prolong" method="post">
        <label for="subscriptionId">Subscription id</label>
        <input name="subscriptionId" id="subscriptionId">

        <label for="days">Days</label>
        <input name="days" id="days">

        <input type="submit" value="Prolong">
    </form>
</@common.page>