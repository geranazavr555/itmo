<#-- @ftlvariable name="userEnterDto" type="ru.itmo.sdcourse.hw4.dto.UserEnterDto" -->
<#import "common.ftlh" as common>

<@common.page>
    <div class="row">
        <div class="col-sm"></div>
        <form method="post" class="col-sm">
            <h1>Task manager</h1>

            <div class="form-group">
                <label for="enterLoginInput">Login:</label>
                <input class="form-control" id="enterLoginInput" name="login" value="${userEnterDto.login!}"/>
                <@common.error "userEnterDto.login"/>
            </div>

            <div class="form-group">
                <label for="enterPasswordInput">Password:</label>
                <input class="form-control" id="enterPasswordInput" name="password" type="password" value="${userEnterDto.password!}"/>
                <@common.error "userEnterDto.password"/>
            </div>

            <input class="btn btn-primary" type="submit" value="Enter">
            <a style="float: right;" href="/register">Register</a>
        </form>
        <div class="col-sm"></div>
    </div>
</@common.page>