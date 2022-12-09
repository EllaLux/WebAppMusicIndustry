<#import "parts/common.ftlh" as c>
<#import "parts/activityPattern.ftlh" as a>

<@c.page>
    <@a.activityPattern "/track" "name" "track" "Введите название трека" activityTrackList?if_exists/>
</@c.page>