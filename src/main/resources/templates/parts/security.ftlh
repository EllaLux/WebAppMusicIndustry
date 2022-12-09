<!-- описываем переменные -->
<!-- known = Session.SPRING_SECURITY_CONTEXT?? проверяем существует ли у нас в контексте необходимый объект
Spring security помещает контекст freemarker в специальный объект, который
позволяет оперировать контекстом Spring security.
если у нас этот объект определён в контексте, значит мы можем работать с пользователем Spring security -->
<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>
<!-- проверяем если SS существует, выполняем действия-->
<#if known>
        <!-- значение содержит нашеготпользователя User-->
        <#assign
            user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
            name = user.getUsername()
            isAdmin = user.isAdmin()
        >
    <#else>
        <#assign
            name = "unknown"
            isAdmin = false
        >
</#if>