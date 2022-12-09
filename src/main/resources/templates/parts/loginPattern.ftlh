<#macro logout>
    <#include "security.ftlh">
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if user??>
            <button type="submit" class="btn btn-color">Sign Out</button>
        </#if>
    </form>
</#macro>

<#macro login path isRegistrationForm str check>

    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100">

                <form class="login100-form validate-form" action="${path}" method="post" enctype="multipart/form-data">
                    <span class="login100-form-title p-b-43">${str}</span>
                    <#if isRegistrationForm>
                        <label class="col-form-label"> Full name:</label>
                        <div class="wrap-input100">
                            <!-- (col-sm-2) ширина текущего элемента будет 2 колонки
                         и (col-form-label) данныей элемент является label полем ввода  -->
                            <input type="text" name="fullname"
                                   class="input100 form-control ${(fullnameError??)?string('is-invalid', '')}"
                                   value="<#if user??>${user.fullname}</#if>"
                                   placeholder="Full name"/>
                            <span class="focus-input100"></span>
                            <#if fullnameError??>
                                <div class="invalid-feedback">
                                    ${fullnameError}
                                </div>
                            </#if>
                        </div>
                    </#if>
                    <label class="col-form-label"> User name:</label>
                    <div class="wrap-input100">
                        <input type="text" name="username"
                               class="input100 form-control ${(usernameError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.username}</#if>"
                               placeholder="User name"/>
                        <span class="focus-input100"></span>
                        <#if usernameError??>
                            <div class="invalid-feedback">
                                ${usernameError}
                            </div>
                        </#if>
                    </div>
                    <label class="col-form-label"> Password:</label>
                    <div class="wrap-input100">
                        <input type="password" name="password"
                               class="input100 form-control ${(passwordError??)?string('is-invalid', '')}"
                               placeholder="Password"/>
                        <span class="focus-input100"></span>
                        <#if passwordError??>
                            <div class="invalid-feedback">
                                ${passwordError}
                            </div>
                        </#if>
                    </div>
                    <#if isRegistrationForm>
                        <label class="col-form-label"> Duplicate password:</label>
                        <div class="wrap-input100">
                            <input type="password" name="password2"
                                   class="input100 form-control ${(password2Error??)?string('is-invalid', '')}"
                                   placeholder="Duplicate password"/>
                            <span class="focus-input100"></span>
                            <#if password2Error??>
                                <div class="invalid-feedback">
                                    ${password2Error}
                                </div>
                            </#if>
                        </div>
                        <label class="col-form-label"> Phone number:</label>
                        <div class="wrap-input100">
                            <input type="text" name="contacts"
                                   class="input100 form-control ${(contactsError??)?string('is-invalid', '')}"
                                   value="<#if user??>${user.contacts}</#if>"
                                   placeholder="Phone number"/>
                            <span class="focus-input100"></span>
                            <#if contactsError??>
                                <div class="invalid-feedback">
                                    ${contactsError}
                                </div>
                            </#if>
                        </div>
                        <label class="col-form-label"> Email:</label>
                        <div class="wrap-input100">
                            <input type="email" name="email"
                                   class="input100 form-control ${(emailError??)?string('is-invalid', '')}"
                                   value="<#if user??>${user.email}</#if>"
                                   placeholder="Email"/>
                            <span class="focus-input100"></span>
                            <#if emailError??>
                                <div class="invalid-feedback">
                                    ${emailError}
                                </div>
                            </#if>
                        </div>
                        <label class="col-form-label"> Company name:</label>
                        <div class="wrap-input100">
                            <input type="text" name="companyName"
                                   class="input100 form-control ${(companyNameError??)?string('is-invalid', '')}"
                                   placeholder="Company name"/>
                            <span class="focus-input100"></span>
                            <#if companyNameError??>
                                <div class="invalid-feedback">
                                    ${companyNameError}
                                </div>
                            </#if>
                        </div>
                        <#if !check>
                            <label class="col-form-label"> Position:</label>
                            <div class="wrap-input100">
                                <input type="text" name="position"
                                       class="input100 form-control"
                                       placeholder="Position"/>
                                <span class="focus-input100"></span>
                            </div>
                        </#if>
                        <label class="col-form-label"> File:</label>
                        <div class="wrap-input100">
                            <input type="file" name="file"
                                   class="input100 form-control file"
                                   placeholder="File"/>
                            <span class="focus-input100"></span>
                        </div>

                        <#if check>
                            <#include "artistRegistrationPart.ftlh">
                        </#if>
                    </#if>
                    <div class="mb-2">
                        <#if !isRegistrationForm>
                            <div class="text-center pt_pb">
						        <span class="txt2">
                                    <a href="/staffRegistration">Registration as a staff</a></br>
                                    <a href="/artistRegistration">Registration as an artist</a>
                                </span>
                            </div>
                        </#if>
                    </div>

                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <div class="container-login100-form-btn">
                        <button type="submit" class="login100-form-btn">
                            <#if isRegistrationForm>
                                Create
                            <#else>
                                Sign In
                            </#if>
                        </button>
                    </div>
                </form>
                <div class="login100-more" style="background-image: url('/static/img/loginPage2.jpg');">
                </div>
            </div>
        </div>
    </div>
    </body>
</#macro>