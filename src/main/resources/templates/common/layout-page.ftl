<#if (pageInfo.pages>0)>
<p class="mb20">共<strong class="text-danger">${pageInfo.pages}</strong>页，每页显示<strong class="text-danger">${pageInfo.pageSize}</strong>条数据，总共<strong class="text-danger">${pageInfo.total}</strong>条数据</p>
<ul class="pagination data-page">
            <#if pageInfo.isFirstPage>
            <li class="disabled">
                <a href="javascript:void(0)" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <#else>
            <li>
                <a href="javascript:void(0)" aria-label="Previous" data-page="1" class="page-search">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            </#if>
            <#list pageInfo.navigatepageNums as num>
                <#if pageInfo.pageNum==num>
                    <li class="active"><a href="javascript:void(0)">${num}</a></li>
                <#else>
                    <li><a href="javascript:void(0)" data-page="${num}" class="page-search">${num}</a></li>
                </#if>
            </#list>
            <#if pageInfo.isLastPage>
            <li class="disabled">
                <a href="javascript:void(0)" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <#else>
            <li>
                <a href="javascript:void(0)" aria-label="Next" data-page="${pageInfo.pages}" class="page-search">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            </#if>
</ul>
</#if>
