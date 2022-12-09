<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>Please, select activity!</h5>
    <form class="form-inline">
        <div class="form-group mb-2">
            <a class="btn btn-info" href="/track" role="button">Add track</a>
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <a class="btn btn-info" href="/reward" role="button">Add reward</a>
        </div>
    </form>
    <form class="form-inline">
        <div class="form-group mb-2">
            <a class="btn btn-info" href="/movie" role="button">Add movie</a>
        </div>
        <div class="form-group mx-sm-3 mb-2" >
            <a class="btn btn-info" href="/concert" role="button">Add concert</a>
        </div>
    </form>
</@c.page>