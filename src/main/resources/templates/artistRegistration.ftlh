<#import "parts/commonLogin.ftlh" as c>
<#import "parts/loginPattern.ftlh" as l>

<@c.page2>
    <div>
        ${message?if_exists}
    </div>
    <@l.login "/artistRegistration" true "Add new artist" true/>
</@c.page2>