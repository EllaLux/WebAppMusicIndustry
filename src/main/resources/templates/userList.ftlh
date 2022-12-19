<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form class="form-inline">
                <input type="text" name="filter" class="form-control" value="User name"
                       placeholder="Nickname">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <table class="table table-secondary mt-3">
        <thead class="thead-dark">
        <tr align="center">
            <th>User name</th>
            <th>Role</th>
            <th>Edit</th>
        </tr>
        </thead>

        <tbody align="center">
        <#list users as user>
            <tr>
                <td>${user.getUsername()}</td>
                <td>
                    <#list user.roles as role>
                        ${role}<#sep>,
                    </#list>
                </td>
                <td> <a href="/user/${user.getId()}">✓</a> </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>