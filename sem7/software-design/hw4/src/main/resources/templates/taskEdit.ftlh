<#-- @ftlvariable name="taskDto" type="ru.itmo.sdcourse.hw4.dto.TaskDto" -->
<#import "common.ftlh" as common>

<#assign new=!taskDto.taskId??/>
<@common.page>
    <div class="row">
        <div class="col-sm"></div>
        <div  class="col-sm">
            <form method="post">
                <h1><#if new>New task<#else>Edit task</#if></h1>

                <div class="form-group">
                    <label for="taskNameInput">Name:</label>
                    <input class="form-control" id="taskNameInput" name="name" value="${taskDto.name!}">
                    <@common.error "taskDto.name"/>
                </div>

                <div class="form-group">
                    <label for="taskNameInput">Description:</label>
                    <textarea class="form-control" id="taskNameInput" name="description">${taskDto.description!}</textarea>
                    <@common.error "taskDto.description"/>
                </div>

                <input class="btn btn-primary" type="submit" value="<#if new>Create<#else>Edit</#if>">
            </form>
            <#if !new>
                <form style="float: right; display: inline; position: relative; bottom: 2.3rem;"
                      method="post" action="/list/${taskDto.taskListId}/tasks/${taskDto.taskId}/delete">
                    <input type="submit" class="btn btn-danger delete-button" value="Delete" data-name="${taskDto.name}"/>
                </form>
                <script>
                    $(".delete-button").click(function () {
                        return confirm("Are you sure you want to delete task \"" + $(this).attr("data-name") + "\"?");
                    });
                </script>
            </#if>
        </div>
        <div class="col-sm"></div>
    </div>
</@common.page>