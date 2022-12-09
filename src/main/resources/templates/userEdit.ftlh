<#import "parts/common.ftlh" as c>

<@c.page>
    User editor
    <!-- отправляет на "/user" методом post данные-->
    <form action="/user" method="post" xmlns="http://www.w3.org/1999/html">
        <input type="text" name="username" value="${user.getUsername()}">
        <!-- отображение списка ролей!-->
        <#list roles as role>
            <div>
                <!-- активна роль или нет. seq_contains(role) - метод FM на проверку наличия элементов в коллекции (возвращает bool)-->
                <!-- string("checked", "") преобразовываем в строку, если true - checked, else - ""-->
                <label><input type="checkbox" name="${role}" ${user.getRoles()?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.getId()}" name="userId">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Save</button>
    </form>
</@c.page>