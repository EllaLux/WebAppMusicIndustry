<#include "security.ftlh">
<#import "loginPattern.ftlh" as l>
<!-- панель навигации, которая изменяется в зависимости от ширины экрана
(lg - на больших экранах панель навигации показывается в развернутом виде)
navbar-light bg-light - цветовая схема панели navbar navbar-light-->
<nav class="navbar navbar-expand-lg navbar-light ${(style??)?string('fixed-top', '')}">
    <a class="navbar-brand" href="/">
        <img src="/static/img/logoPopStars3.png" width="70" height="70" alt="">
    </a>
    <!--a class="navbar-brand" href="/">POP STARS</a>-->
    <!-- кнопка меню, которая будет появляться на экранах меньше чем lg-->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <!-- описание меню (collapse - меню схлопываем) -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <!-- основа меню список  (navbar-nav mr-auto - автоматически генерируемое меню)-->
        <ul class="navbar-nav mr-auto">
            <!-- каждый элемент списка часть меню -->
            <li class="nav-item">
                <!-- навигационная ссылка -->
                <a class="nav-link" href="/">POP STARS</a>
            </li>
            <#if isAdmin>
            <li class="nav-item">
                <!-- навигационная ссылка -->
                <a class="nav-link" href="/company">COMPANY</a>
            </li>
            </#if>
            <li class="nav-item">
                <!-- навигационная ссылка -->
                <a class="nav-link" href="/staff">STAFF</a>
            </li>
            <li class="nav-item">
                <!-- навигационная ссылка -->
                <a class="nav-link" href="/artist">ARTISTS</a>
            </li>
            <li class="nav-item">
                <!-- навигационная ссылка -->
                <a class="nav-link" href="/activity">ACTIVITY</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <!-- навигационная ссылка -->
                    <a class="nav-link" href="/user">USERS LIST</a>
                </li>
            </#if>
            <!-- В security мы описываем переменную user только в том случае, если user авторизован -->
            <#if user??>
                <li class="nav-item">
                    <!-- навигационная ссылка -->
                    <a class="nav-link" href="/user/profile">PROFILE</a>
                </li>
            </#if>
        </ul>
        <#if user??>
            <div class="navbar-text mr-3">Welcome, ${name}!</div>
        <#else>
            <form action="/login" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-color">Sign In</button>
            </form>
        </#if>
        <@l.logout />
    </div>
</nav>
