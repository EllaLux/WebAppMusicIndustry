<#import "parts/common.ftlh" as c>
<#import "parts/security.ftlh" as s>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/staff" class="form-inline">
                <input type="text" name="fullname" class="form-control" value="${fullname?if_exists}"
                       placeholder="Staff full name">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <table class="table table-secondary mt-3">
        <thead class="thead-dark">
        <tr align="center">
            <th width="20%" scope="col"> Full name</th>
            <th width="20%" scope="col"> Username</th>
            <th width="20%" scope="col"> Position</th>
            <th width="20%" scope="col"> Role</th>
            <#if s.isAdmin>
                <th width="20%" scope="col"> Delete</th>
            </#if>
        </tr>
        </thead>
        <#list userAndStaffList as userAndStaff>
            <tbody align="center">
            <td><span> ${userAndStaff.user.getFullname()} </span></td>
            <td><span> ${userAndStaff.user.getUsername()} </span></td>
            <td><span>
                <#if userAndStaff.staff??>
                    ${userAndStaff.staff.getPosition()?if_exists}
                <#else> -
                </#if>
            </span></td>
            <td><span>
                    <#list userAndStaff.user.getRoles() as role>
                        ${role}<#sep>,
                    </#list>
                </span></td>
            <#if s.isAdmin>
                <td><span>
                    <form method="get" action="/user/userDelete" class="form-inline">
                        <input type="hidden" name="userId" value="${userAndStaff.user.getId()}">
                         <button type="submit" class="btn btn-primary ml-2">Delete</button>
                    </form>
                </span></td>
            </#if>
            </tbody>
        <#else>
            Staff are missing!
        </#list>
    </table>
</@c.page>