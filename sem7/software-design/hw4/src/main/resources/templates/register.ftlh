<#-- @ftlvariable name="userRegistrationDto" type="ru.itmo.sdcourse.hw4.dto.UserRegistrationDto" -->
<#import "common.ftlh" as common>

<@common.page>
    <div class="row">
        <div class="col-sm"></div>
        <form method="post" class="col-sm">
            <h1>Task manager</h1>

            <div class="form-group">
                <label for="registerLoginInput">Login:</label>
                <input class="form-control" id="registerLoginInput" name="login" value="${userRegistrationDto.login!}"/>
                <@common.error "userRegistrationDto.login"/>
            </div>

            <div class="form-group">
                <label for="registerPasswordInput">Password:</label>
                <input class="form-control" id="registerPasswordInput" name="password" type="password" value="${userRegistrationDto.password!}"/>
                <@common.error "userRegistrationDto.password"/>
            </div>

            <div class="form-group">
                <label for="registerPasswordConfirmationInput">Password confirmation:</label>
                <input class="form-control" id="registerPasswordConfirmationInput" name="passwordConfirmation" type="password" value="${userRegistrationDto.password!}"/>
                <@common.error "userRegistrationDto.passwordConfirmation"/>
            </div>

            <input class="btn btn-primary" type="submit" value="Register">
            <a style="float: right;" href="/enter">Enter</a>
        </form>
        <div class="col-sm"></div>
    </div>
</@common.page>