<#import "parts/common.ftlh" as c>
<#import "parts/activityPattern.ftlh" as a>

<@c.page>
    <@a.activityPattern "/reward" "result" "reward" "Введите итог участия в мероприятии" activityRewardList?if_exists/>
</@c.page>