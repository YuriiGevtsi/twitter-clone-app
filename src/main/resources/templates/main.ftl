<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <form method="get" action="/main" class="form-inline">
            <input class="form-control" type="text" name="filter" value="${filter?ifExists}" placeholder="Search by tag"/>
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>

<#include "parts/messageEdit.ftl"/>
<#include "parts/messageList.ftl"/>

</@c.page>