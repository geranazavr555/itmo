<#import "common.ftlh" as common>

<@common.page>
    <h1 style="display: inline">${currentUser.login} task lists</h1>
    <form method="post" action="/logout" style="display: inline; float: right; margin-top: 0.5rem; margin-left: 0.3rem;">
        <button class="btn btn-danger new-list-button">Logout</button>
    </form>
    <button class="btn btn-primary new-list-button" style="float: right; margin-top: 0.5rem;">New task list</button>
    <#assign taskLists=currentUser.taskLists?filter(x -> !x.deleted)/>
    <#if (taskLists?size > 0)>
        <div class="row">
            <div class="col-4">
                <div class="list-group" id="list-tab" role="tablist">
                    <#list taskLists as taskList>
                        <a class="list-group-item list-group-item-action" id="taskListLabel${taskList.taskListId}" <#--
                        -->data-bs-toggle="list" href="#taskList${taskList.taskListId}" role="tab" <#--
                        -->aria-controls="taskList${taskList.taskListId}">${taskList.name}</a>
                    </#list>
                </div>
            </div>
            <div class="col-8">
                <div class="tab-content" id="nav-tabContent">
                    <#list taskLists as taskList>
                        <div class="tab-pane fade" id="taskList${taskList.taskListId}" role="tabpanel" <#--
                        -->aria-labelledby="taskListLabel${taskList.taskListId}">
                            <h4 style="display: inline">${taskList.name}</h4>
                            <div class="deleteEditButtons">
                                <button data-taskListId="${taskList.taskListId}" class="btn btn-primary new-button">
                                    Create task
                                </button>
                                <button data-taskListId="${taskList.taskListId}" class="btn btn-secondary edit-button">
                                    Edit
                                </button>
                                <form style="display: inline" method="post" action="/list/${taskList.taskListId}/delete">
                                    <input type="submit" data-taskListName="${taskList.name}"
                                           class="btn btn-danger delete-button" value="Delete"
                                            data-name="${taskList.name}">
                                </form>
                            </div>

                            <#assign taskListItems=taskList.tasks?filter(x -> !x.deleted)/>
                            <div class="list-group" style="margin-top: 1rem;">
                                <#list taskListItems as task>
                                    <label class="list-group-item" >
                                        <input class="form-check-input me-1 task-change-done" data-taskId="${task.taskId}"
                                               data-taskListId="${taskList.taskListId}" data-taskName="${task.name}"
                                               type="checkbox" <#if task.done>checked</#if>>
                                        ${task.name}
                                        <a style="float: right" href="/list/${taskList.taskListId}/tasks/${task.taskId}">Edit</a>
                                        <#if task.description?has_content>
                                            <a style="float: right; margin-right: 0.5rem;"
                                               data-description="${task.description}" class="show-description"
                                               data-bs-target="#description${task.taskId}"
                                               data-bs-toggle="modal"
                                               href="#taskList${taskList.taskListId}">Show description</a>

                                            <div class="modal fade" id="description${task.taskId}" tabindex="-1" aria-labelledby="descriptionLabel${task.taskId}" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="descriptionLabel${task.taskId}">${task.name}</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            ${task.description}
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </#if>
                                    </label>
                                </#list>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>

        <script>
            $(function () {
                const hashIndex = window.location.href.indexOf('#');
                if (hashIndex > 0) {
                    const id = window.location.href.substring(hashIndex + "#taskList".length);
                    $("#taskListLabel" + id).addClass("active");
                    $("#taskList" + id).addClass("active").addClass("show");
                }

                $(".edit-button").click(function () {
                    window.location.href = "/list/" + $(this).attr("data-taskListId") + "/edit";
                });
                $(".new-button").click(function () {
                    window.location.href = "/list/" + $(this).attr("data-taskListId") + "/tasks/new";
                });
                $(".new-list-button").click(function () {
                    window.location.href = "/list/new";
                });
                $(".delete-button").click(function () {
                    return confirm("Are you sure you want to delete task list \"" + $(this).attr("data-name") + "\"?");
                });
                $(".task-change-done").change(function () {
                    const done = $(this).is(":checked");
                    const name = $(this).attr("data-taskListId");
                    $.post(
                        "/list/" + $(this).attr("data-taskListId") + "/tasks/" + $(this).attr("data-taskId") + "/done",
                        {"done": done},
                        function (response) {
                            if (response === "ok")
                            {
                                if (done)
                                    $.notify("\"" + name + "\" done!", "success");
                                else
                                    $.notify("\"" + name + "\" not done :(", "warn");
                            } else
                                window.location.reload();
                        },
                        "json"
                    );
                });
            });
        </script>
    <#else>
        <div>
            You have no task lists yet.
            <a href="/list/new">Create task list</a>
        </div>
    </#if>
</@common.page>