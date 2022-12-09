<#import "parts/commonLogin.ftlh" as c>
<#import "parts/loginPattern.ftlh" as l>

<@c.page2>
    <div>
        ${message?if_exists}
    </div>
    <@l.login "/staffRegistration" true "Add new staff" false/>
</@c.page2>