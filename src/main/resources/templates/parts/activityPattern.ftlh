<#macro activityPattern path activityParameter parameter str activityWhatList>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="${path}" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?if_exists}"
                       placeholder="Nickname">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
                <div class="form-group ml-2">
                    <a class="btn btn-info" href="/activity" role="button">Exit</a>
                </div>
            </form>
        </div>
    </div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new ${parameter}
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" name="title" class="form-control"
                           placeholder="Введите название события">
                </div>
                <div class="form-group">
                    <input type="text" name="dateEvent" class="form-control"
                           placeholder="Введите дату проведения события">
                </div>
                <div class="form-group">
                    <input type="text" name="expenses" class="form-control"
                           placeholder="Затраты на мероприятие">
                </div>
                <div class="form-group">
                    <input type="text" name="income" class="form-control"
                           placeholder="Доход с мероприятия">
                </div>
                <div class="form-group">
                    <input type="text" name="nickname" class="form-control"
                           placeholder="Никнейм артиста">
                </div>
                <div class="form-group">
                    <input type="text" name="site" class="form-control"
                           placeholder="Ссылка">
                </div>
                <div class="form-group">
                    <input type="text" name="${activityParameter}" class="form-control"
                           placeholder="${str}">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary ml-2">Add</button>
                </div>
            </form>
        </div>
    </div>
    <#if artistNickname??>
        <h6>${artistNickname}</h6>
    <#else> Enter nickname
    </#if>
    <#if activityWhatList?has_content>
        <table class="table mt-3">
            <thead class="thead-light">
            <tr align="center">
                <th width="20%" scope="col"> Activity title</th>
                <#if parameter == "track">
                    <th width="20%" scope="col"> Track name</th>
                </#if>
                <#if parameter == "reward">
                    <th width="20%" scope="col"> Result</th>
                </#if>
                <#if parameter == "concert">
                    <th width="20%" scope="col"> Location</th>
                </#if>
                <#if parameter == "movie">
                    <th width="20%" scope="col"> Producer name</th>
                </#if>
                <th width="20%" scope="col"> Date of the event</th>
                <th width="20%" scope="col"> Expenses</th>
                <th width="20%" scope="col"> Income</th>
                <th width="20%" scope="col"> Link</th>
            </tr>
            </thead>
            <#list activityWhatList as whatActivity>
                <tbody align="center">
                <td><span> ${whatActivity.activity.title} </span></td>
                <#if parameter == "track">
                    <td><span> ${whatActivity.track.name} </span></td>
                </#if>
                <#if parameter == "reward">
                    <td><span> ${whatActivity.reward.result} </span></td>
                </#if>
                <#if parameter == "concert">
                    <td><span> ${whatActivity.concert.location} </span></td>
                </#if>
                <#if parameter == "movie">
                    <td><span> ${whatActivity.movie.producer} </span></td>
                </#if>
                <td><span> ${whatActivity.activity.dateEvent} </span></td>
                <td><span> ${whatActivity.activity.expenses} $ </span></td>
                <td><span> ${whatActivity.activity.income} $ </span></td>
                <td><span> ${whatActivity.activity.getLink().getSite()} </span></td>
                </tbody>
            <#else>
                </br>Список пуст
            </#list>
        </table>
    </#if>
</#macro>