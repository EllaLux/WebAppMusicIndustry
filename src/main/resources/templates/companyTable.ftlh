<#import "parts/common.ftlh" as c>
<#import "parts/security.ftlh" as s>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/company" class="form-inline">
                <input type="text" name="name" class="form-control" value="${name?if_exists}"
                       placeholder="Company name">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <#if s.isAdmin>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
           aria-controls="collapseExample">
            Add new company
        </a>
        <div class="collapse <#if company??>show</#if>" id="collapseExample">
            <div class="form-group mt-3">
                <form method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="text" name="name" class="form-control ${(nameError??)?string('is-invalid', '')}"
                               value="<#if company??>${company.name}</#if>" placeholder="Введите название">
                        <#if nameError??>
                            <div class="invalid-feedback">
                                ${nameError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="text" name="date" class="form-control ${(dateError??)?string('is-invalid', '')}"
                               value="<#if company??>${company.date}</#if>" placeholder="Введите дату основания">
                        <#if dateError??>
                            <div class="invalid-feedback">
                                ${dateError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="text" name="address" class="form-control ${(addressError??)?string('is-invalid', '')}"
                               value="<#if company??>${company.address}</#if>" placeholder="Введите адрес">
                        <#if addressError??>
                            <div class="invalid-feedback">
                                ${addressError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="text" name="budget" class="form-control ${(budgetError??)?string('is-invalid', '')}" placeholder="Введите бюджет">
                        <#if budgetError??>
                            <div class="invalid-feedback">
                                ${budgetError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="text" name="site" class="form-control" placeholder="Ссылка на сайт компании">
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary ml-2">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </#if>
    <table class="table mt-3">
        <thead class="thead-light">
        <tr align="center">
            <th width="20%" scope="col"> Company name</th>
            <th width="20%" scope="col"> Date of foundation</th>
            <th width="20%" scope="col"> Address</th>
            <th width="20%" scope="col"> Budget</th>
            <th width="20%" scope="col"> Link</th>
            <#if s.isAdmin>
                <th width="20%" scope="col"> Delete</th>
            </#if>
        </tr>
        </thead>
        <#list companyAndLinkList as companyAndLink>
            <tbody align="center">
            <td><span> ${companyAndLink.company.name} </span></td>
            <td><span> ${companyAndLink.company.date} </span></td>
            <td><span> ${companyAndLink.company.address} </span></td>
            <td><span> ${companyAndLink.company.budget} $ </span></td>
            <td><span>
                    <#if companyAndLink.link??>
                        ${companyAndLink.link.getSite()?if_exists}
                    <#else> -
                    </#if>
                </span></td>
            <#if s.isAdmin>
                <td><span>
                    <form method="get" action="/companyDelete" class="form-inline">
                        <input type="hidden" name="companyId" value="${companyAndLink.company.getId()}">
                         <button type="submit" class="btn btn-primary ml-2">Delete</button>
                    </form>
                </span></td>
                </tbody>
            </#if>
        <#else>
            </br>Список пуст
        </#list>
    </table>
    </form>
</@c.page>