<#-- @ftlvariable name="taskListDto" type="ru.itmo.sdcourse.hw4.dto.TaskListDto" -->
<#import "common.ftlh" as common>

<#assign new=!taskListDto.taskListId??/>
<@common.page>
    <div class="row">
        <div class="col-sm"></div>
        <form method="post" class="col-sm">
            <h1><#if new>New task list<#else>Edit task list</#if></h1>
            <#if new>
                <h3>${taskListDto.name!}</h3>
            </#if>

            <div class="form-group">
                <label for="taskListNameInput">Name:</label>
                <input class="form-control" id="taskListNameInput" name="name" value="${taskListDto.name!}"/>
                <@common.error "taskListDto.name"/>
            </div>

            <input class="btn btn-primary" type="submit" value="<#if new>Create<#else>Edit</#if>">
        </form>
        <div class="col-sm"></div>
    </div>
</@common.page>