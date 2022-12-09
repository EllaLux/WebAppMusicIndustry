<#import "parts/common.ftlh" as c>
<#import "parts/loginPattern.ftlh" as l>

<@c.page>
    <h5>${username?if_exists}</h5>
    ${message?if_exists}
    <div class="container">
        <form method="post">

            <div class="row">
                <div class="col col-lg-6" style="text-align: center">
                    <#if user.filename??>
                        <!-- если email не указан, будет отображаться пустая строчка -->
                        <img class="photoProfile imgProfile rounded" src="/img/${user.filename}">
                    </#if>
                </div>

                <div class="col-lg-6">
                    <div class="form-group row">
                        <!-- (col-sm-2) ширина текущего элемента будет 2 колонки
                     и (col-form-label) данныей элемент является label полем ввода  -->
                        <label class="col-sm-3 col-form-label"> Full Name:</label>
                        <div class="col-sm-6">
                            <div class="form-control input100">
                                <#if user??>${user.fullname}</#if>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label"> User Name:</label>
                        <div class="col-sm-6">
                            <div class="form-control input100">
                                <#if user??>${user.username}</#if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label"> Position:</label>
                        <div class="col-sm-6">
                            <div class="form-control input100">
                                <#if staff??>${staff.position}</#if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label"> Password:</label>
                        <div class="col-sm-6">
                            <input type="password" name="password" class="form-control input100" placeholder="Password"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label"> Contacts:</label>
                        <div class="col-sm-6">
                            <input type="text" name="contacts" class="form-control input100" placeholder="Contacts"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label"> Email:</label>
                        <div class="col-sm-6">
                            <!-- если email не указан, будет отображаться пустая строчка -->
                            <input type="email" name="email" class="form-control input100" placeholder="Email" value="${email!''}"/>
                        </div>
                    </div>

                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary">Save</button>

                </div>
            </div>
        </form>
    </div>
</@c.page>