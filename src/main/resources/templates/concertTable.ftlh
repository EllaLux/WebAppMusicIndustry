<#import "parts/common.ftlh" as c>
<#import "parts/activityPattern.ftlh" as a>

<@c.page>
    <@a.activityPattern "/concert" "location" "concert" "Введите место проведения концерта" activityConcertList?if_exists/>
</@c.page>