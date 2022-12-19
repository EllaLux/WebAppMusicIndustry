<#import "parts/common.ftlh" as c>
<#import "parts/security.ftlh" as s>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/artist" class="form-inline">
                <input type="text" name="nickname" class="form-control" value="${nickname?if_exists}"
                       placeholder="artist nickname">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <table class="table table-secondary mt-3">
        <thead class="thead-dark">
        <tr align="center">
            <th width="20%" scope="col"> Nickname</th>
            <th width="20%" scope="col"> Full name</th>
            <th width="20%" scope="col"> Birthday</th>
            <th width="20%" scope="col"> Education</th>
            <th width="20%" scope="col"> Debut date</th>
            <th width="20%" scope="col"> Salary</th>
            <th width="20%" scope="col"> PositionGroup</th>
            <#if s.isAdmin>
                <th width="20%" scope="col"> Delete</th>
            </#if>
        </tr>
        </thead>
        <#list userAndArtistList as userAndArtist>
            <tbody align="center">
            <td><span> ${userAndArtist.artist.getNickname()} </span></td>
            <td><span> ${userAndArtist.user.getFullname()} </span></td>
            <td><span> ${userAndArtist.artist.getBirthday()} </span></td>
            <td><span> ${userAndArtist.artist.getEducation()} </span></td>
            <td><span> ${userAndArtist.artist.getDebut()} </span></td>
            <td><span> ${userAndArtist.artist.getSalary()} </span></td>
            <td><span>
                    <#if userAndArtist.artist??>
                        ${userAndArtist.artist.getPositionGroup()?if_exists}
                    <#else> -
                    </#if>
                </span></td>
            <#if s.isAdmin>
                <td><span>
                    <form method="get" action="/user/userDelete" class="form-inline">
                        <input type="hidden" name="userId" value="${userAndArtist.user.getId()}">
                         <button type="submit" class="btn btn-primary ml-2">Delete</button>
                    </form>
                </span></td>
            </#if>
            </tbody>
        <#else>
            Artists are missing!
        </#list>
    </table>
</@c.page>