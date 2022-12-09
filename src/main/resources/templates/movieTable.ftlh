<#import "parts/common.ftlh" as c>
<#import "parts/activityPattern.ftlh" as a>

<@c.page>
    <@a.activityPattern "/movie" "producer" "movie" "Введите имя продюсера" activityMovieList?if_exists/>
</@c.page>